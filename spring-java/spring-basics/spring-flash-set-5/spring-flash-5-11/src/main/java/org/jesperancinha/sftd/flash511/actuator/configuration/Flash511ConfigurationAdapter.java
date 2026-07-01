package org.jesperancinha.sftd.flash511.actuator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Flash511ConfigurationAdapter {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authenticationProvider(new Flash511AuthenticationProvider())
                .authorizeRequests()
                .requestMatchers(new AntPathRequestMatcher("/**"))
                .permitAll()
                .and()
                .formLogin()
                .and()
                .csrf()
                .disable()
                .build();
    }

}