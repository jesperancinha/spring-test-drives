package org.jesperancinha.sftd.flash58.conditionals.prod;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
@ConditionalOnMissingClass("org.springframework.context.annotation.Umpalumpa")
public class Dance5Configuration {

    public Dance5Configuration() {
        ConsolerizerComposer.outSpace()
                .black()
                .bgRed("We started this configuration class %s", this)
                .bgGreen("Ooh baby")
                .reset();
    }

}
