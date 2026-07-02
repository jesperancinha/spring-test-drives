package org.jesperancinha.spring.flash27.monitoring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Ping(
        @JsonProperty("status") String status) {
}
