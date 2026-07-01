package org.jesperancinha.sftd.flash4.persistence.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class SpringFlash4LauncherTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new SpringFlash4Launcher()).build();
    }

    @Test
    void testCurrentDateWhenRequest_noErrors() throws Exception {
        mockMvc.perform(post("/")
                .contentType("application/text")
                .header("currentTime", "1999-12-01"))
                .andExpect(status().isOk());
    }

    @Test
    void testCurrentTimeWhenRequest_noErrors() throws Exception {
        mockMvc.perform(post("/time")
                .contentType("application/text")
                .header("currentTime", "1999-12-01 12:30:31"))
                .andExpect(status().isOk());
    }

    @Test
    void thousandDollars_whenRequest_noErrors() throws Exception {
        mockMvc.perform(post("/dollars")
                .contentType("application/text")
                .header("dollars", "150"))
                .andExpect(status().isOk());
    }
}