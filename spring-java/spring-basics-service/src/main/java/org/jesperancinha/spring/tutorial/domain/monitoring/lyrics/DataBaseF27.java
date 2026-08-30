package org.jesperancinha.spring.tutorial.domain.monitoring.lyrics;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DataBaseF27(
        @JsonProperty("status") String status,
        @JsonProperty("details") Details details) {
}
