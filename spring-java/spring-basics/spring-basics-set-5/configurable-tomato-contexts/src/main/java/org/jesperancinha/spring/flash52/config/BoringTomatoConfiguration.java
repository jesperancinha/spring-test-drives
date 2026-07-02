package org.jesperancinha.spring.flash52.config;

import org.jesperancinha.spring.flash52.domain.Tomato;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoringTomatoConfiguration {
    @Bean
    public Tomato originalTomato() {
        return new Tomato("This is a good tomato!");
    }
}
