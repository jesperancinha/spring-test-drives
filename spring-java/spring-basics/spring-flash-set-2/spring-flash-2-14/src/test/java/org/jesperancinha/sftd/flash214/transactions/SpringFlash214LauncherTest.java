package org.jesperancinha.sftd.flash214.transactions;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash214.transactions.model.Car;
import org.jesperancinha.sftd.flash214.transactions.repository.CarRepository;
import org.jesperancinha.sftd.flash214.transactions.utils.AbstractTestContainerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("emb")
@ComponentScan("org.jesperancinha.sftd.flash214.transactions")
@Sql(executionPhase = BEFORE_TEST_METHOD,
        scripts = "classpath:schema.sql")
@DirtiesContext(classMode = BEFORE_CLASS)
class SpringFlash214LauncherTest extends AbstractTestContainerTest {

    @Autowired
    private CarRepository carRepository;

    @BeforeEach
    public void setup() {
        assertThat(postgreSQLContainer.isRunning()).isTrue();
    }

    @Test
    void testContext() {
        final var car = Car.builder().brand("Renault").model("Twingo").build();

        final var savedCar = carRepository.save(car);

        ConsolerizerComposer.outSpace()
                .green("This car has just been saved in the database:")
                .jsonPrettyPrint(car)
                .reset();

        assertThat(savedCar).isNotNull();
        assertThat(savedCar.getBrand()).isEqualTo("Renault");
        assertThat(savedCar.getModel()).isEqualTo("Twingo");
    }
}