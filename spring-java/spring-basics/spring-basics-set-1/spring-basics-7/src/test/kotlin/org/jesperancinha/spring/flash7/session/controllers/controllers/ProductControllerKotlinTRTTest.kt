package org.jesperancinha.spring.flash7.session.controllers.controllers

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations.openMocks
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = RANDOM_PORT)
internal class ProductControllerKotlinTRTTest @Autowired constructor(
    private val testRestTemplate: TestRestTemplate
) {
    @BeforeEach
    fun setUp() {
        openMocks(testRestTemplate)
    }

    @Test
    @Throws(Exception::class)
    fun `should get tulips in the general endpoint`(): Unit {
        testRestTemplate
            .getForEntity<String>("/tulips")
            .shouldNotBeNull()
            .apply { statusCode shouldBe HttpStatus.OK }
            .shouldNotBeNull()
            .body
            .shouldNotBeNull() shouldBe "You just got a bunch of tulips!"
    }


    @Test
    @Throws(Exception::class)
    fun `should get tulips in the general endpoint, but in the ok it should get an error message`() {
        testRestTemplate
            .getForEntity<String>("/tulips/ok")
            .shouldNotBeNull()
            .apply { statusCode shouldBe HttpStatus.OK }
            .shouldNotBeNull()
            .body
            .shouldNotBeNull()
    }
}