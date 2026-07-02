package org.jesperancinha.sftd.flash413.boot.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFlash413Configuration {
    @Bean
    public String power() {
        return "The power of goodbye";
    }
}
