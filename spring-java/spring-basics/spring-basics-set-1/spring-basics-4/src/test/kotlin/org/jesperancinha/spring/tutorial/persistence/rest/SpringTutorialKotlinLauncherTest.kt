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
internal class SpringTutorialKotlinLauncherTest {
    private val mockMvc: MockMvc by lazy {  MockMvcBuilders.standaloneSetup(TimeController()).build() }

    @Test
    fun testCurrentDateWhenRequest_noErrors() {
        mockMvc.perform(
            post("/time")
                .contentType("application/text")
                .header("currentTime", "1999-12-01")
        )
            .andExpect(status().isOk)
    }

    @Test
    fun testCurrentTimeWhenRequest_noErrors() {
        mockMvc.perform(
            post("/time/time")
                .contentType("application/text")
                .header("currentTime", "1999-12-01 12:30:31")
        )
            .andExpect(status().isOk)
    }

    @Test
    fun thousandDollars_whenRequest_noErrors() {
        mockMvc.perform(
            post("/time/dollars")
                .contentType("application/text")
                .header("dollars", "150")
        )
            .andExpect(status().isOk)
    }
}