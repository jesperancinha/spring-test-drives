package org.jesperancinha.sftd.flash29.security.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.jesperancinha.sftd.flash29.security.configuration.Flash29ConfigurationAdapter
import org.jesperancinha.sftd.flash29.security.dto.JewelDto
import org.jesperancinha.sftd.flash29.security.repository.JewelRepository
import org.jesperancinha.sftd.flash29.security.services.JewelService
import org.jesperancinha.sftd.flash29.security.services.JewelType
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

/**
 * Tests for the controller
 * We are testing all REST methods necessary to manage our jewels
 * Note that for all the non failing cases, we always need minimally one logged-in user at lease, regardless of roles or jewel possession.
 */
@WebMvcTest(controllers = [Flash29Controller::class])
@Import(
    Flash29ConfigurationAdapter::class
)
internal class Flash29ControllerKotlinTest @Autowired constructor(
    @Autowired
    private val mockMvc: MockMvc
) {
    @MockkBean(relaxed = true)
    lateinit var jewelService: JewelService

    @MockkBean(relaxed = true)
    lateinit var jewelRepository: JewelRepository

    private val objectMapper = ObjectMapper()


    @Test
    @WithMockUser(username = "joao", roles = ["ADMIN"])
    @Throws(Exception::class)
    fun testGenericHandleWhenCalledThenReturnOwnedJewelsView() {
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
    fun testListJewelsWhenSimpleAuthenticationThenStillAbleToGetList() {
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
    fun testCreateJewelWhenCreatingOneThenCreateIt() {
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
    fun testJewelWhenFetchingByIdThenReturnMatchingJewel() {
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
    fun testRemoveJewelWhenCallToDeleteJewel1ThenRemoveIt() {
        val jewelDto = JewelDto.builder().jewelType(JewelType.EMERALD).guardian("KittenPowers").build()
        every { jewelService.getJewelById(1L) } returns jewelDto
        mockMvc.perform(MockMvcRequestBuilders.delete("/jewels/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(""))
        verify { jewelService.getJewelById(1L) }
        verify { jewelService.deleteJewel(jewelDto) }
    }
}