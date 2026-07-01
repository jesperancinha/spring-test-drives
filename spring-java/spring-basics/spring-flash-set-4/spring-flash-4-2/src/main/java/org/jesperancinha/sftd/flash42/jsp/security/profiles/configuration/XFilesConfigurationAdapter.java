package org.jesperancinha.sftd.flash42.jsp.security.profiles.configuration;

import org.jesperancinha.sftd.flash42.jsp.security.profiles.provider.XFilesAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;

@Configuration
@EnableWebSecurity
public class XFilesConfigurationAdapter {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

            GREEN.printGenericLn("Note that ADMIN in this case is a short statement for ROLE_ADMIN");
            GREEN.printGenericLn("When we assign our SimpleGrantedAuthority to our Authentication, we give it a role as parameter");
            GREEN.printGenericLn("The role is an extended name. In our case it will be ROLE_ADMIN");
            return http
                    .authenticationProvider(new XFilesAuthenticationProvider())
                    .authorizeRequests()
                    .requestMatchers(new AntPathRequestMatcher("/**"))
                    .hasAnyRole("ADMIN", "USER")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .and().build();
        }
}