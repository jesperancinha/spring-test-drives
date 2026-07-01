package org.jesperancinha.sftd.flash22.interceptors;

import org.jesperancinha.sftd.flash22.interceptors.beans.FeelingLoveBean;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SpringFlash22Launcher.class)
class SpringFlash22LauncherTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FeelingLoveBean feelingLoveBean;

    @Captor
    private ArgumentCaptor<Object> objectArgumentCaptor;

    @Captor
    private ArgumentCaptor<ModelAndView> modelAndViewArgumentCaptor;

    @Captor
    private ArgumentCaptor<Exception> exceptionArgumentCaptor;

    @Test
    void main() {
    }

    @Test
    void testGetStringWhenCalledThenTriggerInterceptors() throws Exception {
        when(feelingLoveBean.preHandle(any(Object.class))).thenReturn(true);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Fine Wine"));

        verify(feelingLoveBean, times(1)).preHandle(objectArgumentCaptor.capture());
        verify(feelingLoveBean, times(1)).postHandle(objectArgumentCaptor.capture(), modelAndViewArgumentCaptor.capture());
        verify(feelingLoveBean, times(1)).afterCompletion(objectArgumentCaptor.capture(), exceptionArgumentCaptor.capture());

        final List<Object> allValues = objectArgumentCaptor.getAllValues();
        assertThat(allValues).hasSize(3);
        allValues.forEach(value ->
                assertThat(value.toString()).isEqualTo("org.jesperancinha.sftd.flash22.interceptors.SpringFlash22Launcher#getString()"));
        final ModelAndView value = modelAndViewArgumentCaptor.getValue();
        assertThat(value).isNull();
        final Exception exception = exceptionArgumentCaptor.getValue();
        assertThat(exception).isNull();

    }
}