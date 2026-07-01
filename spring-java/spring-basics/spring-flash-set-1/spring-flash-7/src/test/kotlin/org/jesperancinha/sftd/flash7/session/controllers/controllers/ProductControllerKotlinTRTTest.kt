package org.jesperancinha.sftd.flash7.session.controllers.controllers

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations.initMocks
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = RANDOM_PORT)
internal class ProductControllerKotlinTRTTest @Autowired constructor(
    @Autowired
    private val testRestTemplate: TestRestTemplate
) {
    @BeforeEach
    fun setUp() {
        initMocks(testRestTemplate)
    }

    @Test
    @Throws(Exception::class)
    fun `should get tulips in the general endpoint`() = testRestTemplate
        .getForEntity("/tulips", String::class.java)
        .shouldNotBeNull()
        .apply { statusCode shouldBe HttpStatus.OK }
        .shouldNotBeNull()
        .body
        .shouldNotBeNull() shouldBe "You just got a bunch of tulips!"

    @Test
    @Throws(Exception::class)
    fun `should get tulips in the general endpoint, but in the ok it should get an error message`() {
        testRestTemplate
            .getForEntity("/tulips/ok", String::class.java)
            .shouldNotBeNull()
            .apply { statusCode shouldBe HttpStatus.OK }
            .shouldNotBeNull()
            .body
            .shouldNotBeNull()
    }
}