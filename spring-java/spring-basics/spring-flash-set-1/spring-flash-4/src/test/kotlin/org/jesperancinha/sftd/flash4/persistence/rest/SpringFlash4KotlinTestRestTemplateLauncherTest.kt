package org.jesperancinha.sftd.flash4.persistence.rest

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.springframework.http.HttpMethod.POST

@SpringBootTest(webEnvironment = RANDOM_PORT)
internal class SpringFlash4KotlinTestRestTemplateLauncherTest @Autowired constructor(
    val testRestTemplate: TestRestTemplate
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
        testRestTemplate.exchange("/", POST, entity, String::class.java)
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
        testRestTemplate.exchange("/time", POST, entity, String::class.java)
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
        testRestTemplate.exchange("/dollars", POST, entity, String::class.java)
            .shouldNotBeNull()
            .apply {
                statusCode shouldBe HttpStatus.OK
                body.shouldNotBeNull()
            }
    }
}