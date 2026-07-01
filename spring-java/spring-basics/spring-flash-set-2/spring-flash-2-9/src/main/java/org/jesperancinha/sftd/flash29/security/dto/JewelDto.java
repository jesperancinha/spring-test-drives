package org.jesperancinha.sftd.flash29.security.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.jesperancinha.sftd.flash29.security.services.JewelType;

@Value
@Builder
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class JewelDto {

    @JsonProperty("jewelType")
    JewelType jewelType;

    @JsonProperty("guardian")
    String guardian;
}
