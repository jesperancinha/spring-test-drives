package org.jesperancinha.sftd.flash310.jsoncomponent.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class Guitar {

    @JsonProperty("brand")
    String brand;

    @JsonProperty("model")
    String model;

    @JsonProperty("value")
    Long value;

    @JsonProperty("currency")
    String currency;
}
