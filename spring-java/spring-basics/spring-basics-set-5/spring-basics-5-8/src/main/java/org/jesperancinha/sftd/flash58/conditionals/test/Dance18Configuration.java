package org.jesperancinha.sftd.flash58.conditionals.test;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash58.conditionals.SpringFlash58Launcher;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
@ConditionalOnAvailableEndpoint(endpoint = SpringFlash58Launcher.class)
public class Dance18Configuration {

    public Dance18Configuration() {
        ConsolerizerComposer.outSpace()
                .black()
                .bgYellow("We started this configuration class %s", this)
                .bgYellow("I've been working too hard lately")
                .reset();
    }

}
