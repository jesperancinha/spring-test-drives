package org.jesperancinha.sftd.flash215.beanpostprocessor.configuration;

import org.jesperancinha.sftd.flash215.beanpostprocessor.bean.Cheese;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class CheesesConfiguration {

    @Bean
    public Cheese camembert() {
        return Cheese
                .builder()
                .name("Camembert")
                .url("https://en.wikipedia.org/wiki/Camembert")
                .checks(new ArrayList<>())
                .build();
    }

    @Bean
    public Cheese brie() {
        return Cheese
                .builder()
                .name("Brie")
                .url("https://en.wikipedia.org/wiki/Brie")
                .checks(new ArrayList<>())
                .build();
    }

    @Bean
    public Cheese saoJorge() {
        return Cheese
                .builder()
                .name("Sao Jorge")
                .url("https://en.wikipedia.org/wiki/S%C3%A3o_Jorge_cheese")
                .checks(new ArrayList<>())
                .build();
    }
}
