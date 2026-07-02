package org.jesperancinha.sftd.flash4.persistence.rest

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest(webEnvironment = RANDOM_PORT)
internal class SpringFlash4KotlinLauncherTest {
    private val mockMvc: MockMvc by lazy {  MockMvcBuilders.standaloneSetup(SpringFlash4Launcher()).build() }

    @Test
    @Throws(Exception::class)
    fun testCurrentDateWhenRequest_noErrors() {
        mockMvc.perform(
            post("/")
                .contentType("application/text")
                .header("currentTime", "1999-12-01")
        )
            .andExpect(status().isOk)
    }

    @Test
    @Throws(Exception::class)
    fun testCurrentTimeWhenRequest_noErrors() {
        mockMvc.perform(
            post("/time")
                .contentType("application/text")
                .header("currentTime", "1999-12-01 12:30:31")
        )
            .andExpect(status().isOk)
    }

    @Test
    @Throws(Exception::class)
    fun thousandDollars_whenRequest_noErrors() {
        mockMvc.perform(
            post("/dollars")
                .contentType("application/text")
                .header("dollars", "150")
        )
            .andExpect(status().isOk)
    }
}