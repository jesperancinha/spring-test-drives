package org.jesperancinha.sftd.flash214.transactions.repository;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash214.transactions.model.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@DataJpaTest
@ActiveProfiles("emb")
@ComponentScan("org.jesperancinha.sftd.flash214.transactions")
@Sql(executionPhase = BEFORE_TEST_METHOD,
        scripts = "classpath:schema.sql")
@DirtiesContext(classMode = BEFORE_CLASS)
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Container
    protected static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14")
            .withUsername("postgres")
            .withPassword("admin")
            .withDatabaseName("db");

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    public void testSaveWhenSaveCarThenRetrieveIt() {
        final var car = Car.builder().brand("Renault").model("Twingo").build();

        final Car savedCar = carRepository.save(car);

        ConsolerizerComposer.outSpace()
                .green("This car has just been saved in the database:")
                .jsonPrettyPrint(car)
                .reset();

        assertThat(savedCar).isNotNull();
        assertThat(savedCar.getBrand()).isEqualTo("Renault");
        assertThat(savedCar.getModel()).isEqualTo("Twingo");
    }

}