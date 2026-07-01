package org.jesperancinha.sftd.flash57.secured.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Flash57ConfigurationAdapter {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authenticationProvider(new Flash57AuthenticationProvider())
                .authorizeRequests()
                .requestMatchers(new AntPathRequestMatcher("/**"))
                .hasAnyRole("RULER", "DANCER", "MERCHANT")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .csrf()
                .disable().build();
    }

}