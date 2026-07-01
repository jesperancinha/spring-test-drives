package org.jesperancinha.sftd.flash316.requestparam.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jesperancinha.sftd.flash316.requestparam.model.Think;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.isIn;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ThinkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testThinkRandomWhenAllInputParamsThenCreateRandomThink() throws Exception {
        final var allThinks = Arrays.asList(
                Think.builder().artist("Kelly Clarkson").thinkString("You think you got the best of me").build(),
                Think.builder().artist("Kelly Clarkson").thinkString("Think you had the last laugh").build(),
                Think.builder().artist("Kelly Clarkson").thinkString("Bet you think that everything good is gone").build(),
                Think.builder().artist("Kelly Clarkson").thinkString("Think you left me broken down").build(),
                Think.builder().artist("Kelly Clarkson").thinkString("Think that I'd come running back").build());

        final var thinkStrings = allThinks.stream().map(thinkString -> {
            try {
                return objectMapper.writeValueAsString(thinkString);
            } catch (JsonProcessingException e) {
                return null;
            }
        }).collect(Collectors.toList());

        mockMvc.perform(post("/")
                .queryParam("artist", "Kelly Clarkson")
                .contentType(APPLICATION_JSON)
                .content("[\"You think you got the best of me\", \"Think you had the last laugh\", \"Bet you think that everything good is gone\", \"Think you left me broken down\", \"Think that I'd come running back\"]"))
                .andExpect(status().isOk())
                .andExpect(content().string(isIn(thinkStrings)));
    }

    @Test
    void testThinkRandomWhenOnlyThinksThenCreateRandomThinkWithNullArtist() throws Exception {
        final var allThinks = Arrays.asList(
                Think.builder().thinkString("You think you got the best of me").build(),
                Think.builder().thinkString("Think you had the last laugh").build(),
                Think.builder().thinkString("Bet you think that everything good is gone").build(),
                Think.builder().thinkString("Think you left me broken down").build(),
                Think.builder().thinkString("Think that I'd come running back").build());

        final var thinkStrings = allThinks.stream().map(thinkString -> {
            try {
                return objectMapper.writeValueAsString(thinkString);
            } catch (JsonProcessingException e) {
                return null;
            }
        }).collect(Collectors.toList());

        mockMvc.perform(post("/")
                .contentType(APPLICATION_JSON)
                .content("[\"You think you got the best of me\", \"Think you had the last laugh\", \"Bet you think that everything good is gone\", \"Think you left me broken down\", \"Think that I'd come running back\"]"))
                .andExpect(status().isOk())
                .andExpect(content().string(isIn(thinkStrings)));
    }
}