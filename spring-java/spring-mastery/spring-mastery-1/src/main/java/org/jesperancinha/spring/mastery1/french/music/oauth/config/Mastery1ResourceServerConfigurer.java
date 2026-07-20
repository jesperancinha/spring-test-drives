package org.jesperancinha.spring.mastery1.french.music.oauth.config;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures the Resource Server side: every request that is not one of the Authorization Server's own
 * protocol endpoints (see {@link Mastery1AuthorizationServerConfigurer}) is handled by this filter chain.
 * <p>
 * This replaces the legacy {@code @EnableResourceServer}/{@code ResourceServerConfigurerAdapter} from the
 * archived {@code spring-security-oauth2} library with {@code HttpSecurity.oauth2ResourceServer(...)},
 * pulled in via the {@code spring-boot-starter-security-oauth2-resource-server} starter, validating the
 * JWT bearer tokens issued by the Authorization Server against the {@link Mastery1TokenStoreConfig}
 * JWK material.
 */
@Configuration
public class Mastery1ResourceServerConfigurer {

    @Bean
    public InMemoryAuditEventRepository repository() {
        final InMemoryAuditEventRepository inMemoryAuditEventRepository = new InMemoryAuditEventRepository();
        ConsolerizerComposer.out(" ")
                .magenta("Initializing bean")
                .blue(inMemoryAuditEventRepository)
                .toConsoleLn();
        ConsolerizerComposer.out(" ")
                .green("An event repository is always needed. Exposing the auditevents endpoints")
                .yellow("makes no sense if we don't keep the events somewhere")
                .toConsoleLn();
        return inMemoryAuditEventRepository;
    }

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/open/**").permitAll()
                        .requestMatchers("/artist/**").permitAll()
                        .requestMatchers("/member/**").permitAll()
                        .requestMatchers("/auditevents/**").permitAll()
                        .requestMatchers("/concerts/**").authenticated()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                // Supports the authorization_code grant's browser login page (redirected to by
                // Mastery1AuthorizationServerConfigurer's LoginUrlAuthenticationEntryPoint).
                .formLogin(Customizer.withDefaults())
                .exceptionHandling(handling -> handling.accessDeniedHandler(new BearerTokenAccessDeniedHandler()))
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

}
