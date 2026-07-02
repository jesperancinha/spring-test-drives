package org.jesperancinha.sftd.flash17.cipher.controllers;


import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("test")
public class Flash17CSRFCreateController {

    private final JdbcUserDetailsManager jdbcUserDetailsManager;

    private final PasswordEncoder passwordEncoder;

    public Flash17CSRFCreateController(final JdbcUserDetailsManager jdbcUserDetailsManager, PasswordEncoder passwordEncoder) {
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/open/create/{name}/{password}/{role}")
    public void createUseViaGet(
            @PathVariable("name")
                    String name,
            @PathVariable("password")
                    String password,
            @PathVariable("role")
                    String role
    ) {
        jdbcUserDetailsManager.createUser(User.withUsername(name).password(passwordEncoder.encode(password)).roles(role).build());
        ConsolerizerComposer.outSpace()
                .red("NOTE: NEVER EVER MAKE CREATION REQUESTS VIA A GET REQUEST!")
                .bgRed("THIS IS HERE ONLY FOR DEMONSTRATION PURPOSES ONLY!")
                .blue("Received and created user via GET with CSRF protection active")
                .orange("Name: %s", name)
                .orange("Password: %s", password)
                .orange("Role: %s", role);
    }

    @PostMapping("/open/create/{name}/{password}/{role}")
    public void createUseViaPost(
            @PathVariable
                    String name,
            @PathVariable
                    String password,
            @PathVariable
                    String role
    ) {
    }
}
