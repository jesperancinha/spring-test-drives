package org.jesperancinha.sftd.flash310.jsoncomponent.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jesperancinha.sftd.flash310.jsoncomponent.dto.Guitar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class GuitarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testAllGuitarsWhenCallingThenReturnSimulatedData() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("[\"Guitar(brand=Fender, model=75TH Anni Com Strat MN 2BB, value=2400, currency=EUR)\",\"Guitar(brand=Jackson, model=Jackson JS Series Dinky JS32Q DKA Trans Purple Burst, value=500, currency=EUR)\"]"));
    }

    @Test
    void testCreateGuitarWhenCallingThenSimulateCreation() throws Exception {
        final var guitar = Guitar.builder().brand("Gibson").currency("EUR").model("SG Junior").value(1399L).build();

        mockMvc.perform(post("/")
                .content(objectMapper.writeValueAsString(guitar))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}