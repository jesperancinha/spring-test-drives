package org.jesperancinha.sftd.flash320.bean.required.configuration;

import org.jesperancinha.sftd.flash320.bean.required.domain.People;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!incomplete")
@ImportResource("classpath:bean1.xml")
public class PeopleConfiguration {

    @Bean("people")
    @Profile("!incomplete")
    public People peopleComplete() {
        final People people = new People();
        people.setHomesick(true);
        people.setColdAs("stone");
        people.setSlogan("Help the people");
        return people;
    }
}
