package org.jesperancinha.spring.tutorial.domain.monitoring.lyrics;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Details(
        @JsonProperty("database") String database,
        @JsonProperty("result") Integer result,
        @JsonProperty("validationQuery") String validationQuery,
        @JsonProperty("total") Long total,
        @JsonProperty("free") Long free,
        @JsonProperty("threshold") Long threshold,
        @JsonProperty("lyric") String lyric,
        @JsonProperty("path") String path,
        @JsonProperty("exists") Boolean exists) {
}
