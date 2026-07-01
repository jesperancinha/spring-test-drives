package org.jesperancinha.sftd.flash214.transactions.repository

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.sftd.flash214.transactions.model.Car
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.Sql.ExecutionPhase
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@ActiveProfiles("emb")
@ComponentScan("org.jesperancinha.sftd.flash214.transactions")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = ["classpath:schema.sql"])
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class CarRepositoryKotlinTest(
    @Autowired
    private val carRepository: CarRepository
) {

    @Test
    fun testSaveWhenSaveCarThenRetrieveIt() {
       Car.builder().brand("Renault").model("Twingo").build()
           .let { car ->
               carRepository.save(car).also {
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

    companion object {
        @Container
        protected var postgreSQLContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:14")
            .withUsername("postgres")
            .withPassword("admin")
            .withDatabaseName("db")

        @DynamicPropertySource
        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { postgreSQLContainer.jdbcUrl }
            registry.add("spring.datasource.username") { postgreSQLContainer.username }
            registry.add("spring.datasource.password") { postgreSQLContainer.password }
        }
    }
}