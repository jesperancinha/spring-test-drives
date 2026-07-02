package org.jesperancinha.sftd.flash57.secured.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class Flash57User {

    @JsonProperty("name")
    String name;
    @JsonProperty("password")
    String password;
    @JsonProperty("roles")
    @Exclude
    List<String> roles;
}
