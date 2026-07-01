package org.jesperancinha.sftd.flash44.handler.mapping;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class SpringBeanMappedInstance {
    @JsonProperty("name")
    String name;

    @JsonProperty("order")
    Integer order;

    @JsonProperty("type")
    String type;
}
