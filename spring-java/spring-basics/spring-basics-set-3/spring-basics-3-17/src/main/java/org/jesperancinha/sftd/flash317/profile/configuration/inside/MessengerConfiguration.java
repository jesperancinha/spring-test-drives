package org.jesperancinha.sftd.flash317.profile.configuration.inside;

import org.jesperancinha.sftd.flash317.profile.Messenger;
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
