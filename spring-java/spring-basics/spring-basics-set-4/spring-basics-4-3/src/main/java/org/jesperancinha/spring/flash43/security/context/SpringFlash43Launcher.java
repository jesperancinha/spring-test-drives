package org.jesperancinha.spring.flash43.security.context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SpringBootApplication
@ImportResource("classpath:/WEB-INF/spring/spring-security.xml")
public class SpringFlash43Launcher {

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    static void main(String[] args) {
        SpringApplication.run(SpringFlash43Launcher.class, args);
    }
}
