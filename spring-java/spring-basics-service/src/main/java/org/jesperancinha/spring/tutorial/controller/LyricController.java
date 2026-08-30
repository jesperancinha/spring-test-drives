package org.jesperancinha.spring.tutorial.controller;

import org.jesperancinha.console.consolerizer.common.ConsolerizerColor;
import org.jesperancinha.console.consolerizer.console.ConsolerizerGraphs;
import org.jesperancinha.spring.tutorial.configuration.LyricsTraditionalConfiguration;
import org.jesperancinha.spring.tutorial.configuration.LyricsTypeSafeConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LyricController {

    private final LyricsTraditionalConfiguration lyricsTraditionalConfiguration;
    private final LyricsTypeSafeConfiguration lyricsTypeSafeConfiguration;

    public LyricController(final LyricsTraditionalConfiguration lyricsTraditionalConfiguration,
                           final LyricsTypeSafeConfiguration lyricsTypeSafeConfiguration) {
        this.lyricsTraditionalConfiguration = lyricsTraditionalConfiguration;
        this.lyricsTypeSafeConfiguration = lyricsTypeSafeConfiguration;
    }

    @GetMapping("/lyrics")
    public @ResponseBody
    String getResponse() {
        return ConsolerizerGraphs.getUnicorns(10) +
                ConsolerizerColor.BLUE.getPBEscapedText(lyricsTraditionalConfiguration.getLyric1()) +
                ConsolerizerColor.BLUE.getPBEscapedText(lyricsTraditionalConfiguration.getLyric2()) +
                ConsolerizerColor.BLUE.getPBEscapedText(lyricsTypeSafeConfiguration.getLyric3()) +
                ConsolerizerColor.BLUE.getPBEscapedText(lyricsTypeSafeConfiguration.getLyric4()) +
                ConsolerizerGraphs.getUnicorns(10);
    }
}
