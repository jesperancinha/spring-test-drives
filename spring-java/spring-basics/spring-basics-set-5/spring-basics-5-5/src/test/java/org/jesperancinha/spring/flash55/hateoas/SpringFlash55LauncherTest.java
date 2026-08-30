package org.jesperancinha.spring.flash55.hateoas;

import tools.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringFlash55LauncherTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testContext() {
    }

    /**
     * Hypermedia as the engine of application state test
     */
    @Test
    void testGetAllCellsWhenCallingThenReturnWithHATEOASLink() throws Exception {
        final var mvcResult = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        final var contentAsString = mvcResult.getResponse().getContentAsString();
        final var node = objectMapper.readTree(contentAsString);
        assertThat(node.get("cells")).isEqualTo(objectMapper.readTree("[\"Macrophage\",\"Neutrophil\",\"Natural Killer Cell\",\"Complement\",\"Mast Cell\",\"Monocyte\",\"Follicular Dentritic Cell\"]"));
        assertThat(node.get("_links")).isEqualTo(objectMapper.readTree("{\"self\":{\"href\":\"/endless\"}}"));
    }

    /**
     * Hypermedia as the engine of application state test
     */
    @Test
    void testGetAllCellsWhenCallingEndlessThenReturnWithHATEOASLink() throws Exception {
        final var mvcResult = mockMvc.perform(get("/endless"))
                .andExpect(status().isOk())
                .andReturn();

        final var contentAsString = mvcResult.getResponse().getContentAsString();
        final var node = objectMapper.readTree(contentAsString);
        assertThat(node.get("cells")).isEqualTo(objectMapper.readTree("[\"Macrophage\",\"Neutrophil\",\"Natural Killer Cell\",\"Complement\",\"Mast Cell\",\"Monocyte\",\"Follicular Dentritic Cell\"]"));
        assertThat(node.get("_links")).isEqualTo(objectMapper.readTree("{\"self\":{\"href\":\"/\"}}"));
    }
}