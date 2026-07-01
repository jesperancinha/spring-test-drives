package org.jesperancinha.sftd.flash26.jdbc.handlers

import com.ninjasquad.springmockk.SpykBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainOnly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.UncategorizedSQLException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.jdbc.core.RowMapper

@SpringBootTest
internal class SpringFlash26LauncherKotlinTest(
    @Autowired
    private val springFlash26Launcher: SpringFlash26Launcher
) {
    @SpykBean
    lateinit var jdbcTemplate: JdbcTemplate

    @BeforeEach
    fun setUp() {
        springFlash26Launcher.initializeDatabase()
        val data = listOf(
            arrayOf<Any>("Berbigão", "Cerastoderma edule", "white"),
            arrayOf<Any>("Conquilha", "Donax trunculus", "blue")
        )
        springFlash26Launcher.insertData(data)
    }

    @Test
    fun `should load context`() {
    }

    @Test
    fun `should return results when making requests with row mapper`() {
        springFlash26Launcher.testRowMapper()
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .toList()
            .apply {
                get(0)
                    .shouldNotBeNull()
                    .apply {
                        name shouldBe "Berbigão"
                        scientificName shouldBe "Cerastoderma edule"
                        predominantColor shouldBe "white"
                    }
                get(1)
                    .shouldNotBeNull()
                    .apply {
                        name shouldBe "Conquilha"
                        scientificName shouldBe "Donax trunculus"
                        predominantColor shouldBe "blue"
                    }
            }

        val slotQuery = mutableListOf<String>()
        val slotRowMapper = mutableListOf<RowMapper<*>>()

        verify { jdbcTemplate.query(capture(slotQuery), capture(slotRowMapper)) }

        slotQuery
            .shouldNotBeNull()
            .shouldNotBeEmpty()
            .toList().last() shouldBe "select name, scientificName, predominentColor from shells"
        slotRowMapper
            .shouldNotBeNull()
    }

    @Test
    fun `should test result set extractor en return ok`() {
       springFlash26Launcher.testResultSetExtractor()
           .shouldNotBeNull() shouldBe "OK!"

        val slotQuery = mutableListOf<String>()
        val slotRowMapper = mutableListOf<ResultSetExtractor<*>>()

        verify { jdbcTemplate.query(capture(slotQuery), capture(slotRowMapper)) }

        slotQuery
            .shouldNotBeNull()
            .shouldNotBeEmpty()
            .toList().last() shouldBe "select name, scientificName, predominentColor from shells"
        slotRowMapper
            .shouldNotBeNull()
    }

    @Test
    fun `should test batch update`() {
        val data = listOf(
            arrayOf<Any>("Berbigão", "Cerastoderma edule", "white"),
            arrayOf<Any>("Conquilha", "Donax trunculus", "blue")
        )
        springFlash26Launcher.insertData(data)
            .shouldNotBeNull()
            .toList()
            .shouldContainOnly(1)


        verify {jdbcTemplate.batchUpdate("insert into shells(name, scientificName, predominentColor) values (?,?,?)", data)}
    }

    @Test
    fun `should test result set extractor and throw exception when input is empty`() {
        shouldThrow<UncategorizedSQLException>{ springFlash26Launcher.testNoResultQueryOnResultSetExtractor() }

        val slotQuery = mutableListOf<String>()
        val slotResultSetExtractor = mutableListOf<ResultSetExtractor<*>>()

        verify {jdbcTemplate.query(capture(slotQuery), capture(slotResultSetExtractor)) }
        slotQuery
            .shouldNotBeEmpty()
            .first() shouldBe "create table shells(id serial, name varchar(255), scientificName varchar(255), predominentColor varchar(255))"

        slotResultSetExtractor
            .shouldNotBeNull()
    }
}