package org.jesperancinha.sftd.flash17.cipher.controllers

import com.ninjasquad.springmockk.MockkBean
import com.ninjasquad.springmockk.SpykBean
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.slot
import io.mockk.verify
import org.jesperancinha.sftd.flash17.cipher.configuration.Flash17ConfigurationAdapter
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
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import javax.sql.DataSource

@ActiveProfiles("prod")
@WebMvcTest(Flash17CreateController::class, Flash17Controller::class)
@MockkBean(classes = [DataSource::class])
@Import(
    Flash17ConfigurationAdapter::class
)
@AutoConfigureMockMvc
internal class Flash17CreateControllerKotlinTest @Autowired constructor(
    @Autowired
    private val mockMvc: MockMvc,
) {

    @MockkBean(relaxed = true)
    lateinit var jdbcUserDetailsManager: JdbcUserDetailsManager

    @SpykBean(BCryptPasswordEncoder::class)
    lateinit var passwordEncoder: PasswordEncoder

    @Test
    @Throws(Exception::class)
    fun testCreateUserWhenCalledThenCallCreationOk() {
        mockMvc.perform(
            post("/open/create")
                .header("name", "admin")
                .header("password", "password")
                .header("role", "ADMIN")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
        val slotUser = slot<UserDetails>()
        val slotPassword = slot<CharSequence>()
        verify { jdbcUserDetailsManager.createUser(capture(slotUser)) }
        verify { passwordEncoder.encode(capture(slotPassword)) }
        slotUser.captured.apply {
            username.shouldNotBeNull() shouldBe "admin"
            password.shouldNotBeNull() shouldNotBe "password"
            authorities.shouldHaveSize(1)
                .shouldContain(SimpleGrantedAuthority("ROLE_ADMIN"))
        }
        slotPassword.captured shouldBe "password"
    }
}