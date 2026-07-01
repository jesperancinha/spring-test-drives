package org.jesperancinha.sftd.flash17.cipher.controllers

import com.ninjasquad.springmockk.MockkBean
import com.ninjasquad.springmockk.SpykBean
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.slot
import io.mockk.verify
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.sftd.flash17.cipher.configuration.Flash17CSRFConfigurationAdapter
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import javax.sql.DataSource

@ActiveProfiles("test")
@WebMvcTest(Flash17CSRFCreateController::class, Flash17Controller::class)
@MockkBean(classes = [DataSource::class])
@AutoConfigureMockMvc
@Import(
    Flash17CSRFConfigurationAdapter::class
)
internal class Flash17CSRFCreateControllerKotlinTest @Autowired constructor(
    private val mockMvc: MockMvc
) {

    @MockkBean(relaxed = true)
    lateinit var jdbcUserDetailsManager: JdbcUserDetailsManager

    @SpykBean(BCryptPasswordEncoder::class)
    lateinit var passwordEncoder: PasswordEncoder

    @Test
    @WithMockUser(roles = ["ADMIN"])
    @Throws(Exception::class)
    fun testCreateUserViaGeWhenCreateUserViaGetThenOk() {
        mockMvc.perform(get("/open/create/admin/password/ADMIN"))
            .andExpect(MockMvcResultMatchers.status().isOk)
        val slotUser = slot<UserDetails>()
        val slotPassword = slot<CharSequence>()
        verify { jdbcUserDetailsManager.createUser(capture(slotUser)) }
        verify { passwordEncoder.encode(capture(slotPassword)) }
        slotUser.captured.apply {
            username.shouldNotBeNull() shouldBe "admin"
            password.shouldNotBeNull()
            authorities.shouldHaveSize(1)
                .shouldContain(SimpleGrantedAuthority("ROLE_ADMIN"))
        }
        slotPassword.captured shouldBe "password"
    }

    @Test
    @Throws(Exception::class)
    fun testCreateUserViaGeWhenCreateUserViaPostThenForbidden() {
        mockMvc.perform(post("/open/create/admin/password/ADMIN"))
            .andExpect(MockMvcResultMatchers.status().isForbidden)
        ConsolerizerComposer.outSpace()
            .green("Take note that this is actually how we should create users")
            .yellow("However, we have CSRF enabled in TST and this is why we cannot POST")
            .reset()
    }
}