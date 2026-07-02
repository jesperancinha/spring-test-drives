package org.jesperancinha.sftd.flash44.handler.mapping;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record SpringBeanMappedInstance(
    @JsonProperty("name")
    String name,

    @JsonProperty("order")
    Integer order,

    @JsonProperty("type")
    String type
) {}
