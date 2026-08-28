package org.jesperancinha.spring.flash13.gzip

import io.kotest.matchers.shouldBe
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.resttestclient.TestRestTemplate
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate
import org.springframework.boot.resttestclient.exchange
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod.GET
import java.io.ByteArrayInputStream
import java.util.zip.GZIPInputStream

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureTestRestTemplate
internal class SpringFlash13LauncherITKotlinTest @Autowired constructor(
    private val restTemplate: TestRestTemplate,
) {

    @field:LocalServerPort
    var port: Long = -1L

    @Test
    fun `should test context`() {
    }

    @Test
    fun `should get normal values when performing normal requests`() {
        val headers = HttpHeaders()
        val request = HttpEntity<Any>(headers)
        val response = restTemplate.exchange<ByteArray>(
            "http://localhost:$port/flash13.styles.css.gz", GET,
            request
        )
        val directGZipValueString = response.body?.let {
            GZIPInputStream(ByteArrayInputStream(it)).use { gzip -> gzip.readBytes().toString(Charsets.UTF_8) }
        }
        val headers2 = HttpHeaders()
        headers2["Accept-Encoding"] = "gzip, deflate"
        val request2 = HttpEntity<Any>(headers2)
        val response2 = restTemplate.exchange<String>(
            "http://localhost:$port/flash13.styles.css", GET,
            request2
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