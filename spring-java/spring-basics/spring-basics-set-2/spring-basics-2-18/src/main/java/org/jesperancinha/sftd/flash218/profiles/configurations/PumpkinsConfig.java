package org.jesperancinha.sftd.flash218.profiles.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.BLUE;

@Configuration
@Profile("pumpkin")
public class PumpkinsConfig {

    PumpkinsConfig() {
        BLUE.printGenericLn("Despite all my rage, I am still just a rat in a cage\n" +
                "Despite all my rage, I am still just a rat in a cage\n" +
                "Then someone will say, \"What is lost can never be saved\"\n" +
                "Despite all my rage, I am still just a rat in a cage");
    }
}
