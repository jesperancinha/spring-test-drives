package org.jesperancinha.sftd.flash33.rollback.transactional.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class EpisodeDto {

    @JsonProperty("id")
    Long id;

    @JsonProperty("name")
    String name;
}
