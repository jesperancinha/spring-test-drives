package org.jesperancinha.spring.tutorial.controller;

import org.jesperancinha.spring.tutorial.configuration.LyricsTraditionalConfiguration;
import org.jesperancinha.spring.tutorial.configuration.LyricsTypeSafeConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LyricsController.class)
@ContextConfiguration(classes = {LyricsController.class, LyricsTraditionalConfiguration.class, LyricsTypeSafeConfiguration.class})
class LyricsControllerTest {

    @MockitoBean
    private LyricsTypeSafeConfiguration lyricsTypeSafeConfiguration;

    @MockitoBean
    private LyricsTraditionalConfiguration lyricsTraditionalConfiguration;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetResponseWhenCallingThenReturnConfiguration() throws Exception {
        when(lyricsTraditionalConfiguration.getLyric1()).thenReturn("got");
        when(lyricsTraditionalConfiguration.getLyric2()).thenReturn("everything");
        when(lyricsTypeSafeConfiguration.getLyric3()).thenReturn("I");
        when(lyricsTypeSafeConfiguration.getLyric4()).thenReturn("wanted");

        mockMvc.perform(get("/lyrics"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84" +
                                "<p style=\"margin:0; font-family: monospace; color:#0000aa\"><b>got</b></p>" +
                                "<p style=\"margin:0; font-family: monospace; color:#0000aa\"><b>everything</b></p>" +
                                "<p style=\"margin:0; font-family: monospace; color:#0000aa\"><b>I</b></p>" +
                                "<p style=\"margin:0; font-family: monospace; color:#0000aa\"><b>wanted</b></p>" +
                                "\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84\uD83E\uDD84"));

        verify(lyricsTraditionalConfiguration, times(0)).setLyric1(any());
        verify(lyricsTraditionalConfiguration, times(1)).getLyric1();
        verify(lyricsTraditionalConfiguration, times(0)).setLyric2(any());
        verify(lyricsTraditionalConfiguration, times(1)).getLyric2();
        verify(lyricsTypeSafeConfiguration, times(0)).setLyric3(any());
        verify(lyricsTypeSafeConfiguration, times(1)).getLyric3();
        verify(lyricsTypeSafeConfiguration, times(0)).setLyric4(any());
        verify(lyricsTypeSafeConfiguration, times(1)).getLyric4();
        verifyNoMoreInteractions(lyricsTraditionalConfiguration);
        verifyNoMoreInteractions(lyricsTypeSafeConfiguration);
    }
}