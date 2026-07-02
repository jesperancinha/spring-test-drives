package org.jesperancinha.sftd.flash58.conditionals.prod;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
@ConditionalOnMissingBean(name = "wow")
public class Dance10Configuration {

    public Dance10Configuration() {
        ConsolerizerComposer.outSpace()
                .black()
                .bgGreen("We started this configuration class %s", this)
                .bgGreen("And loneliness killed my world")
                .reset();
    }

}
