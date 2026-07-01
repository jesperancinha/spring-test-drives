package org.jesperancinha.sftd.mastery2.portuguese.music.configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;

public class Mastery2AuthenticationProvider implements AuthenticationProvider {
    private static final List<Mastery2User> FLASH_USERS = Arrays.asList(
            Mastery2User.builder().name("admin").password("admin").role("ROLE_ADMIN").build(),
            Mastery2User.builder().name("user").password("user").role("ROLE_USER").build()
    );

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        final var username = authentication.getName();
        final var password = authentication.getCredentials().toString();
        final var flashLoginUser = Mastery2User
                .builder()
                .name(username)
                .password(password)
                .build();
        final var userOptional =
                FLASH_USERS.stream()
                        .filter(flashLoginUser::equals)
                        .findAny();
        final var grantedAuthorities = List.of(
                new SimpleGrantedAuthority(
                        userOptional.orElseThrow(() ->
                                new BadCredentialsException(String.format("User %s cannot be authenticated!", username)))
                                .getRole()));
        return new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}