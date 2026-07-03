package org.jesperancinha.spring.flash419.contexes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Jacksonized
public record SongObject(
    @JsonProperty("allSongs")
    List<String> allSongs
) {}
