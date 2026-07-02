package org.jesperancinha.sftd.flash52.config;

import org.jesperancinha.sftd.flash52.domain.Tomato;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoodTomatoSpringConfiguration {

    @Bean(initMethod = "growTomato",
            destroyMethod = "eatTomato")
    public Tomato tomato(final Tomato originalTomato) {
        return originalTomato;
    }
}
