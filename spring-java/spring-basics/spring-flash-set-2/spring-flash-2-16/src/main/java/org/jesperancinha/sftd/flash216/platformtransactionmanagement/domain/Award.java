package org.jesperancinha.sftd.flash216.platformtransactionmanagement.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class Award {
    String artist;

    String award;

    LocalDateTime awardDate;
}
