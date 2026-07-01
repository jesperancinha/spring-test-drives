package org.jesperancinha.sftd.flash29.security.services;

import org.jesperancinha.sftd.flash29.security.domain.FlashUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Flash29AuthenticationProvider implements AuthenticationProvider {
    private static final List<FlashUser> FLASH_USERS = Arrays.asList(
            FlashUser.builder().name("admin").password("admin").roles(Arrays.asList("ROLE_ADMIN", "ROLE_READ")).build(),
            FlashUser.builder().name("admin2").password("admin").roles(Arrays.asList("ROLE_ADMIN", "ROLE_READ", "ROLE_WRITE")).build(),
            FlashUser.builder().name("user").password("user").roles(Collections.singletonList("ROLE_USER")).build()
    );

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        final var username = authentication.getName();
        final var password = authentication.getCredentials().toString();
        final var flashLoginUser = FlashUser.builder().name(username).password(password).build();
        final var userOptional =
                FLASH_USERS.stream()
                        .filter(flashUser ->
                                flashUser.getName().equals(flashLoginUser.getName()) &&
                                        flashUser.getPassword().equals(flashLoginUser.getPassword())
                        )
                        .findAny();
        final var grantedAuthorities =
                userOptional.orElseThrow(() ->
                        new BadCredentialsException(String.format("User %s cannot be authenticated!", username)))
                        .getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
