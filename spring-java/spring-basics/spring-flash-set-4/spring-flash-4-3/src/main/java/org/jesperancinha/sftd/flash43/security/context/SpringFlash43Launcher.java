package org.jesperancinha.sftd.flash43.security.context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@SpringBootApplication
@Controller
@ImportResource("classpath:/WEB-INF/spring/spring-security.xml")
public class SpringFlash43Launcher {

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @RequestMapping("/")
    public String revealSecret(Map<String, Object> model) {
        return "index";
    }

    @RequestMapping("/origin")
    public String revealPartOfTheSecret(Map<String, Object> model) {
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash43Launcher.class, args);
    }
}
