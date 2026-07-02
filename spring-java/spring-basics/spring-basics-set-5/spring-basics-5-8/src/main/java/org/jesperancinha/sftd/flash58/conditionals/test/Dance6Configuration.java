package org.jesperancinha.sftd.flash58.conditionals.test;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.devtools.restart.ConditionalOnInitializedRestarter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
@ConditionalOnInitializedRestarter
public class Dance6Configuration {

    public Dance6Configuration() {
        ConsolerizerComposer.outSpace()
                .black()
                .bgYellow("We started this configuration class %s", this)
                .bgYellow("Love me right")
                .reset();
    }

}
