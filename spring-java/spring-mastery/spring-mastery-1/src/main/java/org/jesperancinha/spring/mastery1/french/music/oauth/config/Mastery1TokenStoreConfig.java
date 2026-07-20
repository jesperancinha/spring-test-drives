package org.jesperancinha.spring.mastery1.french.music.oauth.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * Provides the token-signing material shared by the Authorization Server (which signs access/refresh
 * tokens as JWTs) and the Resource Server (which verifies them).
 * <p>
 * The legacy {@code spring-security-oauth2} library modelled this as a {@code TokenStore} - either
 * in-memory or, as this module previously did, JDBC-backed via {@code JdbcTokenStore} plus
 * {@code DefaultTokenServices} - because its opaque tokens had to be persisted and looked up on every
 * request. Current Spring Security / Spring Authorization Server instead issues self-contained, signed
 * JWTs: there is nothing to "store", the token itself carries its claims and is verified against the
 * public key exposed at the {@code /oauth2/jwks} endpoint. This class configures that JWK material
 * (an in-memory RSA key pair, generated at startup - fine for this teaching demo, a real deployment
 * would load a persisted/rotated key) and the resulting {@link JwtDecoder}.
 */
@Configuration
public class Mastery1TokenStoreConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        final KeyPair keyPair = generateRsaKey();
        final RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        final RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        final RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        final JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    private static KeyPair generateRsaKey() {
        try {
            final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to generate an RSA key pair for JWT signing", ex);
        }
    }

}
