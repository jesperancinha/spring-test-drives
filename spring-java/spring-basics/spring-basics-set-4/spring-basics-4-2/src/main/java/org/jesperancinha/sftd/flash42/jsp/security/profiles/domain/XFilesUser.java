package org.jesperancinha.sftd.flash42.jsp.security.profiles.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(onConstructor_ = @JsonCreator)
@EqualsAndHashCode
public class XFilesUser {
    @JsonProperty("name")
    String name;
    @JsonProperty("password")
    String password;
    @JsonProperty("role")
    @Exclude
    String role;
}
