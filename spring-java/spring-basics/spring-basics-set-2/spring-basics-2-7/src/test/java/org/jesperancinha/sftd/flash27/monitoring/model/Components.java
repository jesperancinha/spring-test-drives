package org.jesperancinha.sftd.flash27.monitoring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Components(
        @JsonProperty("ssl") Object ssl,
        @JsonProperty("db") DataBaseF27 db,
                         @JsonProperty("diskSpace") DiskSpace diskSpace,
                         @JsonProperty("flash27") Flash27 flash27,
                         @JsonProperty("ping") Ping ping) {
}
