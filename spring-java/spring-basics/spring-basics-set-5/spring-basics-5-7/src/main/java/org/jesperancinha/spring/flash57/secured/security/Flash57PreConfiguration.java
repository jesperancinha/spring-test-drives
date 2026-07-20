package org.jesperancinha.spring.flash57.secured.security;

import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Profile({"prod", "!test and !acc"})
@EnableMethodSecurity(prePostEnabled = true)
public class Flash57PreConfiguration {
}
