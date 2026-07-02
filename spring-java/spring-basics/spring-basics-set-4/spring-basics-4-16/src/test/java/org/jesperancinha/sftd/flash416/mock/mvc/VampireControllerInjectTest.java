package org.jesperancinha.sftd.flash416.mock.mvc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(VampireController.class)
class VampireControllerInjectTest {

    @Autowired
    private MockMvc mockMvc;

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