package org.jesperancinha.sftd.flash316.requestparam.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Think {
    private String artist;
    private String thinkString;
}
