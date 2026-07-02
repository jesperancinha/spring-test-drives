package org.jesperancinha.sftd.flash17.cipher.controllers;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.BLUE;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.ORANGE;

@Profile("prod")
@RestController
public class Flash17CreateController {

    private JdbcUserDetailsManager jdbcUserDetailsManager;

    private final PasswordEncoder passwordEncoder;

    public Flash17CreateController(final JdbcUserDetailsManager jdbcUserDetailsManager, PasswordEncoder passwordEncoder) {
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "/open/create")
    public ResponseEntity<Object> createUser(
            @RequestHeader(name = "name")
                    String name,
            @RequestHeader(name = "password")
                    String password,
            @RequestHeader(name = "role")
                    String role) {
        jdbcUserDetailsManager.createUser(User.withUsername(name).password(passwordEncoder.encode(password)).roles(role).build());
        BLUE.printGenericTitleLn("Received and created user via POST with CSRF disabled");
        ORANGE.printGenericLn("Name: %s", name);
        ORANGE.printGenericLn("Password: %s", password);
        ORANGE.printGenericLn("Role: %s", role);
        return ResponseEntity.ok().build();
    }
}
