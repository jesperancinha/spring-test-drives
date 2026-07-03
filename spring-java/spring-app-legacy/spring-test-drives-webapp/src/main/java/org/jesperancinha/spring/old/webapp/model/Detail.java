package org.jesperancinha.spring.old.webapp.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Detail {

    private final String name;

    private final String city;
}
