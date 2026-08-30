package org.jesperancinha.spring.tutorial.domain.monitoring.lyrics;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Ping(
        @JsonProperty("status") String status) {
}
