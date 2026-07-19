package org.jesperancinha.spring.flash57.secured.security;

import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Profile("acc")
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = false)
public class Flash57SecuredConfiguration {
}
