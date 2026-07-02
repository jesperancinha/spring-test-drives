package org.jesperancinha.sftd.flash57.secured.security;

import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Profile("test")
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class Flash57Jsr250Configuration {
}
