package org.jesperancinha.sftd.flash12.cors

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(controllers = [Flash12Controller::class])
internal class Flash12ControllerKotlinTest @Autowired constructor(
    private val mockMvc: MockMvc
) {

    @Test
    @Throws(Exception::class)
    fun `should show sentence when called`() {
        mockMvc
            .perform(get("/cors"))
            .andExpect(status().isOk)
            .andExpect(
                content()
                    .string("When accessing from spring-flash-12, there should be no blocking to visualization")
            )
    }

    @Test
    @Throws(Exception::class)
    fun `should show invalid CORS request when sending from different origin`() {
        mockMvc
            .perform(
            get("/cors")
                .header("Origin", "http://thissiteissomethingcopletelydifferentlocalhost.com")
        )
            .andExpect(status().isForbidden)
            .andExpect(content().string("Invalid CORS request"))
    }

    @Test
    @Throws(Exception::class)
    fun `should always show the always endpoint`() {
        mockMvc.perform(get("/always"))
            .andExpect(status().isOk)
            .andExpect(content().string("This should be blocked from the opposite url."))
    }

    @Test
    @Throws(Exception::class)
    fun `should show always even from a different origin`() {
        mockMvc.perform(
            get("/always")
                .header("Origin", "http://thissiteissomethingcopletelydifferentlocalhost.com")
        )
            .andExpect(status().isOk)
            .andExpect(content().string("This should be blocked from the opposite url."))
    }

    @Test
    @Throws(Exception::class)
    fun `should show page when accessing the protected website from flash12`() {
        mockMvc.perform(get("/protected"))
            .andExpect(status().isOk)
            .andExpect(
                content()
                    .string("When accessing from spring-flash-12, there should be no blocking to visualization")
            )
    }

    @Test
    @Throws(Exception::class)
    fun `should have the site protectd and show invalid CORS request when origin is different`() {
        mockMvc.perform(
            get("/protected")
                .header("Origin", "http://thissiteissomethingcopletelydifferentlocalhost.com")
        )
            .andExpect(status().isForbidden)
            .andExpect(content().string("Invalid CORS request"))
    }
}