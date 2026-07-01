package org.jesperancinha.sftd.flash23.xml.interceptors

import com.ninjasquad.springmockk.SpykBean
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.verify
import org.jesperancinha.sftd.flash23.xml.interceptors.beans.FeelingLoveBean
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ImportResource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(SpringFlash23Launcher::class)
@ImportResource("classpath:WEB-INF/beans.xml")
internal class SpringFlash23LauncherKotlinITTest @Autowired constructor(
    private val mockMvc: MockMvc
) {

    @SpykBean
    lateinit var feelingLoveBean: FeelingLoveBean

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
                    .toString() shouldBe "org.jesperancinha.sftd.flash23.xml.interceptors.SpringFlash23Launcher#getString()"
            }
    }
}