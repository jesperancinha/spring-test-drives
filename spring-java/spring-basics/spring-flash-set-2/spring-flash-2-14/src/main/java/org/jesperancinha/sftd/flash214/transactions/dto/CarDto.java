package org.jesperancinha.sftd.flash214.transactions.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class CarDto {
    @JsonProperty("id")
    Long id;
    @JsonProperty("model")
    String model;
    @JsonProperty("brand")
    String brand;
    @JsonProperty("carYear")
    Integer carYear;
    @JsonProperty("movieAppearances")
    String[] movieAppearances;

}
