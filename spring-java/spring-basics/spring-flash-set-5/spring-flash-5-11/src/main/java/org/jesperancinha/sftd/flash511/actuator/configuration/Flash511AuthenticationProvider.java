package org.jesperancinha.sftd.flash511.actuator.configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Flash511AuthenticationProvider implements AuthenticationProvider {
    private static final List<Flash511User> FLASH_USERS = Arrays.asList(
            Flash511User.FlashUserBuilder.flashUserBuilder().name("admin").password("admin").roles("ROLE_ADMIN").build(),
            Flash511User.FlashUserBuilder.flashUserBuilder().name("admin2").password("admin").roles("ROLE_ADMIN").build()
    );

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        final var username = authentication.getName();
        final var password = authentication.getCredentials().toString();
        final var flashLoginUser = Flash511User.FlashUserBuilder.flashUserBuilder().name(username).password(password).build();
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
