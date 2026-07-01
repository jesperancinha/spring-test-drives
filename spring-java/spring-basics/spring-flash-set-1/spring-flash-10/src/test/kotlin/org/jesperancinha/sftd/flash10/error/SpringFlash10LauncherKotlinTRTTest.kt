package org.jesperancinha.sftd.flash10.error

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.string.shouldContain
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("prod")
@SpringBootTest(webEnvironment = RANDOM_PORT)
internal class SpringFlash10LauncherKotlinTRTTest @Autowired constructor(
    private val testRestTemplate: TestRestTemplate
) {

    @Test
    fun `should test context`() {
    }

    @Test
    @Throws(Exception::class)
    fun `should go to error page when page gets called without authentication`() {
        testRestTemplate.getForEntity("/", String::class.java)
            .shouldNotBeNull()
            .body
            .shouldNotBeNull()
            .shouldContain("Hi there! You've been redirected to the error page!")
            .shouldContain("Here is some music to enjoy while we try to fix this!")
    }
}