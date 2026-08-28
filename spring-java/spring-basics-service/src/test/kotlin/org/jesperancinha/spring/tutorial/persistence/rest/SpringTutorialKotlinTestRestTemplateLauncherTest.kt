package org.jesperancinha.spring.tutorial.persistence.rest

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.ints.shouldBeBetween
import io.kotest.matchers.longs.shouldBeBetween
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import jakarta.servlet.http.HttpSession
import org.jesperancinha.spring.tutorial.persistence.rest.controller.SessionController
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.resttestclient.TestRestTemplate
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate
import org.springframework.boot.resttestclient.exchange
import org.springframework.boot.resttestclient.getForEntity
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureTestRestTemplate
internal class SpringTutorialKotlinTestRestTemplateLauncherTest @Autowired constructor(
    private val testRestTemplate: TestRestTemplate
) {
    @Test
    @Throws(Exception::class)
    fun `should test current date with no errors`() {
        val headers = HttpHeaders()
            .apply {
                add("content-type", "application/text")
                add("currentTime", "1999-12-01")
            }
        val entity = HttpEntity<String>(headers)
        testRestTemplate.exchange<String>("/time", POST, entity)
            .shouldNotBeNull()
            .apply {
                statusCode shouldBe HttpStatus.OK
                body.shouldNotBeNull()
            }
    }

    @Test
    @Throws(Exception::class)
    fun `should test current time with no errors`() {
        val headers = HttpHeaders()
            .apply {
                add("content-type", "application/text")
                add("currentTime", "1999-12-01 12:30:31")
            }
        val entity = HttpEntity<String>(headers)
        testRestTemplate.exchange<String>("/time/time", POST, entity)
            .shouldNotBeNull()
            .apply {
                statusCode shouldBe HttpStatus.OK
                body.shouldNotBeNull()
            }
    }

    @Test
    @Throws(Exception::class)
    fun `should test thousands of dollars with no errors`() {
        val headers = HttpHeaders()
            .apply {
                add("content-type", "application/text")
                add("dollars", "150")
            }
        val entity = HttpEntity<String>(headers)
        testRestTemplate.exchange<String>("/time/dollars", POST, entity)
            .shouldNotBeNull()
            .apply {
                statusCode shouldBe HttpStatus.OK
                body.shouldNotBeNull()
            }
    }

    @Test
    @Throws(Exception::class)
    fun `should show session details with top list numbers when called`() {
        testRestTemplate.getForEntity<Array<Long>>("/session")
            .shouldNotBeNull()
            .body
            .shouldNotBeNull()
            .toList()
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .first().shouldBeBetween(0L, 1000L)
    }

    @Test
    fun `should add another number when creating list`() {
        val app = SessionController()
        val session: HttpSession = mockk()
        val numberList: ArrayList<Int> = ArrayList()
        every { session.getAttribute("numberList") } returns numberList
        val intCollection = app.generateList(session)
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .apply {
                first().shouldNotBeNull()
                    .shouldBeBetween(0, 1000)
            }
        app.generateList(session)
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .let {
                it.first() shouldBe intCollection.toList()[0]
                it.toList()[1].shouldBeBetween(0, 1000)
            }
    }
}