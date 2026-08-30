package org.jesperancinha.spring.tutorial.domain.monitoring.lyrics;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Components(
        @JsonProperty("ssl") Object ssl,
        @JsonProperty("db") DataBaseF27 db,
                         @JsonProperty("diskSpace") DiskSpace diskSpace,
                         @JsonProperty("lyrics") Lyrics lyrics,
                         @JsonProperty("ping") Ping ping) {
}
