package org.jesperancinha.sftd.flash13.gzip

import io.kotest.matchers.shouldBe
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod.GET

@SpringBootTest(webEnvironment = RANDOM_PORT)
internal class SpringFlash13LauncherITKotlinTest @Autowired constructor(
    private val restTemplate: TestRestTemplate,
) {

    @LocalServerPort
    var port: Long = -1L

    @Test
    fun `should test context`() {
    }

    @Test
    fun `should get normal values when performing normal requests`() {
        val headers = HttpHeaders()
        val request = HttpEntity<Any>(headers)
        val response = restTemplate.exchange(
            "http://localhost:$port/flash13.styles.css.gz", GET,
            request, String::class.java
        )
        val directGZipValueString = response.body
        val headers2 = HttpHeaders()
        headers2["Accept-Encoding"] = "gzip, deflate"
        val request2 = HttpEntity<Any>(headers2)
        val response2 = restTemplate.exchange(
            "http://localhost:$port/flash13.styles.css", GET,
            request2, String::class.java
        )
        val gzipValueString = response2.body
        ConsolerizerComposer.outSpace()
            .green(directGZipValueString)
            .reset()
        ConsolerizerComposer.outSpace()
            .green(gzipValueString)
            .reset()
        directGZipValueString shouldBe gzipValueString
    }
}