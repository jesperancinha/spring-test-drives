package org.jesperancinha.sftd.flash220.allparams;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class SpringFlash220LauncherMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPostAllSinsMatrixWhenCallThenGetAllSins() throws Exception {
        mockMvc.perform(
                post("/matrix/wow;sin1=Lust;sin2=Gluttony;sin3=Greed;sin4=Sloth;sin5=Wrath;sin6=Envy;sin7=Pride")
        )
                .andExpect(status().isOk())
                .andExpect(content().string("You are wise!"));
    }

    @Test
    void testPostAllSinsRequestWhenCallThenGetAllSins() throws Exception {
        mockMvc.perform(
                post("/request/Lust/Gluttony?sin3=Greed&sin4=Sloth")
                        .header("sin5", "Wrath")
                        .header("sin6", "Envy")
                        .header("sin7", "Pride")
        )
                .andExpect(status().isOk())
                .andExpect(content().string("You are wise!"));
    }
}