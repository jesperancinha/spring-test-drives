package org.jesperancinha.sftd.flash218.profiles.configurations;

import org.jesperancinha.console.consolerizer.console.ConsolerizerGraphs;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!garbage & !rem")
public class NotGarbageNotREM {
    public NotGarbageNotREM() {
        new PumpkinsConfig();
        ConsolerizerGraphs.printRainbowFlag("It's the smashing pumpkins!");
    }
}
