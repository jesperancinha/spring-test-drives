package org.jesperancinha.sftd.action.actuator;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class JeorgActionActuatorConfigurations {

    @Profile("!test")
    @Bean
    public String actuator() {
        ConsolerizerComposer.outSpace()
                .black()
                .bgCyan("When the actuator dependency is added, only info and health are available as default endpoints")
                .reset();
        return "OK";
    }
}
