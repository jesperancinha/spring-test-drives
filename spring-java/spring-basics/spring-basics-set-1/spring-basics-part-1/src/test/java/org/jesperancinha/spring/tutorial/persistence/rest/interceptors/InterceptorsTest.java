package org.jesperancinha.spring.tutorial.persistence.rest.interceptors;

import org.jesperancinha.spring.tutorial.persistence.rest.beans.FeelingLoveBean;
import org.jesperancinha.spring.tutorial.persistence.rest.configuration.InterceptorConfiguration;
import org.jesperancinha.spring.tutorial.persistence.rest.controller.WineController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.annotation.DirtiesContext;

@WebMvcTest(controllers = WineController.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {WineController.class, InterceptorConfiguration.class})
@Execution(SAME_THREAD)
class InterceptorsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FeelingLoveBean feelingLoveBean;

        private final ArgumentCaptor<Object> objectArgumentCaptor = ArgumentCaptor.forClass(Object.class);

        private final ArgumentCaptor<ModelAndView> modelAndViewArgumentCaptor = ArgumentCaptor.forClass(ModelAndView.class);

        private final ArgumentCaptor<Exception> exceptionArgumentCaptor = ArgumentCaptor.forClass(Exception.class);

    @Test
    void main() {
    }

    @Test
    void testGetStringWhenCalledThenTriggerInterceptors() throws Exception {
        when(feelingLoveBean.preHandle(any(Object.class))).thenReturn(true);

        mockMvc.perform(get("/wine"))
                .andExpect(status().isOk())
                .andExpect(content().string("Fine Wine"));

        verify(feelingLoveBean, times(1)).preHandle(objectArgumentCaptor.capture());
        verify(feelingLoveBean, times(1)).postHandle(objectArgumentCaptor.capture(), modelAndViewArgumentCaptor.capture());
        verify(feelingLoveBean, times(1)).afterCompletion(objectArgumentCaptor.capture(), exceptionArgumentCaptor.capture());

        final List<Object> allValues = objectArgumentCaptor.getAllValues();
        assertThat(allValues).hasSize(3);
        allValues.forEach(value ->
                assertThat(value.toString()).isEqualTo("org.jesperancinha.spring.tutorial.persistence.rest.controller.WineController#getString()"));
        final ModelAndView value = modelAndViewArgumentCaptor.getValue();
        assertThat(value).isNull();
        final Exception exception = exceptionArgumentCaptor.getValue();
        assertThat(exception).isNull();

    }
}