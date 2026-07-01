package org.jesperancinha.sftd.flash6.session

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.ints.shouldBeBetween
import io.kotest.matchers.longs.shouldBeBetween
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import jakarta.servlet.http.HttpSession
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get


@WebMvcTest(controllers = [SpringFlash6Launcher::class])
internal class SpringFlash6KotlinLauncherTest @Autowired constructor(
    @Autowired
    private val mockMvc: MockMvc
) {

    @Test
    @Throws(Exception::class)
    fun testShowSessionDetailsWhenCalledThenTopListWithNumbers() {
        val objectMapper = ObjectMapper()
        val mvcResult: MvcResult = mockMvc.perform(get("/"))
            .andReturn()
        mvcResult
            .shouldNotBeNull()
            .response
            .shouldNotBeNull()
            .contentAsString
            .shouldNotBeNull()
            .let { objectMapper.readValue(it, Array<Long>::class.java) }
            .toList()
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .first().shouldBeBetween(0L, 1000L)
    }

    @Test
    fun testGenerateListWhenCreateThenAddAnotherNumber() {
        val app = SpringFlash6Launcher()
        val session: HttpSession = mockk()
        val numberList: ArrayList<Int> = ArrayList()
        every { session.getAttribute("numberList") } returns numberList
        val intCollection = app.generateList(session)
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .apply {
                first().shouldNotBeNull()
                    .shouldBeBetween(0, 1000)
            }
        app.generateList(session)
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .let {
                it.first() shouldBe intCollection.toList()[0]
                it.toList()[1].shouldBeBetween(0, 1000)
            }
    }
}