package org.jesperancinha.sftd.flash8.jsp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ViewController.class)
class ViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testMainPageWhenCalledThenAIWFCIY() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult).isNotNull();
        final ModelAndView modelAndView = mvcResult.getModelAndView();
        assertThat(modelAndView).isNotNull();
        final var viewName = modelAndView.getViewName();
        assertThat(viewName).isEqualTo("index");
        final var model = modelAndView.getModel();
        assertThat(model).isNotNull();
        final String notification = (String) model.get("notification");
        assertThat(notification).isEqualTo("All I want for christmas is you!");
    }
}