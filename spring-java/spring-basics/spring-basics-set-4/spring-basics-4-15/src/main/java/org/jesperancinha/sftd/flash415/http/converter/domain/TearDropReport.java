package org.jesperancinha.sftd.flash415.http.converter.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class TearDropReport {

    @JsonProperty("totalCount")
    Long totalCount;

    @JsonProperty("average")
    Long average;

    @JsonProperty("stdDeviation")
    Long stdDeviation;

    @JsonProperty("tearDropTypeSet")
    Set<TearDropType> tearDropTypeSet;
}

