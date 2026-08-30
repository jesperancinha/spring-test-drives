package org.jesperancinha.spring.mastery1.french.music.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Enables annotation-driven web and method security for this module.
 * <p>
 * The actual {@link org.springframework.security.web.SecurityFilterChain} beans live in
 * {@link Mastery1AuthorizationServerConfigurer} (Authorization Server protocol endpoints, {@code @Order(1)})
 * and {@link Mastery1ResourceServerConfigurer} (everything else, including Resource Server JWT
 * validation, {@code @Order(2)}) - a single application can no longer combine
 * {@code @EnableAuthorizationServer} and {@code @EnableResourceServer} on the same
 * {@code WebSecurityConfigurerAdapter} the way the legacy {@code spring-security-oauth2} library did, so
 * each side gets its own ordered filter chain instead.
 * <p>
 * {@code Mastery1AuthenticationProvider} and {@code Mastery1UserDetailsService} no longer need to be
 * wired explicitly here either: Spring Security's {@code AuthenticationConfiguration} automatically
 * builds the global {@code AuthenticationManager} from any {@code AuthenticationProvider}/
 * {@code UserDetailsService} beans found in the application context.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class Mastery1OAuth2WebSecurityConfigurer {
}
