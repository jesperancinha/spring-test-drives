package org.jesperancinha.sftd.flash317.profile.configuration.outside;

import org.jesperancinha.sftd.flash317.profile.Messenger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration("outsideConfiguration")
public class MessengerConfiguration {
    @Bean
    @Profile("outside")
    public Messenger messenger() {
        return new Messenger("Hello from the outside");
    }
}
