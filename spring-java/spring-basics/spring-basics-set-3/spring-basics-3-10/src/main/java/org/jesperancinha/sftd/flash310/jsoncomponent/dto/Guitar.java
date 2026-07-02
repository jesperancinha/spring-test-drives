package org.jesperancinha.sftd.flash310.jsoncomponent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record Guitar(
    @JsonProperty("brand")
    String brand,

    @JsonProperty("model")
    String model,

    @JsonProperty("value")
    Long value,

    @JsonProperty("currency")
    String currency
) {}
