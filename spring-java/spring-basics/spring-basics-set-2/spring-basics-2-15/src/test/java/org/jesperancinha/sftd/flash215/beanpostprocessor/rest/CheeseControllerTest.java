package org.jesperancinha.sftd.flash215.beanpostprocessor.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jesperancinha.sftd.flash215.beanpostprocessor.bean.Cheese;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CheeseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllCheesesWhenCallThenRetrieveCheeseList() throws Exception {
        final var expectedCheeses = List.of(
                Cheese.builder().name("Camembert").url("https://en.wikipedia.org/wiki/Camembert").checks(new ArrayList<>()).build(),
                Cheese.builder().name("Brie").url("https://en.wikipedia.org/wiki/Brie").checks(new ArrayList<>()).build(),
                Cheese.builder().name("Sao Jorge").url("https://en.wikipedia.org/wiki/S%C3%A3o_Jorge_cheese").checks(new ArrayList<>()).build()
        );
        final var objectMapper = new ObjectMapper();

        final var mvcResult = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        final var contentAsString = mvcResult.getResponse().getContentAsString();
        final var cheeses = objectMapper.readValue(contentAsString, Cheese[].class);
        final var camembert = cheeses[0];
        assertThat(camembert.getName()).isEqualTo(expectedCheeses.get(0).getName());
        assertThat(camembert.getUrl()).isEqualTo(expectedCheeses.get(0).getUrl());
        final var brie = cheeses[1];
        assertThat(brie.getName()).isEqualTo(expectedCheeses.get(1).getName());
        assertThat(brie.getUrl()).isEqualTo(expectedCheeses.get(1).getUrl());
        final var saoJorge = cheeses[2];
        assertThat(saoJorge.getName()).isEqualTo(expectedCheeses.get(2).getName());
        assertThat(saoJorge.getUrl()).isEqualTo(expectedCheeses.get(2).getUrl());
    }
}