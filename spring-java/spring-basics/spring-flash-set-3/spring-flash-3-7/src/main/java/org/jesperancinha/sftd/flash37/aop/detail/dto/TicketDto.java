package org.jesperancinha.sftd.flash37.aop.detail.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class TicketDto {
    @JsonProperty("id")
    Long id;

    @JsonProperty("artist")
    String artist;

    @JsonProperty("show")
    String show;

    @JsonProperty("localDateTime")
    LocalDateTime localDateTime;

    @JsonProperty("uuid")
    UUID uuid;
}
