package org.jesperancinha.sftd.flash14.health

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.string.shouldContain
import org.apache.commons.io.IOUtils
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatusCode
import org.springframework.http.client.ClientHttpResponse
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.DefaultResponseErrorHandler
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate
import java.io.IOException
import java.net.URI
import java.nio.charset.Charset

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = [Flash14HealthIndicator::class])
@EnableAutoConfiguration
internal class Flash14HealthIndicatorKotlinTest {
    @LocalServerPort
    private val port: Long? = null
    private val restTemplate = RestTemplate()
    @Test
    fun `should show a positive health check`() {
        val url = "http://localhost:$port/actuator/health"
        val headers = HttpHeaders()
        val request = HttpEntity<Any>(headers)
        restTemplate.errorHandler = object : DefaultResponseErrorHandler() {
            override fun handleError(
                response: ClientHttpResponse,
                statusCode: HttpStatusCode,
                url: URI?,
                method: HttpMethod?
            ) {
                IOUtils.toString(response.body, Charset.defaultCharset())
                    .shouldNotBeNull()
                    .shouldContain("\"lyric\":\"A chance for calm, A hope for freedom\"")
                ConsolerizerComposer.outSpace()
                    .green("All assertions complete!")
                    .reset()
                super.handleError(response, statusCode, url, method)
            }
        }
       shouldThrow<HttpServerErrorException> {
            restTemplate.exchange(
                url, HttpMethod.GET,
                request, String::class.java
            )
        }
    }
}