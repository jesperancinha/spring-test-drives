package org.jesperancinha.std.flash49.actuator.oauth.config;

import org.jesperancinha.std.flash49.actuator.oauth.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Component
@Primary
public class Flash49AuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public Flash49AuthenticationProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        if (authentication.getName() == null || authentication.getCredentials() == null) {
            return null;
        }
        if (authentication.getName()
                .isEmpty() || authentication.getCredentials()
                .toString()
                .isEmpty()) {
            return null;
        }
        final var user = this.userRepository.findUserByName(authentication.getName());

        if (Objects.nonNull(user)) {
            final String providedUser = authentication.getName();
            final String providedUserPassword = (String) authentication.getCredentials();
            if (providedUser.equalsIgnoreCase(user.getName()) && passwordEncoder.matches(providedUserPassword, user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
            }
        }

        throw new UsernameNotFoundException("Invalid username/password.");
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}