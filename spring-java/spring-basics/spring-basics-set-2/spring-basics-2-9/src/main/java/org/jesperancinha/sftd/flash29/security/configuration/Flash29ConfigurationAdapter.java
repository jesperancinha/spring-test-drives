package org.jesperancinha.sftd.flash29.security.configuration;

import org.jesperancinha.sftd.flash29.security.services.Flash29AuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class Flash29ConfigurationAdapter {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return  http
                .authenticationProvider(new Flash29AuthenticationProvider())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}