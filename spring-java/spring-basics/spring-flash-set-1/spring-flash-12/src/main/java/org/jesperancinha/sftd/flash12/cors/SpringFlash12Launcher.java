package org.jesperancinha.sftd.flash12.cors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringFlash12Launcher {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash12Launcher.class, args);
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(
                    @NonNull
                            CorsRegistry registry) {
                registry.addMapping("/protected").allowedOrigins("http://spring-flash-12:8081");
            }
        };
    }
}
