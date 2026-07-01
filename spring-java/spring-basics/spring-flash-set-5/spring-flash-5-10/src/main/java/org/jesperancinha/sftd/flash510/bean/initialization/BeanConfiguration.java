package org.jesperancinha.sftd.flash510.bean.initialization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public Seed blackBean() {
        final var seed = new Seed();
        seed.setSeedState(SeedState.DRY);
        seed.setDescription("Black Bean");
        return seed;
    }

}
