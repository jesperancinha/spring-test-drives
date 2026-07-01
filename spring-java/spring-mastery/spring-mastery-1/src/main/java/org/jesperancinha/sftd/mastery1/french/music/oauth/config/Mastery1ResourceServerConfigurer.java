package org.jesperancinha.sftd.mastery1.french.music.oauth.config;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableResourceServer
public class Mastery1ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID)
                .stateless(false);
    }

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

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(new AntPathRequestMatcher("/open/**"))
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher("/artist/**"))
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher("/member/**"))
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher("/auditevents/**"))
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher("/concerts/**"))
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler())
                .and()
                .csrf()
                .disable();
    }

}