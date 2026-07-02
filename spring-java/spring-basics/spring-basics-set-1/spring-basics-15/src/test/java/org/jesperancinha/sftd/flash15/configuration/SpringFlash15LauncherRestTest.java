package org.jesperancinha.sftd.flash15.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class SpringFlash15LauncherRestTest {

    @MockitoBean
    private Flash15TypeSafeConfiguration flash15TypeSafeConfiguration;

    @MockitoBean
    private Flash15TraditionalConfiguration flash15TraditionalConfiguration;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetResponseWhenCallingThenReturnConfiguration() throws Exception {
        when(flash15TraditionalConfiguration.getLyric1()).thenReturn("got");
        when(flash15TraditionalConfiguration.getLyric2()).thenReturn("everything");
        when(flash15TypeSafeConfiguration.getLyric3()).thenReturn("I");
        when(flash15TypeSafeConfiguration.getLyric4()).thenReturn("wanted");

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84" +
                                "<p style=\"margin:0; font-family: monospace; color:#0000aa\"><b>got</b></p>" +
                                "<p style=\"margin:0; font-family: monospace; color:#0000aa\"><b>everything</b></p>" +
                                "<p style=\"margin:0; font-family: monospace; color:#0000aa\"><b>I</b></p>" +
                                "<p style=\"margin:0; font-family: monospace; color:#0000aa\"><b>wanted</b></p>" +
                                "\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84"));

        verify(flash15TraditionalConfiguration, times(0)).setLyric1(any());
        verify(flash15TraditionalConfiguration, times(1)).getLyric1();
        verify(flash15TraditionalConfiguration, times(0)).setLyric2(any());
        verify(flash15TraditionalConfiguration, times(1)).getLyric2();
        verify(flash15TypeSafeConfiguration, times(0)).setLyric3(any());
        verify(flash15TypeSafeConfiguration, times(1)).getLyric3();
        verify(flash15TypeSafeConfiguration, times(0)).setLyric4(any());
        verify(flash15TypeSafeConfiguration, times(1)).getLyric4();
        verifyNoMoreInteractions(flash15TraditionalConfiguration);
        verifyNoMoreInteractions(flash15TypeSafeConfiguration);
    }
}