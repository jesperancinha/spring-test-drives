package org.jesperancinha.sftd.flash58.conditionals.test;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
@ConditionalOnClass(org.springframework.boot.autoconfigure.condition.ConditionalOnClass.class)
public class Dance9Configuration {

    public Dance9Configuration() {
        ConsolerizerComposer.outSpace()
                .black()
                .bgYellow("We started this configuration class %s", this)
                .bgYellow("Seems to be loneliness")
                .reset();
    }
}
