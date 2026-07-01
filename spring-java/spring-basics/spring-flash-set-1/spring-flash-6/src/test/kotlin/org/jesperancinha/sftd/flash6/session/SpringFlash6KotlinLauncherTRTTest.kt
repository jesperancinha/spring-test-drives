package org.jesperancinha.sftd.flash6.session

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
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate


@SpringBootTest(webEnvironment = RANDOM_PORT)
internal class SpringFlash6KotlinLauncherTRTTest @Autowired constructor(
    private val testRestTemplate: TestRestTemplate
) {

    @Test
    @Throws(Exception::class)
    fun testShowSessionDetailsWhenCalledThenTopListWithNumbers() {
        testRestTemplate.getForEntity("/", Array<Long>::class.java)
            .shouldNotBeNull()
            .body
            .shouldNotBeNull()
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