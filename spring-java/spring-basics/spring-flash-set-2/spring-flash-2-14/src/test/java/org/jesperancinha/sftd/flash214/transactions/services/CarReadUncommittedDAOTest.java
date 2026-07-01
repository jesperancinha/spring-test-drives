package org.jesperancinha.sftd.flash214.transactions.services;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash214.transactions.model.Car;
import org.jesperancinha.sftd.flash214.transactions.utils.AbstractTestContainerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("emb")
@ComponentScan({
        "org.jesperancinha.sftd.flash214.transactions",
        "org.jesperancinha.sftd.flash214.transactions.services"
})
@Sql(executionPhase = BEFORE_TEST_METHOD,
        scripts = "classpath:schema.sql")
@DirtiesContext(classMode = BEFORE_CLASS)
class CarReadUncommittedDAOTest extends AbstractTestContainerTest {

    @Autowired
    private CarReadUncommittedDAO carReadUncommittedDAO;

    @BeforeEach
    public void setup() {
        assertThat(postgreSQLContainer.isRunning()).isTrue();
    }

    /**
     * A running example for the read uncommmitted isolation level
     *
     * @throws InterruptedException An error
     */
    @Test
    void testReadUncommittedWhenRunningThenExemplifyReadUncommitted() throws InterruptedException {
        ConsolerizerComposer.outSpace()
                .cyan(title("Read Uncommitted"))
                .magenta("In this example, we are writing data in one transaction.")
                .magenta("At the same time, another transaction is starting.")
                .magenta("Our embedded database, and many database systems do not support read uncommitted.")
                .magenta("This is the reason why this example works in the same way as the read committed one.")
                .magenta("In a read uncommitted example, we would see that we would be able to read the progress of one transaction from another read uncommitted transaction.")
                .magenta("In this example, we never see a single car, because a rollback occurs everytime before the transaction completes.")
                .magenta("In a real case, we would see the new cars being added before a commit or a rollback.")
                .reset();
        final var executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            carReadUncommittedDAO.createCar(Car.builder().brand("Citroën").model("2CV").build());
            carReadUncommittedDAO.createCar(Car.builder().brand("Citroën").model("2CV").build());
            carReadUncommittedDAO.createCar(Car.builder().brand("Citroën").model("2CV").build());
            carReadUncommittedDAO.createCar(Car.builder().brand("Citroën").model("2CV").build());
        });
        executorService.submit(() -> {
            carReadUncommittedDAO.getAllCars();
            carReadUncommittedDAO.getAllCars();
            carReadUncommittedDAO.getAllCars();
            carReadUncommittedDAO.getAllCars();
        });
        executorService.shutdown();
        final boolean termination = executorService.awaitTermination(10, TimeUnit.SECONDS);
        assertThat(termination).isTrue();
    }
}