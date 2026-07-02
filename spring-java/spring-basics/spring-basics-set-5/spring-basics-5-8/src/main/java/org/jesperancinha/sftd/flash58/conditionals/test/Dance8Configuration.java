package org.jesperancinha.sftd.flash58.conditionals.test;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.actuate.autoconfigure.web.server.ConditionalOnManagementPort;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
@ConditionalOnManagementPort(ManagementPortType.SAME)
public class Dance8Configuration {

    public Dance8Configuration() {
        ConsolerizerComposer.outSpace()
                .black()
                .bgYellow("We started this configuration class %s", this)
                .bgYellow("Happiness")
                .reset();
    }

}
