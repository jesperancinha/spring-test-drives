package org.jesperancinha.sftd.flash27.monitoring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Flash27(
        @JsonProperty("status") String status,
        @JsonProperty("details") Details details
) {
}
