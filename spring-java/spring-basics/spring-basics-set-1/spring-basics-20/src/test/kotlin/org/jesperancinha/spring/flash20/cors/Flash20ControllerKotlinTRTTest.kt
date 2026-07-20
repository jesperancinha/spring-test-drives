package org.jesperancinha.spring.flash20.cors

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.resttestclient.TestRestTemplate
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate
import org.springframework.boot.resttestclient.exchange
import org.springframework.boot.resttestclient.getForEntity
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.context.annotation.ImportResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureTestRestTemplate
@ImportResource("classpath:WEB-INF/beans.xml")
internal class Flash20ControllerKotlinTRTTest @Autowired constructor(
    private val testRestTemplate: TestRestTemplate
) {
    @Test
    @Throws(Exception::class)
    fun `should retrieve sentence in cors unprotected website`() {
        testRestTemplate.getForEntity<String>("/cors")
            .apply {
                statusCode shouldBe HttpStatus.OK
                body
                    .shouldNotBeNull() shouldBe "When accessing from spring-basics-20, there should be no blocking to visualization"
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
        testRestTemplate.exchange<String>("/cors", HttpMethod.GET, entity)
            .apply {
                statusCode shouldBe HttpStatus.FORBIDDEN
                body
                    .shouldNotBeNull() shouldBe "Invalid CORS request"
            }
    }

    @Test
    @Throws(Exception::class)
    fun `should never fail in the always endpoint`() {
        testRestTemplate.getForEntity<String>("/always")
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
        testRestTemplate.exchange<String>("/always", HttpMethod.GET, entity)
            .apply {
                statusCode shouldBe HttpStatus.OK
                body
                    .shouldNotBeNull() shouldBe "This should be blocked from the opposite url."
            }
    }

    @Test
    @Throws(Exception::class)
    fun `should show sentence in protected website when calling from localhost`() {
        testRestTemplate.getForEntity<String>("/protected")
            .apply {
                statusCode shouldBe HttpStatus.OK
                body
                    .shouldNotBeNull() shouldBe "When accessing from spring-basics-20, there should be no blocking to visualization"
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
        testRestTemplate.exchange<String>("/protected", HttpMethod.GET, entity)
            .apply {
                statusCode shouldBe HttpStatus.FORBIDDEN
                body
                    .shouldNotBeNull() shouldBe "Invalid CORS request"
            }
    }
}

