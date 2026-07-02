package org.jesperancinha.sftd.flash8.jsp

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [ViewController::class])
internal class ViewControllerKotlinTest @Autowired constructor(
    private val mockMvc: MockMvc
) {
    @Test
    @Throws(Exception::class)
    fun `should get notification when getting main page`() {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk)
            .andReturn()
            .shouldNotBeNull()
            .modelAndView
            .shouldNotBeNull()
            .apply {
                viewName
                    .shouldNotBeNull()
                    .shouldBe("index")
                model
                    .shouldNotBeNull()["notification"]
                    .shouldNotBeNull() shouldBe "All I want for christmas is you!"
            }
    }
}