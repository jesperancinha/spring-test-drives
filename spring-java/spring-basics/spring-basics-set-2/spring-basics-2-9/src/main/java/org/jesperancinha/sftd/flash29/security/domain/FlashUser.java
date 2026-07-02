package org.jesperancinha.sftd.flash29.security.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@Builder
@EqualsAndHashCode
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class FlashUser {

    @JsonProperty("name")
    String name;

    @JsonProperty("password")
    String password;

    @JsonProperty("roles")
    List<String> roles;
}
