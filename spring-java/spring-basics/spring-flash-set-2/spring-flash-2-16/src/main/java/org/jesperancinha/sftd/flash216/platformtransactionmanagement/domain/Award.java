package org.jesperancinha.sftd.flash216.platformtransactionmanagement.domain;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Builder
@Jacksonized
public record Award(
    String artist,
    String award,
    LocalDateTime awardDate
) {}
