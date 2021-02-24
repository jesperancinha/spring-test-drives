package org.jesperancinha.std.flash17.cipher.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import static org.jesperancinha.console.consolerizer.ConsolerizerColor.BLUE;
import static org.jesperancinha.console.consolerizer.ConsolerizerColor.GREEN;

@Profile("prod")
@Configuration
@EnableWebSecurity
public class Flash17ConfigurationAdapter extends WebSecurityConfigurerAdapter {

    private JdbcUserDetailsManager jdbcUserDetailsManager;
    private PasswordEncoder passwordEncoder;

    public Flash17ConfigurationAdapter(final JdbcUserDetailsManager jdbcUserDetailsManager, final PasswordEncoder passwordEncoder) {
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/open/**")
                .permitAll()
                .antMatchers("/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and().csrf().disable();
        GREEN.printGenericLn("We disable CSRF given that it interferes with POST requests");
        BLUE.printGenericLn("From: https://docs.spring.io/spring-security/site/docs/3.2.0.CI-SNAPSHOT/reference/html/csrf.html");
        BLUE.printGenericLn("13.3 When to use CSRF protection\n" +
                "When you use CSRF protection? Our recommendation is to use CSRF protection for any request that could be processed by a browser by normal users. If you are only creating a service that is used by non-browser clients, you will likely want to disable CSRF protection.");
        GREEN.printGenericLn("Note that ADMIN in this case is a short statement for ROLE_ADMIN");
        GREEN.printGenericLn("When we assign our SimpleGrantedAuthority to our Authentication, we give it a role as parameter");
        GREEN.printGenericLn("The role is an extended name. In our case it will be ROLE_ADMIN");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jdbcUserDetailsManager).passwordEncoder(passwordEncoder);
    }

}