package org.jesperancinha.sftd.flash419.contexes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class SongObject {
    @JsonProperty("allSongs")
    List<String> allSongs;
}
