package org.jesperancinha.spring.flash15.configuration.controller;

import org.jesperancinha.console.consolerizer.common.ConsolerizerColor;
import org.jesperancinha.console.consolerizer.console.ConsolerizerGraphs;
import org.jesperancinha.spring.flash15.configuration.Flash15TraditionalConfiguration;
import org.jesperancinha.spring.flash15.configuration.Flash15TypeSafeConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LyricController {

    private final Flash15TraditionalConfiguration flash15TraditionalConfiguration;
    private final Flash15TypeSafeConfiguration flash15TypeSafeConfiguration;

    public LyricController(final Flash15TraditionalConfiguration flash15TraditionalConfiguration,
                           final Flash15TypeSafeConfiguration flash15TypeSafeConfiguration) {
        this.flash15TraditionalConfiguration = flash15TraditionalConfiguration;
        this.flash15TypeSafeConfiguration = flash15TypeSafeConfiguration;
    }

    @GetMapping("/")
    public @ResponseBody
    String getResponse() {
        final var sb = new StringBuilder();
        sb.append(ConsolerizerGraphs.getUnicorns(10));
        sb.append(ConsolerizerColor.BLUE.getPBEscapedText(flash15TraditionalConfiguration.getLyric1()));
        sb.append(ConsolerizerColor.BLUE.getPBEscapedText(flash15TraditionalConfiguration.getLyric2()));
        sb.append(ConsolerizerColor.BLUE.getPBEscapedText(flash15TypeSafeConfiguration.getLyric3()));
        sb.append(ConsolerizerColor.BLUE.getPBEscapedText(flash15TypeSafeConfiguration.getLyric4()));
        sb.append(ConsolerizerGraphs.getUnicorns(10));
        return sb.toString();
    }
}
