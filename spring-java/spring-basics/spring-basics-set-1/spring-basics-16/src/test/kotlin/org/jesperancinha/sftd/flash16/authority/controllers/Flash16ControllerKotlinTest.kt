package org.jesperancinha.sftd.flash16.authority.controllers

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(Flash16Controller::class)
internal class Flash16ControllerKotlinTest @Autowired constructor(
    private val mockMvc: MockMvc
){

    @Test
    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Throws(Exception::class)
    fun `should respond Ok to generic request on request handler`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/userdata.jsp"))
            .andExpect(MockMvcResultMatchers.view().name("userdata"))
            .andExpect(MockMvcResultMatchers.model().attribute("name", "admin"))
            .andExpect(
                MockMvcResultMatchers.model().attribute(
                    "roles", listOf(
                        SimpleGrantedAuthority("ROLE_ADMIN")
                    )
                )
            )
    }

    @Test
    @WithMockUser(roles = ["ADMIN"])
    @Throws(Exception::class)
    fun `should respond Ok when performing authenticated request through request handler`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/userdata.jsp"))
    }
}