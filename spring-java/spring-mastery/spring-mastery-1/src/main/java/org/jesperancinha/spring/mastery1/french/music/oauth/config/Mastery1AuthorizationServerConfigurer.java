package org.jesperancinha.spring.mastery1.french.music.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.time.Duration;
import java.util.UUID;

/**
 * Configures the OAuth2 Authorization Server protocol endpoints ({@code /oauth2/authorize},
 * {@code /oauth2/token}, {@code /oauth2/jwks}, ...) using Spring Authorization Server, which is now
 * bundled directly in Spring Security ({@code spring-security-oauth2-authorization-server}, pulled in
 * here via the {@code spring-boot-starter-security-oauth2-authorization-server} starter).
 * <p>
 * This replaces the legacy {@code @EnableAuthorizationServer}/{@code AuthorizationServerConfigurerAdapter}
 * and {@code ClientDetailsServiceConfigurer} from the archived {@code spring-security-oauth2} library with
 * a {@link RegisteredClientRepository} bean and a plain {@link SecurityFilterChain}.
 * <p>
 * Note the resource-owner-password-credentials grant ({@code password}) that the old configuration
 * exposed is intentionally not carried over: Spring Authorization Server does not support it, in line
 * with the OAuth 2.1 recommendation to drop it. {@code client_credentials} is registered instead for
 * straightforward machine-to-machine testing (e.g. via curl), alongside {@code authorization_code} and
 * {@code refresh_token} for the browser-based flow.
 */
@Configuration
public class Mastery1AuthorizationServerConfigurer {

    private static final String CLIENT_ID = "mastery1-client";
    private static final String CLIENT_SECRET = "mastery1";
    private static final String SCOPE_READ = "read";
    private static final String SCOPE_WRITE = "write";
    private static final Duration ACCESS_TOKEN_TIME_TO_LIVE = Duration.ofHours(1);
    private static final Duration REFRESH_TOKEN_TIME_TO_LIVE = Duration.ofDays(30);

    private final PasswordEncoder passwordEncoder;

    public Mastery1AuthorizationServerConfigurer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2AuthorizationServer(authorizationServer ->
                        http.securityMatcher(authorizationServer.getEndpointsMatcher()))
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .exceptionHandling(exceptions -> exceptions.defaultAuthenticationEntryPointFor(
                        new LoginUrlAuthenticationEntryPoint("/login"),
                        new MediaTypeRequestMatcher(MediaType.TEXT_HTML)))
                // Disabled for this teaching demo so the token endpoint can be exercised directly with
                // curl/Postman using client_secret_basic, without first establishing a session/CSRF token.
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        final RegisteredClient mastery1Client = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(CLIENT_ID)
                .clientSecret(passwordEncoder.encode(CLIENT_SECRET))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://127.0.0.1:8081/login/oauth2/code/mastery1-client")
                .scope(SCOPE_READ)
                .scope(SCOPE_WRITE)
                .clientSettings(ClientSettings.builder()
                        // "trusted" client for this demo: skip the consent screen, matching the old
                        // configuration's allowFormAuthenticationForClients()/"trust" scope intent.
                        .requireAuthorizationConsent(false)
                        .build())
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(ACCESS_TOKEN_TIME_TO_LIVE)
                        .refreshTokenTimeToLive(REFRESH_TOKEN_TIME_TO_LIVE)
                        .build())
                .build();

        return new InMemoryRegisteredClientRepository(mastery1Client);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

}
