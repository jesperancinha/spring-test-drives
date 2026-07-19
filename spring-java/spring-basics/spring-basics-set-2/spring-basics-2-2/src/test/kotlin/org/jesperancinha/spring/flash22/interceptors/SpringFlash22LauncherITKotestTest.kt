package org.jesperancinha.spring.flash22.interceptors

import com.ninjasquad.springmockk.MockkSpyBean
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.verify
import org.jesperancinha.spring.flash22.interceptors.beans.FeelingLoveBean
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(controllers = [SpringFlash22Launcher::class])
@Import(FeelingLoveBean::class)
internal class SpringFlash22LauncherITKotestTest @Autowired constructor(
    private val mockMvc: MockMvc,
    @MockkSpyBean
    private val feelingLoveBean: FeelingLoveBean,
) {

    @Test
    fun main() {
    }

    @Test
    @Throws(Exception::class)
    fun testGetStringWhenCalledThenTriggerInterceptors() {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
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
                    .toString() shouldBe "org.jesperancinha.spring.flash22.interceptors.SpringFlash22Launcher#getString()"
            }
    }
}