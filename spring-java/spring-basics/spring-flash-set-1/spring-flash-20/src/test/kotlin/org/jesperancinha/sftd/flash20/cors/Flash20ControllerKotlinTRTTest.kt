package org.jesperancinha.sftd.flash20.cors

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.ImportResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ImportResource("classpath:WEB-INF/beans.xml")
internal class Flash20ControllerKotlinTRTTest @Autowired constructor(
    @Autowired
    private val testRestTemplate: TestRestTemplate
) {
    @Test
    @Throws(Exception::class)
    fun `should retrieve sentence in cors unprotected website`() {
        testRestTemplate.getForEntity("/cors", String::class.java)
            .apply {
                statusCode shouldBe HttpStatus.OK
                body
                    .shouldNotBeNull() shouldBe "When accessing from spring-flash-20, there should be no blocking to visualization"
            }
    }

    @Test
    @Throws(Exception::class)
    @Disabled("Cors isn't working with beans for the moment")
    fun `should fail when calls are different`() {

        val headers = HttpHeaders().apply {
            add("origin","http://thissiteissomethingcopletelydifferentlocalhost.com")
        }
        val entity = HttpEntity<String>(headers)
        testRestTemplate.exchange("/cors", HttpMethod.GET, entity, String::class.java)
            .apply {
                statusCode shouldBe HttpStatus.FORBIDDEN
                body
                    .shouldNotBeNull() shouldBe "Invalid CORS request"
            }
    }

    @Test
    @Throws(Exception::class)
    fun `should never fail in the always endpoint`() {
        testRestTemplate.getForEntity("/always", String::class.java)
            .apply {
                statusCode shouldBe HttpStatus.OK
                body
                    .shouldNotBeNull() shouldBe "This should be blocked from the opposite url."
            }
    }

    @Test
    @Throws(Exception::class)
    fun `should show sentence in the always protected website even from another origin`() {
        val headers = HttpHeaders().apply {
            add("origin","http://thissiteissomethingcopletelydifferentlocalhost.com")
        }
        val entity = HttpEntity<String>(headers)
        testRestTemplate.exchange("/always", HttpMethod.GET, entity, String::class.java)
            .apply {
                statusCode shouldBe HttpStatus.OK
                body
                    .shouldNotBeNull() shouldBe "This should be blocked from the opposite url."
            }
    }

    @Test
    @Throws(Exception::class)
    fun `should show sentence in protected website when calling from localhost`() {
        testRestTemplate.getForEntity("/protected", String::class.java)
            .apply {
                statusCode shouldBe HttpStatus.OK
                body
                    .shouldNotBeNull() shouldBe "When accessing from spring-flash-20, there should be no blocking to visualization"
            }
    }

    @Test
    @Throws(Exception::class)
    @Disabled("Cors isn't working with beans for the moment")
    fun `should fail when calling from another location`() {
        val headers = HttpHeaders().apply {
            add("origin","http://thissiteissomethingcopletelydifferentlocalhost.com")
        }
        val entity = HttpEntity<String>(headers)
        testRestTemplate.exchange("/protected", HttpMethod.GET, entity, String::class.java)
            .apply {
                statusCode shouldBe HttpStatus.FORBIDDEN
                body
                    .shouldNotBeNull() shouldBe "Invalid CORS request"
            }
    }
}

