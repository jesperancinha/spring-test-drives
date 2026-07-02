package org.jesperancinha.sftd.flash58.conditionals.prod;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
@ConditionalOnSingleCandidate(Dance1Configuration.class)
public class Dance20Configuration {

    public Dance20Configuration() {
        ConsolerizerComposer.outSpace()
                .black()
                .bgGreen("We started this configuration class %s", this)
                .bgGreen("Telling me that she's gone")
                .reset();
    }
}
