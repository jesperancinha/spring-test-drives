package org.jesperancinha.sftd.flash58.conditionals.prod;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
@ConditionalOnResource(resources = "classpath:application.properties")
public class Dance7Configuration {

    public Dance7Configuration() {
        ConsolerizerComposer.outSpace()
                .black()
                .bgGreen("We started this configuration class %s", this)
                .bgGreen("Let me love you till the morning comes")
                .reset();
    }

}
