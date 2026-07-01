package org.jesperancinha.sftd.flash55.hateoas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringFlash55LauncherTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testContext() {
    }

    /**
     * Hypermedia as the engine of application state test
     */
    @Test
    void testGetAllCellsWhenCallingThenReturnWithHATEOASLink() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("" +
                        "{\"cells\":[\"Macrophage\",\"Neutrophil\",\"Natural Killer Cell\",\"Complement\",\"Mast Cell\",\"Monocyte\",\"Follicular Dentritic Cell\"]," +
                        "\"_links\":{\"self\":{\"href\":\"/endless\"}}}"
                ));
    }

    /**
     * Hypermedia as the engine of application state test
     */
    @Test
    void testGetAllCellsWhenCallingEndlessThenReturnWithHATEOASLink() throws Exception {
        mockMvc.perform(get("/endless"))
                .andExpect(status().isOk())
                .andExpect(content().string("" +
                        "{\"cells\":[\"Macrophage\",\"Neutrophil\",\"Natural Killer Cell\",\"Complement\",\"Mast Cell\",\"Monocyte\",\"Follicular Dentritic Cell\"]," +
                        "\"_links\":{\"self\":{\"href\":\"/\"}}}"
                ));
    }
}