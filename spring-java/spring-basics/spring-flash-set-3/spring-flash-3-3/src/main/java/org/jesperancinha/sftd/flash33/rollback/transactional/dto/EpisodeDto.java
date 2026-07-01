package org.jesperancinha.sftd.flash33.rollback.transactional.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record EpisodeDto(
    @JsonProperty("id")
    Long id,

    @JsonProperty("name")
    String name
) {}
