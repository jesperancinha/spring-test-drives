package org.jesperancinha.sftd.mastery2.portuguese.music.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;

@Configuration
public class Mastery2ConfigurationAdapter {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        GREEN.printGenericLn("Note that ADMIN in this case is a short statement for ROLE_ADMIN");
        GREEN.printGenericLn("When we assign our SimpleGrantedAuthority to our Authentication, we give it a role as parameter");
        GREEN.printGenericLn("The role is an extended name. In our case it will be ROLE_ADMIN");
        return http.authenticationProvider(new Mastery2AuthenticationProvider())
                .authorizeRequests()
                .requestMatchers(new AntPathRequestMatcher("/portuguese/antonio")).hasRole("ADMIN")
                .requestMatchers( new AntPathRequestMatcher("/portuguese/antonio"))
                .authenticated()
                .requestMatchers(new AntPathRequestMatcher("/portuguese"))
                .permitAll()
                .and()
                .formLogin().and().build();
    }

}
