package org.jesperancinha.sftd.mastery1.french.music.oauth.config;

import org.jesperancinha.sftd.mastery1.french.music.oauth.service.Mastery1UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class Mastery1OAuth2WebSecurityConfigurer {

    private final AuthenticationProvider authenticationProvider;

    private final Mastery1UserDetailsService mastery1UserDetailsService;

    private final PasswordEncoder passwordEncoder;

    public Mastery1OAuth2WebSecurityConfigurer(AuthenticationProvider authenticationProvider, Mastery1UserDetailsService mastery1UserDetailsService, PasswordEncoder passwordEncoder) {
        this.authenticationProvider = authenticationProvider;
        this.mastery1UserDetailsService = mastery1UserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return http.userDetailsService(mastery1UserDetailsService)
                .authenticationProvider(authenticationProvider).build();
    }


}