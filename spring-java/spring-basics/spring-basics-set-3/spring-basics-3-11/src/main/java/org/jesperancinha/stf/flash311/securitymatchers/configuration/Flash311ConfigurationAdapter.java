package org.jesperancinha.stf.flash311.securitymatchers.configuration;

import org.jesperancinha.stf.flash311.securitymatchers.services.Flash311AuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;

@Configuration
public class Flash311ConfigurationAdapter {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        GREEN.printGenericLn("Note that RED and BLUE in this case is a short statement for ROLE_RED and ROlE_BLUE respectively");
        GREEN.printGenericLn("When we assign our SimpleGrantedAuthority to our Authentication, we give it a role as parameter");
        GREEN.printGenericLn("The role is an extended name. In our case it will be ROLE_RED");
        GREEN.printGenericLn("The same goes to all other roles like ROLE_BLUE");
        return http
                .authenticationProvider(new Flash311AuthenticationProvider())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("RED")
                        .requestMatchers("/user/**").hasRole("BLUE")
                        .requestMatchers("/").authenticated()
                        .requestMatchers("/normal/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/static/test.html").authenticated()
                        .requestMatchers(HttpMethod.GET, "/static/index.html").permitAll()
                )
                .formLogin(form -> form.defaultSuccessUrl("/static/index.html", true))
                .build();
    }

}