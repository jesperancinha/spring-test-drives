package org.jesperancinha.spring.tutorial.domain.monitoring.lyrics;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Health(
        @JsonProperty("components") Components components,
        @JsonProperty("status") String status) {
}
