package org.jesperancinha.sftd.mastery3.plants.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.shouldBe
import io.mockk.slot
import io.mockk.verify
import org.jesperancinha.sftd.mastery3.plants.dto.PlantDto
import org.jesperancinha.sftd.mastery3.plants.model.Plant
import org.jesperancinha.sftd.mastery3.plants.service.PlantService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class PlantsControllerKotlinTest(
    @Autowired
    private val mockMvc: MockMvc
) {
    @MockkBean(relaxed = true)
    lateinit var plantService: PlantService
    
    private val objectMapper = ObjectMapper()
    @Test
    @Throws(Exception::class)
    fun openPostFailWhenCalledTheResponseMustBeEmpty() {
        val plantDto = PlantDto
            .builder()
            .name("Yucca")
            .scientificName("Yucca filamentosa")
            .build()
        mockMvc.perform(
            post("/create/old")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(plantDto))
        )
            .andExpect(status().isOk)
            .andExpect(content().string("{\"name\":\"\",\"scientificName\":\"\"}"))
        val slotPlant = slot<Plant>()
        verify {plantService.createPlant(capture(slotPlant)) }
        slotPlant.captured
            .apply {
                name shouldBe plantDto.name
                scientificName shouldBe plantDto.scientificName
            }
    }

    @Test
    @Throws(Exception::class)
    fun testGet3CopiesWhenCallThenReturn3Yuccas() {
        val plantDto = PlantDto
            .builder()
            .name("Yucca")
            .scientificName("Yucca filamentosa")
            .build()
        mockMvc.perform(
            get("/list/copies")
                .accept(APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().string(
                    objectMapper.writeValueAsString(
                        listOf(
                            plantDto, plantDto, plantDto
                        )
                    )
                )
            )
    }

    @Test
    @Throws(Exception::class)
    fun testGet3CopiesArrayWhenCallThenReturn3Yuccas() {
        val plantDto = PlantDto
            .builder()
            .name("Yucca")
            .scientificName("Yucca filamentosa")
            .build()
        mockMvc.perform(
            get("/list/array/copies")
                .accept(APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().string(
                    objectMapper.writeValueAsString(listOf(plantDto)
                    )
                )
            )
    }
}