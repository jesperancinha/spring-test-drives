package org.jesperancinha.sftd.flash313.bean.mapping.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GenerationController.class)
class GenerationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHandleRequestInternalWhenCalledThenGotoGenerationView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/beatenGeneration.html"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "Open your imagination"));
    }
}