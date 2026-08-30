package org.jesperancinha.spring.flash20.cors

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.context.annotation.ImportResource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(Flash20Controller::class)
@ImportResource("classpath:WEB-INF/beans.xml")
internal class Flash20ControllerKotlinTest @Autowired constructor(
    private val mockMvc: MockMvc
) {

    @Test
    @Throws(Exception::class)
    fun `should retrieve sentence in cors unprotected website`() {
        mockMvc.perform(get("/cors"))
            .andExpect(status().isOk)
            .andExpect(
                content()
                    .string("When accessing from spring-basics-20, there should be no blocking to visualization")
            )
    }

    @Test
    @Throws(Exception::class)
    fun `should fail when calls are different`() {
        mockMvc.perform(
            get("/cors")
                .header("origin", "http://thissiteissomethingcopletelydifferentlocalhost.com")
        )
            .andExpect(status().isForbidden)
            .andExpect(content().string("Invalid CORS request"))
    }

    @Test
    @Throws(Exception::class)
    fun `should never fail in the always endpoint`() {
        mockMvc.perform(get("/always"))
            .andExpect(status().isOk)
            .andExpect(content().string("This should be blocked from the opposite url."))
    }

    @Test
    @Throws(Exception::class)
    fun `should show sentence in the always protected website even from another origin`() {
        mockMvc.perform(
            get("/always")
                .header("origin", "http://thissiteissomethingcopletelydifferentlocalhost.com")
        )
            .andExpect(status().isOk)
            .andExpect(content().string("This should be blocked from the opposite url."))
    }

    @Test
    @Throws(Exception::class)
    fun `should show sentence in protected website when calling from localhost`() {
        mockMvc.perform(get("/protected"))
            .andExpect(status().isOk)
            .andExpect(
                content()
                    .string("When accessing from spring-basics-20, there should be no blocking to visualization")
            )
    }

    @Test
    @Throws(Exception::class)
    fun `should fail when calling from another location`() {
        mockMvc.perform(
            get("/protected")
                .header("origin", "http://thissiteissomethingcopletelydifferentlocalhost.com")
        )
            .andExpect(status().isForbidden)
            .andExpect(content().string("Invalid CORS request"))
    }
}