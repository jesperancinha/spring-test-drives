package org.jesperancinha.spring.flash29.security.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.jesperancinha.spring.flash29.security.configuration.JewelSecurityConfiguration
import org.jesperancinha.spring.flash29.security.dto.JewelDto
import org.jesperancinha.spring.flash29.security.repository.JewelRepository
import org.jesperancinha.spring.flash29.security.services.JewelService
import org.jesperancinha.spring.flash29.security.services.JewelType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.MockMvcConfigurer
import org.springframework.web.context.WebApplicationContext
import tools.jackson.databind.ObjectMapper

/**
 * Tests for the controller
 * We are testing all REST methods necessary to manage our jewels
 * Note that for all the non failing cases, we always need minimally one logged-in user at lease, regardless of roles or jewel possession.
 */
@WebMvcTest(controllers = [JewelController::class])
@Import(
    JewelSecurityConfiguration::class
)
@Execution(SAME_THREAD)
internal class JewelControllerKotlinTest @Autowired constructor(
    @MockkBean(relaxed = true)
    private val jewelService: JewelService,
    @MockkBean(relaxed = true)
    private val jewelRepository: JewelRepository,
) {
    private val objectMapper = ObjectMapper()
    lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var context: WebApplicationContext

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply<DefaultMockMvcBuilder>(springSecurity())
            .build()
    }

    @Test
    @WithMockUser(username = "joao", roles = ["ADMIN"])
    @Throws(Exception::class)
    fun `should return owned jewels view when generic handle is called`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.view().name("ownedjewels"))
            .andExpect(MockMvcResultMatchers.model().attribute("name", "joao"))
            .andExpect(
                MockMvcResultMatchers.model().attribute(
                    "roles", listOf(
                        SimpleGrantedAuthority("ROLE_ADMIN")
                    )
                )
            )
    }

    @Test
    @WithMockUser(username = "joao", roles = ["ADMIN"])
    @Throws(Exception::class)
    fun `should still be able to get list when simple authentication`() {
        val listOfJewels = listOf(
            JewelDto.builder().jewelType(JewelType.EMERALD).guardian("KittenPowers").build(),
            JewelDto.builder().jewelType(JewelType.RUBY).guardian("KittenStrongSword").build()
        )
        every { jewelService.all } returns listOfJewels
        mockMvc.perform(MockMvcRequestBuilders.get("/jewels"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(listOfJewels)))
        verify { jewelService.all }
    }

    @Test
    @WithMockUser(username = "joao", roles = ["ADMIN"])
    @Throws(Exception::class)
    fun `should create jewel when creating one`() {
        val kittenPowersJewel = JewelDto.builder().jewelType(JewelType.EMERALD).guardian("KittenPowers").build()
        every { jewelService.createJewel(kittenPowersJewel) } returns kittenPowersJewel
        mockMvc.perform(
            MockMvcRequestBuilders.post("/jewels")
                .content(objectMapper.writeValueAsString(kittenPowersJewel))
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(kittenPowersJewel)))
        verify { jewelService.createJewel(kittenPowersJewel) }
    }

    @Test
    @WithMockUser(username = "joao", roles = ["ADMIN"])
    @Throws(Exception::class)
    fun `should return matching jewel when fetching by id`() {
        val jewelDto = JewelDto.builder().jewelType(JewelType.EMERALD).guardian("KittenPowers").build()
        every { jewelService.getJewelById(1L) } returns jewelDto
        mockMvc.perform(MockMvcRequestBuilders.get("/jewels/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(jewelDto)))
        verify { jewelService.getJewelById(1L) }
    }

    @Test
    @WithMockUser(username = "joao", roles = ["ADMIN"])
    @Throws(Exception::class)
    fun `should remove jewel when calling delete jewel 1`() {
        val jewelDto = JewelDto.builder().jewelType(JewelType.EMERALD).guardian("KittenPowers").build()
        every { jewelService.getJewelById(1L) } returns jewelDto
        mockMvc.perform(MockMvcRequestBuilders.delete("/jewels/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(""))
        verify { jewelService.getJewelById(1L) }
        verify { jewelService.deleteJewel(jewelDto) }
    }
}