package org.jesperancinha.sftd.flash215.beanpostprocessor.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Jacksonized
public record Cheese(
    @JsonProperty("name")
    String name,

    @JsonProperty("url")
    String url,

    @JsonProperty("checks")
    List<String> checks
) {}
