package org.jesperancinha.spring.tutorial.persistence.rest

import org.jesperancinha.spring.tutorial.persistence.rest.controller.TimeController
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest(webEnvironment = RANDOM_PORT)
class SpringTutorialKotlinLauncherTest {
    private val mockMvc: MockMvc by lazy {  MockMvcBuilders.standaloneSetup(TimeController()).build() }

    @Test
    fun `should be able to load application context`() {
    }

    @Test
    fun `should get current date with no errors when requested`() {
        mockMvc.perform(
            post("/time")
                .contentType("application/text")
                .header("currentTime", "1999-12-01")
        )
            .andExpect(status().isOk)
    }

    @Test
    fun `should get current time with no errors when requested`() {
        mockMvc.perform(
            post("/time/time")
                .contentType("application/text")
                .header("currentTime", "1999-12-01 12:30:31")
        )
            .andExpect(status().isOk)
    }

    @Test
    fun `should get thousand dollars with no errors when requested`() {
        mockMvc.perform(
            post("/time/dollars")
                .contentType("application/text")
                .header("dollars", "150")
        )
            .andExpect(status().isOk)
    }
}