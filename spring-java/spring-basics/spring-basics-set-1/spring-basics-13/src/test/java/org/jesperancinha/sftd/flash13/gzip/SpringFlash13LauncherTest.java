package org.jesperancinha.sftd.flash13.gzip;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = SpringFlash13Launcher.class)
class SpringFlash13LauncherTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testContext() {
    }

    @Test
    void testCallsToCssWhenNormalThenGetNormal() throws Exception {
        mockMvc.perform(get("/flash13.styles.css"))
                .andExpect(status().isOk())
                .andExpect(content().string(".car {\n" +
                        "  text-align: left;\n" +
                        "  font-weight: bolder;\n" +
                        "}"));
    }

    @Test
    void testCallsToCssWhenGzipThenGetContents() throws Exception {
        mockMvc.perform(get("/original.styles.css"))
                .andExpect(status().isOk())
                .andExpect(content().string(".car {\n" +
                        "  text-align: center;\n" +
                        "  font-weight: bolder;\n" +
                        "}"));
    }
}