package org.jesperancinha.sftd.flash37.aop.detail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Jacksonized
public record TicketDto(
    @JsonProperty("id")
    Long id,

    @JsonProperty("artist")
    String artist,

    @JsonProperty("show")
    String show,

    @JsonProperty("localDateTime")
    LocalDateTime localDateTime,

    @JsonProperty("uuid")
    UUID uuid
) {}
