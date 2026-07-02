package org.jesperancinha.spring.flash27.monitoring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DiskSpace(
        @JsonProperty("status") String status,
        @JsonProperty("details") Details details
) {
}
