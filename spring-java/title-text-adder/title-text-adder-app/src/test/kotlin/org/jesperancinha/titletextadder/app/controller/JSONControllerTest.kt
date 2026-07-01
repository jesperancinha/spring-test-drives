package org.jesperancinha.titletextadder.app.controller

import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class JSONControllerTest @Autowired constructor(
    val jsonController: JSONController
) {
    @Test
    fun `show title in json form`() {
        jsonController.getTitleInJSON("title")
            .should {
                it.id shouldBe "1"
                it.title shouldBe "This is an endpoint check title"
                it.text shouldBe "This is the expected text"
                it.score shouldBe 1
            }
    }
}