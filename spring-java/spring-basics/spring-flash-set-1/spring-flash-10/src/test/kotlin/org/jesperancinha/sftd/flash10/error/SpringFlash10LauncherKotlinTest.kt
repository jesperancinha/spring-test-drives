package org.jesperancinha.sftd.flash10.error

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(SpringFlash10Launcher::class)
@ActiveProfiles("prod")
internal class SpringFlash10LauncherKotlinTest @Autowired constructor(
    private val mockMvc: MockMvc
) {

    @Test
    fun `should test context`() {
    }

    @Test
    @Throws(Exception::class)
    fun `should go to error page when page gets called without authentication`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
            .shouldNotBeNull()
            .modelAndView
            .shouldNotBeNull()
            .apply {
                viewName
                    .shouldNotBeNull() shouldBe "runtimeError"
                model
                    .shouldNotBeNull()
                    .let {
                        it["ex"].shouldBeInstanceOf<RuntimeException>()
                        it["url"]
                            .shouldNotBeNull()
                            .toString() shouldBe "http://localhost/"

                    }
            }
    }
}