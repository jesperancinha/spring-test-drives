package org.jesperancinha.sftd.flash7.session.controllers.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import io.kotest.matchers.types.shouldBeInstanceOf
import org.jesperancinha.sftd.flash7.session.controllers.ProductController
import org.jesperancinha.sftd.flash7.session.handlers.ErrorCar
import org.jesperancinha.sftd.flash7.session.handlers.ErrorFlower
import org.jesperancinha.sftd.flash7.session.handlers.MixErrorMessage
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations.initMocks
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [ProductController::class])
internal class ProductControllerKotlinTest @Autowired constructor(
    @Autowired
    private val mockMvc: MockMvc
) {

    private val objectMapper by lazy { ObjectMapper() }

    @BeforeEach
    fun setUp() {
        initMocks(mockMvc)
    }

    @Test
    @Throws(Exception::class)
    fun `should get tulips in the general endpoint`() = mockMvc
        .perform(get("/tulips"))
        .andExpect(status().isOk)
        .andReturn()
        .shouldNotBeNull()
        .response
        .shouldNotBeNull()
        .contentAsString
        .shouldNotBeNull() shouldBe "You just got a bunch of tulips!"

    @Test
    @Throws(Exception::class)
    fun `should get tulips in the general endpoint, but in the ok it should get an error message`() {
        mockMvc.perform(get("/tulips/ok"))
            .andExpect(status().isOk)
            .andReturn()
            .shouldNotBeNull()
            .response
            .shouldNotBeNull()
            .apply {
                contentAsString.shouldBeEmpty()
                errorMessage shouldBe "This is an error, but it's ok"
            }
    }

    @Test
    @Throws(Exception::class)
    fun `should get a real service unavailable error`() {
        mockMvc.perform(get("/tulips/error"))
            .andExpect(status().isServiceUnavailable)
            .andReturn()
            .shouldNotBeNull()
            .response
            .shouldNotBeNull()
            .apply {
                contentAsString.shouldBeEmpty()
                errorMessage shouldBe "We are not available at the moment!"
            }
    }

    @Test
    @Throws(Exception::class)
    fun `should result in an error when calling the generic rose endpoint`() {
        mockMvc.perform(get("/flowers/rose"))
            .andExpect(status().isNotFound)
            .andReturn()
            .shouldNotBeNull()
            .response
            .shouldNotBeNull()
            .apply {
                contentAsString.shouldNotBeNull()
                    .let { objectMapper.readValue(it, ErrorFlower::class.java) }
                    .shouldNotBeNull()
                    .apply {
                        status shouldBe NOT_FOUND.value()
                        errorMessage.shouldBeNull()
                    }
                errorMessage.shouldBeNull()

            }
    }

    @Test
    @Throws(Exception::class)
    fun `should result in no error when calling the generic kitt endpoint`() {
        mockMvc.perform(get("/cars/kitt"))
            .andExpect(status().isNotFound)
            .andReturn()
            .shouldNotBeNull()
            .response
            .shouldNotBeNull()
            .apply {
                contentAsString.shouldNotBeNull()
                    .let { objectMapper.readValue(contentAsString, ErrorCar::class.java) }
                    .apply {
                        status shouldBe NOT_FOUND.value()
                        message.shouldNotBeNull()
                    }
                errorMessage.shouldBeNull()

            }
    }

    @Test
    @Throws(Exception::class)
    fun `should get an error when calling the local rose endpoint`() {
        mockMvc.perform(get("/flowers/local/rose"))
            .andExpect(status().isOk)
            .andReturn()
            .shouldNotBeNull()
            .let {
                it.response
                    .shouldNotBeNull()
                    .apply {
                        contentAsString
                            .shouldNotBeNull()
                            .shouldBeEmpty()
                        errorMessage.shouldBeNull()
                    }
                it.modelAndView
                    .shouldNotBeNull()
                    .apply {
                        viewName.shouldNotBeNull()
                        viewName shouldBe "errorpage"
                    }.model.shouldNotBeNull()
                    .let { model ->
                        model["fail"]
                            .apply {
                                shouldNotBeNull()
                                shouldBeInstanceOf<ErrorFlower>()
                                httpStatus shouldBe NOT_FOUND
                                message.shouldNotBeNull()
                            }
                    }
            }
    }

    @Test
    @Throws(Exception::class)
    fun `should get an error when performing a request to the local car kitt endpoint`() {
        mockMvc.perform(get("/cars/local/kitt"))
            .andExpect(status().isOk)
            .andReturn()
            .shouldNotBeNull()
            .let {
                it.response
                    .shouldNotBeNull()
                    .apply {
                        contentAsString
                            .shouldNotBeNull()
                            .shouldBeEmpty()
                        errorMessage.shouldBeNull()
                    }
                it.modelAndView
                    .shouldNotBeNull()
                    .apply {
                        viewName.shouldNotBeNull()
                        viewName shouldBe "errorpage"
                    }.model.shouldNotBeNull()
                    .let { model ->
                        model["fail"]
                            .apply {
                                shouldNotBeNull()
                                shouldBeInstanceOf<ErrorCar>()
                                message.shouldNotBeNull()
                            }
                    }
            }

    }

    @Test
    @Throws(Exception::class)
    fun `should get a pottery error when calling the pottery endpoint`() {
        mockMvc.perform(get("/pottery/amphora"))
            .andExpect(status().isNotFound)
            .andReturn()
            .shouldNotBeNull()
            .response
            .shouldNotBeNull()
            .apply {
                contentAsString.shouldNotBeNull()
                    .let { objectMapper.readValue(it, MixErrorMessage::class.java) }
                    .shouldNotBeNull()
                    .message
                    .shouldNotBeNull()
                errorMessage.shouldBeNull()
            }
    }

    @Test
    @Throws(Exception::class)
    fun `should get mix response when calling fourheels error endpoint`() {
        mockMvc.perform(get("/fourwheels/rover"))
            .andExpect(status().isNotFound)
            .andReturn()
            .shouldNotBeNull()
            .response
            .shouldNotBeNull()
            .apply {
                contentAsString.shouldNotBeNull()
                    .let { objectMapper.readValue(it, MixErrorMessage::class.java) }
                    .shouldNotBeNull()
                    .message.shouldNotBeNull()
                errorMessage.shouldBeNull()
            }
    }
}