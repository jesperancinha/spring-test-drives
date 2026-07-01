package org.jesperancinha.sftd.flash511.actuator.sessions.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class JeorgFlash511Configuration {

    @Bean
    public String pepper() {
        return "Give it away, give it away, give it away, now";
    }
}
