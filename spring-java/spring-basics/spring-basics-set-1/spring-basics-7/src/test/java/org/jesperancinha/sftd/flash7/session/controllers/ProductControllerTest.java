package org.jesperancinha.sftd.flash7.session.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jesperancinha.sftd.flash7.session.handlers.ErrorCar;
import org.jesperancinha.sftd.flash7.session.handlers.ErrorFlower;
import org.jesperancinha.sftd.flash7.session.handlers.MixErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetTulipsWhenCalled_getResponse() throws Exception {
        final MvcResult tulips = mockMvc
                .perform(get("/tulips"))
                .andExpect(status().isOk())
                .andReturn();
        final var contentAsString = tulips.getResponse().getContentAsString();

        assertThat(contentAsString).isNotNull();
        assertThat(contentAsString).isEqualTo("You just got a bunch of tulips!");
    }

    @Test
    void testGetTulipsOkWhenCalled_getResponse() throws Exception {
        final MvcResult tulips = mockMvc.perform(get("/tulips/ok"))
                .andExpect(status().isOk())
                .andReturn();
        final var contentAsString = tulips.getResponse().getContentAsString();
        final var errorMessage = tulips.getResponse().getErrorMessage();
        assertThat(contentAsString).isEmpty();
        assertThat(errorMessage).isEqualTo("This is an error, but it's ok");
    }

    @Test
    void testGetTulipsErrorWhenCalled_getResponse() throws Exception {
        final MvcResult tulips = mockMvc.perform(get("/tulips/error"))
                .andExpect(status().isServiceUnavailable())
                .andReturn();
        final var contentAsString = tulips.getResponse().getContentAsString();
        final var errorMessage = tulips.getResponse().getErrorMessage();
        assertThat(contentAsString).isEmpty();
        assertThat(errorMessage).isEqualTo("We are not available at the moment!");
    }

    @Test
    void testGetFlowerWhenCalled_getResponse() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        final MvcResult roses = mockMvc.perform(get("/flowers/rose"))
                .andExpect(status().isNotFound())
                .andReturn();
        final var contentAsString = roses.getResponse().getContentAsString();
        final var errorMessage = roses.getResponse().getErrorMessage();
        assertThat(contentAsString).isNotNull();
        final ErrorFlower errorFlower = objectMapper.readValue(contentAsString, ErrorFlower.class);
        assertThat(errorFlower.getHttpStatus()).isEqualTo(NOT_FOUND);
        assertThat(errorMessage).isNull();
    }

    @Test
    void testGetCarWhenCalled_getResponse() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        final MvcResult cars = mockMvc.perform(get("/cars/kitt"))
                .andExpect(status().isNotFound())
                .andReturn();
        final var contentAsString = cars.getResponse().getContentAsString();
        final var errorMessage = cars.getResponse().getErrorMessage();
        assertThat(contentAsString).isNotNull();
        final ErrorCar errorCar = objectMapper.readValue(contentAsString, ErrorCar.class);
        assertThat(errorCar.getMessage()).isNotNull();
        assertThat(errorMessage).isNull();
    }

    @Test
    void testGetFlowerLocalWhenCalled_getResponse() throws Exception {
        final MvcResult roses = mockMvc.perform(get("/flowers/local/rose"))
                .andExpect(status().isOk())
                .andReturn();
        final var contentAsString = roses.getResponse().getContentAsString();
        final var errorMessage = roses.getResponse().getErrorMessage();
        assertThat(contentAsString).isNotNull();
        assertThat(contentAsString).isEmpty();

        final ModelAndView modelAndView = roses.getModelAndView();
        assertThat(modelAndView).isNotNull();
        final String viewName = modelAndView.getViewName();
        assertThat(viewName).isNotNull();
        assertThat(viewName).isEqualTo("errorpage");
        final Map<String, Object> model = modelAndView.getModel();
        assertThat(model).isNotNull();
        final ErrorFlower errorFlower = ((ErrorFlower) model.get("fail"));
        assertThat(errorFlower.getHttpStatus()).isEqualTo(NOT_FOUND);
        assertThat(errorFlower.getMessage()).isNotNull();
        assertThat(errorMessage).isNull();
    }

    @Test
    void testGetCarLocalWhenCalled_getResponse() throws Exception {
        final MvcResult cars = mockMvc.perform(get("/cars/local/kitt"))
                .andExpect(status().isOk())
                .andReturn();
        final var contentAsString = cars.getResponse().getContentAsString();
        final var errorMessage = cars.getResponse().getErrorMessage();
        assertThat(contentAsString).isNotNull();
        assertThat(contentAsString).isEmpty();

        final ModelAndView modelAndView = cars.getModelAndView();
        assertThat(modelAndView).isNotNull();
        final String viewName = modelAndView.getViewName();
        assertThat(viewName).isNotNull();
        assertThat(viewName).isEqualTo("errorpage");
        final Map<String, Object> model = modelAndView.getModel();
        assertThat(model).isNotNull();
        final ErrorCar errorFlower = ((ErrorCar) model.get("fail"));
        assertThat(errorFlower.getMessage()).isNotNull();
        assertThat(errorMessage).isNull();
    }

    @Test
    void testGetPotteryWhenCalled_getResponse() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        final MvcResult pottery = mockMvc.perform(get("/pottery/amphora"))
                .andExpect(status().isNotFound())
                .andReturn();
        final var contentAsString = pottery.getResponse().getContentAsString();
        final var errorMessage = pottery.getResponse().getErrorMessage();
        assertThat(contentAsString).isNotNull();
        final MixErrorMessage mixErrorMessage = objectMapper.readValue(contentAsString, MixErrorMessage.class);
        assertThat(mixErrorMessage.getMessage()).isNotNull();
        assertThat(errorMessage).isNull();
    }

    @Test
    void testGetFourWheelsWhenCalled_getResponse() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        final MvcResult fourWheels = mockMvc.perform(get("/fourwheels/rover"))
                .andExpect(status().isNotFound())
                .andReturn();
        final var contentAsString = fourWheels.getResponse().getContentAsString();
        final var errorMessage = fourWheels.getResponse().getErrorMessage();
        assertThat(contentAsString).isNotNull();
        final MixErrorMessage mixErrorMessage = objectMapper.readValue(contentAsString, MixErrorMessage.class);
        assertThat(mixErrorMessage.getMessage()).isNotNull();
        assertThat(errorMessage).isNull();
    }
}