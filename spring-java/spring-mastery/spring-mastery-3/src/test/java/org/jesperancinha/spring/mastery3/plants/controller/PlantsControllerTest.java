package org.jesperancinha.spring.mastery3.plants.controller;

import tools.jackson.databind.ObjectMapper;
import org.jesperancinha.spring.mastery3.plants.dto.PlantDto;
import org.jesperancinha.spring.mastery3.plants.model.Plant;
import org.jesperancinha.spring.mastery3.plants.service.PlantService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlantsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PlantService plantService;

        private final ArgumentCaptor<Plant> argumentCaptor = ArgumentCaptor.forClass(Plant.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void openPostFailWhenCalledTheResponseMustBeEmpty() throws Exception {
        final var plantDto = PlantDto
                .builder()
                .name("Yucca")
                .scientificName("Yucca filamentosa")
                .build();

        mockMvc.perform(post("/create/old")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(plantDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(plantService, only()).createPlant(argumentCaptor.capture());
        final var value = argumentCaptor.getValue();
        assertThat(value.getName()).isEqualTo(plantDto.getName());
        assertThat(value.getScientificName()).isEqualTo(plantDto.getScientificName());
    }

    @Test
    public void testGet3CopiesWhenCallThenReturn3Yuccas() throws Exception {
        final var plantDto = PlantDto
                .builder()
                .name("Yucca")
                .scientificName("Yucca filamentosa")
                .build();

        mockMvc.perform(get("/list/copies")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(List.of(
                        plantDto, plantDto, plantDto
                ))));
    }

    @Test
    public void testGet3CopiesArrayWhenCallThenReturn3Yuccas() throws Exception {
        final var plantDto = PlantDto
                .builder()
                .name("Yucca")
                .scientificName("Yucca filamentosa")
                .build();
        mockMvc.perform(get("/list/array/copies")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(List.of(
                        plantDto
                ))));
    }
}