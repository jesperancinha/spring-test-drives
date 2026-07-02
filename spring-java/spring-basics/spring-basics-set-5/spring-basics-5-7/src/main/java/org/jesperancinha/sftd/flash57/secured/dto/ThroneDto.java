package org.jesperancinha.sftd.flash57.secured.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.jesperancinha.sftd.flash57.secured.services.ThroneType;

@Value
@Builder
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class ThroneDto {
    ThroneType throneType;
    String keeper;
}
