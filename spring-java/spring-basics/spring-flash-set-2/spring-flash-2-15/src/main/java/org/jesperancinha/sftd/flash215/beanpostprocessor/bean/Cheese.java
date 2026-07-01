package org.jesperancinha.sftd.flash215.beanpostprocessor.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class Cheese {

    @JsonProperty("name")
    String name;

    @JsonProperty("url")
    String url;

    @JsonProperty(value = "checks")
    List<String> checks;

    public Cheese() {
        name = null;
        url = null;
        checks = null;
    }

}
