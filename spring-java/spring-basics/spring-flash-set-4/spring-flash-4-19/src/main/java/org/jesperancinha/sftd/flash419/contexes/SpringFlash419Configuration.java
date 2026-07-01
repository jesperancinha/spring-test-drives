package org.jesperancinha.sftd.flash419.contexes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SpringFlash419Configuration {

    @Bean
    public SongObject songObject() {
        return SongObject.builder().allSongs(Arrays.asList(
               "Oxygène (Part IV) - Jean-Michel Jarre",
                "Equinoxe (Part V) - Jean-Michel Jarre"
        )).build();
    }
}
