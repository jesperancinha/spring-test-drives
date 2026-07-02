package org.jesperancinha.sftd.flash57.secured.configuration;

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

public class Flash57AuthenticationProvider implements AuthenticationProvider {
    private static final List<Flash57User> FLASH_USERS = Arrays.asList(
            Flash57User.builder().name("king").password("admin").roles(List.of("ROLE_RULER", "ROLE_DANCER", "ROLE_MERCHANT")).build(),
            Flash57User.builder().name("queen").password("admin").roles(List.of("ROLE_RULER", "ROLE_DANCER", "ROLE_MERCHANT")).build(),
            Flash57User.builder().name("dancer").password("admin").roles(Collections.singletonList("ROLE_DANCER")).build(),
            Flash57User.builder().name("merchant").password("admin").roles(Collections.singletonList("ROLE_MERCHANT")).build()
    );

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        final var username = authentication.getName();
        final var password = authentication.getCredentials().toString();
        final var flashLoginUser = Flash57User.builder().name(username).password(password).build();
        final var userOptional =
                FLASH_USERS.stream()
                        .filter(flashLoginUser::equals)
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
