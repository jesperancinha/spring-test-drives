package org.jesperancinha.sftd.flash58.conditionals.test;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnJava.Range.EQUAL_OR_NEWER;

@Profile("test")
@Configuration
@ConditionalOnJava(range = EQUAL_OR_NEWER,
        value = JavaVersion.SEVENTEEN)
public class Dance16Configuration {

    public Dance16Configuration() {
        ConsolerizerComposer.outSpace()
                .black()
                .bgYellow("We started this configuration class %s", this)
                .bgYellow("Your lies are haunting me")
                .reset();
    }
}
