package org.jesperancinha.sftd.flash214.transactions.services

import io.kotest.matchers.booleans.shouldBeTrue
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
internal class CarReadCommittedDAOKotlinTest(
    @Autowired
    private val carReadCommittedDAO: CarReadCommittedDAO
) : AbstractTestContainerTest() {
    @BeforeEach
    fun setup() {
        postgreSQLContainer.isRunning.shouldBeTrue()
    }

    /**
     * A running example for the read committed isolation level
     *
     * @throws InterruptedException An error
     */
    @Test
    @Throws(InterruptedException::class)
    fun testReadCommittedWhenRunningThenExemplifyReadCommitted() {
        ConsolerizerComposer.outSpace()
            .cyan(ConsolerizerComposer.title("Read Committed"))
            .magenta("In this example, we are writing data in one transaction.")
            .magenta("At the same time, another transaction is starting.")
            .magenta("Read committed transactions only allow reading after a transaction is done.")
            .magenta("This means we can accompany another transactions progress only after a commit has been made")
            .magenta("In this read committed example, we see that we can read the progress of one transaction from another read committed transaction.")
            .magenta("This can still result in phantom reads, given that the data can change, while the read transaction is ongoing.")
            .reset()
        val executorService = Executors.newFixedThreadPool(2)
        executorService.submit { carReadCommittedDAO.createCar(Car.builder().brand("Citroën").model("2CV").build()) }
        executorService.submit { carReadCommittedDAO.allCars }
        executorService.shutdown()
        val termination = executorService.awaitTermination(50, TimeUnit.SECONDS)
        termination.shouldBeTrue()
    }
}