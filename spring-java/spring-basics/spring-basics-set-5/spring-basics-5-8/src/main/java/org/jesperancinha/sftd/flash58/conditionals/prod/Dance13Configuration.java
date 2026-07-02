package org.jesperancinha.sftd.flash58.conditionals.prod;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.actuate.autoconfigure.info.ConditionalOnEnabledInfoContributor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
@ConditionalOnEnabledInfoContributor("")
public class Dance13Configuration {

    public Dance13Configuration() {
        ConsolerizerComposer.outSpace()
                .black()
                .bgGreen("We started this configuration class %s", this)
                .bgGreen("And how you looked at other girls")
                .reset();
    }

}
