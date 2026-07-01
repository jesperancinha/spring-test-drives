package org.jesperancinha.sftd.flash220.allparams

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
internal class SpringFlash220LauncherMvcKotlinTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @Test
    @Throws(Exception::class)
    fun `should post all sins in a matrix and get all sins as response`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/matrix/wow;sin1=Lust;sin2=Gluttony;sin3=Greed;sin4=Sloth;sin5=Wrath;sin6=Envy;sin7=Pride")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("You are wise!"))
    }

    @Test
    @Throws(Exception::class)
    fun `should post all sins in the headers and get all sins as response`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/request/Lust/Gluttony?sin3=Greed&sin4=Sloth")
                .header("sin5", "Wrath")
                .header("sin6", "Envy")
                .header("sin7", "Pride")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("You are wise!"))
    }
}