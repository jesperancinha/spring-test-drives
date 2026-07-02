package org.jesperancinha.sftd.flash41.scope.prototype.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jesperancinha.sftd.flash41.scope.prototype.domain.Desert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {Desert.class, DesertController.class})
class DesertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String[] DESERT_MISS_THE_RAIN = new String[]{
            "I step off the train",
            "I'm walking down your street again",
            "And past your door",
            "I guess you don't live there anymore",
    };

    @Test
    void randomDesert() throws Exception {
        final var mvcResult = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        final var responseString = mvcResult.getResponse().getContentAsString();
        final var desert = objectMapper.readValue(responseString, Desert.class);
        assertThat(desert).isNotNull();
        assertThat(desert.getStatement()).isIn((Object[]) DESERT_MISS_THE_RAIN);

        final var mvcResult2 = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        final var responseString2 = mvcResult2.getResponse().getContentAsString();
        final var desert2 = objectMapper.readValue(responseString2, Desert.class);
        assertThat(desert2).isNotNull();
        assertThat(desert2.getStatement()).isIn((Object[]) DESERT_MISS_THE_RAIN);
    }
}