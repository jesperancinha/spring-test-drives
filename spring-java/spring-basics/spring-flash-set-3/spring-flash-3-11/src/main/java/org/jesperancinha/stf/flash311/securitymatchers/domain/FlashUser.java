package org.jesperancinha.stf.flash311.securitymatchers.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class FlashUser {
    private String name;
    private String password;
    private String role;
}
