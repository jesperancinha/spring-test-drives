package org.jesperancinha.sftd.flash218.profiles.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.WHITE;

@Configuration
@Profile("garbage")
public class GarbageConfig {
    GarbageConfig() {
        WHITE.printGenericLn("I am milk\n" +
                "I am red hot kitchen\n" +
                "And I am cool\n" +
                "Cool as the deep blue ocean\n" +
                "I am lost\n" +
                "So I am cruel\n" +
                "But I'd be love and sweetness\n" +
                "If I had you");
    }
}
