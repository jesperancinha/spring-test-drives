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
internal class CarSerializableDAOKotlinTest(
    @Autowired
    private val carSerializableDAO: CarSerializableDAO
) : AbstractTestContainerTest() {
    @BeforeEach
    fun setup() {
        postgreSQLContainer.isRunning.shouldBeTrue()
    }

    /**
     * A running example for the serializable isolation level
     *
     * @throws InterruptedException An error
     */
    @Test
    @Throws(InterruptedException::class)
    fun testSerializableWhenRunningThenExemplifySerializable() {
        ConsolerizerComposer.outSpace()
            .cyan(ConsolerizerComposer.title("Serializable"))
            .magenta("In this example, we are writing data in one transaction.")
            .magenta("At the same time, another transaction is starting.")
            .magenta("Serializable transactions only allow reading after on transaction is done. Transaction gain a locked access to the database.")
            .magenta("This way, it is not possible to keep up with the progress in the database via one reading transaction with Serializable isolation level.")
            .magenta("In this Serializable example, we'll see that transactions block each other until completion.")
            .magenta("There is no possibility of phantom reads, because transactions get exclusive access. There are different kinds of locks. This is only the basic way of using locks.")
            .reset()
        val executorService = Executors.newFixedThreadPool(2)
        executorService.submit {
            try {
                Thread.sleep(500)
            } catch (e: InterruptedException) {
                ConsolerizerColor.RED.printThrowableAndExit(e)
            }
            carSerializableDAO.createCar(Car.builder().brand("Citroën").model("2CV").build())
        }
        executorService.submit {
            carSerializableDAO.allCars
            carSerializableDAO.allCars
        }
        executorService.shutdown()
        executorService.awaitTermination(50, TimeUnit.SECONDS).shouldBeTrue()
    }
}