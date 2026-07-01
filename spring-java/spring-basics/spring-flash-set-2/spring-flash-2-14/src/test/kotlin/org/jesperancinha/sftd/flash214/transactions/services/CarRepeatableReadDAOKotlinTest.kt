package org.jesperancinha.sftd.flash214.transactions.services

import io.kotest.matchers.booleans.shouldBeTrue
import org.jesperancinha.console.consolerizer.common.ConsolerizerColor
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.sftd.flash214.transactions.model.Car
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
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@SpringBootTest
@ActiveProfiles("emb")
@ComponentScan("org.jesperancinha.sftd.flash214.transactions", "org.jesperancinha.sftd.flash214.transactions.services")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = ["classpath:schema.sql"])
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
internal class CarRepeatableReadDAOKotlinTest(
    @Autowired
    private val carRepeatableReadDAO: CarRepeatableReadDAO
) : AbstractTestContainerTest() {
    @BeforeEach
    fun setup() {
        postgreSQLContainer.isRunning.shouldBeTrue()
    }

    /**
     * A running example for the repeatable read isolation level
     *
     * @throws InterruptedException An error
     */
    @Test
    @Throws(InterruptedException::class)
    fun testRepeatableReadWhenRunningThenExemplifyRepeatableRead() {
        ConsolerizerComposer.outSpace()
            .cyan(ConsolerizerComposer.title("Repeatable Read"))
            .magenta("In this example, we are writing data in one transaction.")
            .magenta("At the same time, another transaction is starting.")
            .magenta("A Repeatable read means that the reader transaction will always read at least the same.")
            .magenta("The small nuance comes from the fact that removals will not be detected. Additions, however will.")
            .magenta("In this repeatable read example, we will see how we can read the progress of one transaction from another repeatable transaction.")
            .magenta("We won't, in any case, see the removals, nor we will see the additions. This is after all, an embedded database system")
            .reset()
        val executorService = Executors.newFixedThreadPool(2)
        executorService.submit {
            val car = carRepeatableReadDAO.createCar(Car.builder().brand("Citroën").model("2CV").build())
            carRepeatableReadDAO.createCar(Car.builder().brand("Citroën").model("2CV").build())
            try {
                Thread.sleep(100)
            } catch (e: InterruptedException) {
                ConsolerizerColor.RED.printThrowableAndExit(e)
            }
            carRepeatableReadDAO.deleteCarById(car.id)
        }
        executorService.submit {
            ConsolerizerComposer.outSpace()
                .yellow("First repeatable read transaction. Phantom reads would occur here in a supportive real database")
            carRepeatableReadDAO.allCars
            ConsolerizerComposer.outSpace()
                .yellow("Second repeatable read transaction. Phantom reads would occur here in a supportive real database")
            carRepeatableReadDAO.allCars
            ConsolerizerComposer.outSpace()
                .yellow("Third repeatable read transaction. Phantom reads would occur here in a supportive real database")
            carRepeatableReadDAO.allCars
        }
        executorService.shutdown()
        executorService.awaitTermination(10, TimeUnit.SECONDS).shouldBeTrue()
    }
}