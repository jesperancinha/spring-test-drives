package org.jesperancinha.stf.flash311.securitymatchers.domain;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record FlashUser(
    String name,
    String password,
    String role
) {}
