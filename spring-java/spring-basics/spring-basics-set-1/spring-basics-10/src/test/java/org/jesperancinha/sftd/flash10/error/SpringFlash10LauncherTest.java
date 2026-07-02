package org.jesperancinha.sftd.flash10.error;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SpringFlash10Launcher.class)
@ActiveProfiles("prod")
class SpringFlash10LauncherTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void textContext() {
    }

    @Test
    void testGetStringWhenCalledOnProdThenGotoErrorPage() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult).isNotNull();
        final var modelAndView = mvcResult.getModelAndView();
        assertThat(modelAndView).isNotNull();
        final var viewName = modelAndView.getViewName();
        assertThat(viewName).isEqualTo("runtimeError");
        final var model = modelAndView.getModel();
        assertThat(model.get("ex")).isInstanceOf(RuntimeException.class);
        assertThat(model.get("url").toString()).isEqualTo("http://localhost/");
    }

}