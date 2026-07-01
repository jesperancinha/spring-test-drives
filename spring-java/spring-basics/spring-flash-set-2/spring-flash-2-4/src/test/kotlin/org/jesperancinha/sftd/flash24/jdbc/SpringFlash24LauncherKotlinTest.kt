package org.jesperancinha.sftd.flash24.jdbc

import com.ninjasquad.springmockk.SpykBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainOnly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.support.SQLExceptionTranslator
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ContextConfiguration(initializers = [SpringFlash24LauncherKotlinTest.Initializer::class])
internal class SpringFlash24LauncherKotlinTest @Autowired constructor(
    @Autowired
    private val springFlash24Launcher: SpringFlash24Launcher
) {
    @SpykBean
    lateinit var jdbcTemplate: JdbcTemplate

    internal class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
            TestPropertyValues.of(
                "spring.datasource.url=${postgreSQLContainer.jdbcUrl}",
                "spring.datasource.username=${postgreSQLContainer.username}",
                "spring.datasource.password=${postgreSQLContainer.password}"
            ).applyTo(configurableApplicationContext.environment)
        }
    }

    @BeforeEach
    fun setUp() {
        postgreSQLContainer.isRunning.shouldBeTrue()
        springFlash24Launcher.initializeDatabase()
        val concert1 = arrayOf(
            "VH1 Divas Live",
            "New York's Beacon Theatre, US",
            LocalDateTime.of(1998, 4, 16, 0, 0, 0).toString()
        )
        val concert2 =
            arrayOf("Lollapalooza 2012", "Chicago’s Grant Park, US", LocalDateTime.of(2012, 8, 12, 16, 0, 0).toString())
        val concerts = listOf(
            concert1, concert2
        )
        springFlash24Launcher.textCRUDCreate(concerts)
    }

    @Test
    fun `should load application context`() {
    }

    @Test
    fun `should delete 1 and 2 but not 3 on CRUD delete execution`() {
        val concert1Id = springFlash24Launcher.testCRUDDelete(1)
        val concert2Id = springFlash24Launcher.testCRUDDelete(2)
        val concert3Id = springFlash24Launcher.testCRUDDelete(3)
        concert1Id shouldBe 1
        concert2Id shouldBe 1
        concert3Id shouldBe 0
    }

    @Test
    fun `should read as normal when reading 2`() {
        springFlash24Launcher.testCRUDRead2()
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .let {
                it.toList().apply {
                    get(0).apply {
                        name shouldBe "VH1 Divas Live"
                        venue shouldBe "New York's Beacon Theatre, US"
                    }
                    get(1).apply {
                        name shouldBe "Lollapalooza 2012"
                        venue shouldBe "Chicago’s Grant Park, US"
                    }
                }
            }
        val querySlot = slot<String>()
        val rowMapperSlot = slot<RowMapper<*>>()
        verify { jdbcTemplate.query(capture(querySlot), capture(rowMapperSlot)) }
        rowMapperSlot
            .shouldNotBeNull()
        querySlot.captured
            .shouldNotBeNull() shouldBe "select id, name, venue, local_date_time from concerts"
    }

    @Test
    fun `should update 2 records when updating 2 records`() {
        val concert1Update = arrayOf<Any>(
            "<HIDDEN1>",
            "New York's Beacon Theatre, US",
            LocalDateTime.of(1998, 4, 16, 0, 0, 0).toString(),
            1
        )
        val concert2Update =
            arrayOf<Any>("<HIDDEN2>", "Chicago’s Grant Park, US", LocalDateTime.of(2012, 8, 12, 16, 0, 0).toString(), 2)
        val concertsUpdate = listOf(concert1Update, concert2Update)
        val ints = springFlash24Launcher.testCRUDUpdate(concertsUpdate)
        val concerts = springFlash24Launcher.testCRUDRead()
        ints
            .shouldNotBeNull()
            .toList()
            .shouldHaveSize(2)
            .shouldContainOnly(1)
        concerts
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .toList()
            .apply {
                get(0)
                    .shouldNotBeNull()
                    .apply {
                        name shouldBe "<HIDDEN1>"
                        venue shouldBe "New York's Beacon Theatre, US"
                    }
                get(1)
                    .shouldNotBeNull()
                    .apply {
                        name shouldBe "<HIDDEN2>"
                        venue shouldBe "Chicago’s Grant Park, US"
                    }
            }

        val querySlot = mutableListOf<String>()
        val rowMapperSlot = mutableListOf<RowMapper<*>>()
        verify { jdbcTemplate.query(capture(querySlot), capture(rowMapperSlot)) }

        rowMapperSlot
            .shouldNotBeNull()
            .shouldNotBeEmpty()
            .shouldHaveSize(1)
        querySlot
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .toList()
            .forEach {
                it shouldBe "select id, name, venue, local_date_time from concerts"
            }
        verify {
            jdbcTemplate.batchUpdate(
                "UPDATE concerts set name = ?, venue = ?, local_date_time = ? WHERE id = ?",
                concertsUpdate
            )
        }
    }

    @Test
    fun `should read 1 when called`() {
        springFlash24Launcher.testCRUDRead()
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .toList()
            .apply {
                get(0).apply {
                    name shouldBe "VH1 Divas Live"
                    venue shouldBe "New York's Beacon Theatre, US"
                }
                get(1).apply {
                    name shouldBe "Lollapalooza 2012"
                    venue shouldBe "Chicago’s Grant Park, US"
                }
            }
        val querySlot = mutableListOf<String>()
        val rowMapperSlot = mutableListOf<RowMapper<*>>()
        verify { jdbcTemplate.query(capture(querySlot), capture(rowMapperSlot)) }
        rowMapperSlot
            .shouldNotBeNull()
        querySlot
            .shouldNotBeNull()
            .toList()[0] shouldBe "select id, name, venue, local_date_time from concerts"
    }

    @Test
    fun `should create 2 records when making two independent calls via batch`() {
        val concert1 = arrayOf(
            "VH1 Divas Live",
            "New York's Beacon Theatre, US",
            LocalDateTime.of(1998, 4, 16, 0, 0, 0).toString()
        )
        val concert2 =
            arrayOf("Lollapalooza 2012", "Chicago’s Grant Park, US", LocalDateTime.of(2012, 8, 12, 16, 0, 0).toString())
        val ints = springFlash24Launcher.textCRUDCreate(
            listOf(
                concert1, concert2
            )
        )
        verify {
            jdbcTemplate.batchUpdate(
                "insert into concerts(name, venue, local_date_time) values (?,?,?)",
                listOf(concert1, concert2)
            )
        }
        ints
            .shouldNotBeNull()
            .toList()
            .shouldHaveSize(2)
            .shouldContainOnly(1)
    }


    @Test
    fun `should throuw exception and then try to execute a fake query`() {
        shouldThrow<DataAccessException> { springFlash24Launcher.testException() }
        val slotExceptionTranslator = slot<SQLExceptionTranslator>()
        val slotQuery = mutableListOf<String>()
        verify { jdbcTemplate.exceptionTranslator = capture(slotExceptionTranslator) }
        verify { jdbcTemplate.execute(capture(slotQuery)) }
        slotExceptionTranslator.captured.shouldNotBeNull()
        slotQuery.shouldNotBeNull().toList().last() shouldBe "This is not and will never be a query"
    }

    companion object {
        @Container
        var postgreSQLContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:14")
            .withUsername("postgres")
            .withPassword("admin")
            .withDatabaseName("db")
    }
}