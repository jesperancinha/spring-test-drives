package org.jesperancinha.sftd.flash415.http.converter.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class TearDrop {
    @JsonProperty("count")
    Long count;

    @JsonProperty("tearDropType")
    TearDropType tearDropType;
}
