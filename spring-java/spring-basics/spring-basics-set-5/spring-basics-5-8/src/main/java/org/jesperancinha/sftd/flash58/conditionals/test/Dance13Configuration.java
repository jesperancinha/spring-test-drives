package org.jesperancinha.sftd.flash58.conditionals.test;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.actuate.autoconfigure.info.ConditionalOnEnabledInfoContributor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
@ConditionalOnEnabledInfoContributor("")
public class Dance13Configuration {

    public Dance13Configuration() {
        ConsolerizerComposer.outSpace()
                .black()
                .bgYellow("We started this configuration class %s", this)
                .bgYellow("And how you looked at other girls")
                .reset();
    }

}
