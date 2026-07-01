package org.jesperancinha.sftd.flash218.profiles.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("garbage | pumpkin")
public class GarbageORPumpkinConfig {
    public GarbageORPumpkinConfig() {
        new GarbageConfig();
        new PumpkinsConfig();
    }
}
