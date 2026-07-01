package org.jesperancinha.sftd.flash27.monitoring

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.sftd.flash27.monitoring.model.Health
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
internal class Flash27HealthIndicatorKotlinTest(
    @Autowired
    private val mockMvc: MockMvc
) {
    @Test
    @Throws(Exception::class)
    fun health() {
        val contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/actuator/health"))
            .andReturn()
            .shouldNotBeNull()
            .response
            .shouldNotBeNull()
            .contentAsString
        contentAsString
            .shouldNotBeNull()
        ConsolerizerComposer.outSpace()
            .brightBlue("Service response is:")
            .jsonPrettyPrint(contentAsString)
            .reset()
        objectMapper.readValue(contentAsString, Health::class.java)
            .shouldNotBeNull()
            .apply {
                status
                    .shouldNotBeNull()
                    .shouldBeIn("UP", "DOWN", "OUT_OF_SERVICE")
                val db = components
                    .shouldNotBeNull()
                    .db
                db.shouldNotBeNull()
                    .apply {
                        status shouldBe "UP"
                        details.shouldNotBeNull()
                            .apply {
                                status shouldBe "UP"
                                details.shouldNotBeNull()
                                    .apply {
                                        database shouldBe "H2"
                                        result.shouldBeIn(1, null)
                                        validationQuery.shouldBeIn("SELECT 1", "isValid()")
                                        lyric.shouldBeNull()
                                        total.shouldBeNull()
                                        free.shouldBeNull()
                                        threshold.shouldBeNull()
                                    }
                            }
                        components.shouldNotBeNull()
                            .apply {
                                status
                                    .shouldNotBeNull()
                                    .shouldBe("UP")
                                diskSpace
                                    .shouldNotBeNull()
                                    .apply {
                                        status.shouldBe("UP")
                                        details.shouldNotBeNull()
                                            .apply {
                                                database.shouldBeIn("H2", null)
                                                result.shouldBeNull()
                                                validationQuery.shouldBeIn("SELECT 1", "isValid()", null)
                                                lyric.shouldBeNull()
                                            }
                                    }
                                flash27.shouldNotBeNull()
                                    .apply {
                                        status.shouldNotBeNull()
                                            .shouldBeIn("UP", "DOWN", "OUT_OF_SERVICE", "UNKNOWN")
                                        details.apply {
                                            database.shouldBeIn("H2", null)
                                            result.shouldBeNull()
                                            validationQuery.shouldBeIn("SELECT 1", "isValid()", null)
                                            lyric.shouldNotBeNull().shouldBeIn(
                                                "Oh, not to touch a hair on your head",
                                                "Leave you as you are",
                                                "If he felt he had to direct you",
                                                "Then direct you into my arms"
                                            )
                                            total.shouldBeNull()
                                            free.shouldBeNull()
                                            threshold.shouldBeNull()
                                        }
                                    }
                                ping.shouldNotBeNull()
                                    .status
                                    .shouldNotBeNull() shouldBe "UP"
                            }
                    }
            }
    }

    companion object {
        private val objectMapper = ObjectMapper()
    }
}