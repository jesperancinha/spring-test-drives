package org.jesperancinha.sftd.flash57.secured.security;

import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Profile("acc")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class Flash57SecuredConfiguration {
}
