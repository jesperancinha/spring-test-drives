package org.jesperancinha.sftd.flash320.bean.required.configuration;

import org.jesperancinha.sftd.flash320.bean.required.domain.People;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("incomplete")
@ImportResource("classpath:bean2.xml")
public class PeopleConfigurationIncomplete {

    @Bean("people")
    @Profile("incomplete")
    public People peopleIncomplete() {
        return new People();
    }
}
