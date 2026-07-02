package org.jesperancinha.sftd.flash15.configuration;

import org.jesperancinha.console.consolerizer.common.ConsolerizerColor;
import org.jesperancinha.console.consolerizer.console.ConsolerizerGraphs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringFlash15Launcher {

    private final Flash15TraditionalConfiguration flash15TraditionalConfiguration;
    private final Flash15TypeSafeConfiguration flash15TypeSafeConfiguration;

    SpringFlash15Launcher(final Flash15TraditionalConfiguration flash15TraditionalConfiguration,
                          final Flash15TypeSafeConfiguration flash15TypeSafeConfiguration) {
        this.flash15TraditionalConfiguration = flash15TraditionalConfiguration;
        this.flash15TypeSafeConfiguration = flash15TypeSafeConfiguration;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash15Launcher.class, args);
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

    ;

}
