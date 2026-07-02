package org.jesperancinha.sftd.flash214.transactions.utils;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Created by jofisaes on 16/12/2021
 */
@Testcontainers
@ContextConfiguration(initializers = {AbstractTestContainerTest.Initializer.class})
public abstract class AbstractTestContainerTest {
    @Container
    protected static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14")
            .withUsername("postgres")
            .withPassword("admin")
            .withDatabaseName("db");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
