package org.jesperancinha.sftd.flash214.transactions

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.sftd.flash214.transactions.model.Car
import org.jesperancinha.sftd.flash214.transactions.repository.CarRepository
import org.jesperancinha.sftd.flash214.transactions.utils.AbstractTestContainerTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.Sql.ExecutionPhase

@SpringBootTest
@ActiveProfiles("emb")
@ComponentScan("org.jesperancinha.sftd.flash214.transactions")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = ["classpath:schema.sql"])
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
internal class SpringFlash214LauncherKotlinTest(
    @Autowired
    private val carRepository: CarRepository
) : AbstractTestContainerTest() {

    @BeforeEach
    fun setup() {
        Assertions.assertThat(postgreSQLContainer.isRunning).isTrue
    }

    @Test
    fun testContext() {
        Car.builder().brand("Renault").model("Twingo").build()
            .let { car ->
                carRepository.save(car)
                    .also {
                        ConsolerizerComposer.outSpace()
                            .green("This car has just been saved in the database:")
                            .jsonPrettyPrint(car)
                            .reset()
                    }
            }
            .shouldNotBeNull()
            .apply {
                brand shouldBe "Renault"
                model shouldBe "Twingo"
            }
    }
}