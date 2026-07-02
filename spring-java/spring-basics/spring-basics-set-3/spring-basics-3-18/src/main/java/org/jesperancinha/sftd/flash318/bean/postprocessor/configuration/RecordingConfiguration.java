package org.jesperancinha.sftd.flash318.bean.postprocessor.configuration;

import org.jesperancinha.sftd.flash318.bean.postprocessor.domain.Recording;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecordingConfiguration {

    @Bean
    public Recording recording() {
        return new Recording("Bohemian Raphsody", 19670L, "Rockfield Studio 1", "Monmouth, South Wales");
    }
}
