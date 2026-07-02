package org.jesperancinha.sftd.flash418.controller.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SpringFlash418LauncherTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testContext() {
    }

    @Test
    void testEightiesCheckWhenSendingHit80sThenNoError() throws Exception {
        final var song = Song.builder().name("You Keep Me Hangin'' On").artist("Kim Wilde").hitDate(LocalDate.of(1987, 6, 1)).build();

        mockMvc.perform(MockMvcRequestBuilders.put("/")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(song)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void testEightiesCheckWhenSendingHit90sThenThrowError() throws Exception {
        final var song = Song.builder().name("Madison Avenue - Don''t Call Me Baby").artist("Madison Avenue").hitDate(LocalDate.of(1999, 10, 8)).build();

        mockMvc.perform(MockMvcRequestBuilders.put("/")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(song)))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().string(containsString("This music is not from the eighties")));
    }

    @Test
    void testEightiesCheckWhenCallingMethodNotAllowedThenHandleInASpringFashion() throws Exception {
        final var song = Song.builder().name("Madison Avenue - Don''t Call Me Baby").artist("Madison Avenue").hitDate(LocalDate.of(1999, 10, 8)).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(song)))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(content().string(containsString("")))
                .andExpect(header().string("allow", "PUT"));
    }
}