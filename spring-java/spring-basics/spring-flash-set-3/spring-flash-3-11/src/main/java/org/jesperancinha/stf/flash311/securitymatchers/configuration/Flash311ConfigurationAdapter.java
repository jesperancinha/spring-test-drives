package org.jesperancinha.stf.flash311.securitymatchers.configuration;

import org.jesperancinha.stf.flash311.securitymatchers.services.Flash311AuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                .authorizeRequests()
                .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("RED")
                .requestMatchers(new AntPathRequestMatcher("/user/**")).hasRole("BLUE")
                .requestMatchers(new AntPathRequestMatcher("/"))
                .authenticated()
                .requestMatchers(new AntPathRequestMatcher("/normal/**"))
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher("/static/test.html", HttpMethod.GET.toString()))
                .authenticated()
                .requestMatchers(new AntPathRequestMatcher("/static/index.html", HttpMethod.GET.toString()))
                .permitAll()
                .and()
                .formLogin().defaultSuccessUrl("/static/index.html", true)
                .and().build();
    }

}