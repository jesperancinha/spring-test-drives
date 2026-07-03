package org.jesperancinha.spring.flash317.profile.configuration.inside;

import org.jesperancinha.spring.flash317.profile.Messenger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration("insideConfiguration")
public class MessengerConfiguration {
    @Bean
    @Profile("inside")
    public Messenger messenger() {
        return new Messenger("Hello from the other side");
    }
}
