package org.jesperancinha.sftd.flash416.mock.mvc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = VampireController.class)
class VampireControllerTest {

    @Autowired
    private VampireController vampireController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vampireController).build();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testArmsWhenRequestThenReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/arms"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("text/plain"))
                .andExpect(content().string("Come into these arms again"));
    }

    @Test
    void testBodyWhenRequestThenReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/body"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("text/plain"))
                .andExpect(content().string("Lay your body down"));
    }
}