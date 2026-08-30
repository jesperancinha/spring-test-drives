package org.jesperancinha.spring.tutorial.interceptors

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.verify
import org.jesperancinha.spring.tutorial.beans.FeelingLoveBean
import org.jesperancinha.spring.tutorial.configuration.InterceptorConfiguration
import org.jesperancinha.spring.tutorial.controller.WineController
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(controllers = [WineController::class])
@ContextConfiguration(classes = [WineController::class, InterceptorConfiguration::class])
internal class InterceptorsKotestTest @Autowired constructor(
    private val mockMvc: MockMvc,
    @MockkBean(relaxed = true)
    private val feelingLoveBean: FeelingLoveBean,
) {

    @Test
    fun `should run main launcher`() {
    }

    @Test
    @Throws(Exception::class)
    fun `should trigger interceptor when get string is called`() {
        every { feelingLoveBean.preHandle(any()) } returns true
        mockMvc.perform(MockMvcRequestBuilders.get("/wine"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("Fine Wine"))
        val handlers = mutableListOf<Any?>()
        verify { feelingLoveBean.preHandle(captureNullable(handlers)) }
        verify { feelingLoveBean.postHandle(captureNullable(handlers), isNull()) }
        verify { feelingLoveBean.afterCompletion(captureNullable(handlers), isNull()) }
        handlers
            .shouldNotBeNull()
            .shouldNotBeEmpty()
            .shouldHaveSize(3)
            .forEach {
                it.shouldNotBeNull()
                    .toString() shouldBe "org.jesperancinha.spring.tutorial.controller.WineController#getString()"
            }
    }
}