package org.jesperancinha.spring.flash214.transactions.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CarDto(
    @JsonProperty("id")
    Long id,
    @JsonProperty("model")
    String model,
    @JsonProperty("brand")
    String brand,
    @JsonProperty("carYear")
    Integer carYear,
    @JsonProperty("movieAppearances")
    String[] movieAppearances
) {}
