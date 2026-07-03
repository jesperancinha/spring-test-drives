package org.jesperancinha.spring.flash52;

import jakarta.annotation.PreDestroy;
import org.jesperancinha.spring.flash52.config.GoodTomatoSpringConfiguration;
import org.jesperancinha.spring.flash52.domain.Tomato;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        GoodTomatoSpringConfigurationTest.TestTomatoConfiguration.class,
        GoodTomatoSpringConfiguration.class
})
class GoodTomatoSpringConfigurationTest {

    @MockitoSpyBean
    private TomatoExtended originalTomato;

    @Test
    void testTomatoWhenTomatoInitsAndEndsThenInitAndEndMethodsCalled() {
        verify(originalTomato, times(1)).growTomato();
    }

    public static class TomatoExtended extends Tomato {

        public TomatoExtended(String description) {
            super(description);
        }

        public void growTomato() {
        }

        public void eatTomato() {

        }

        @PreDestroy
        public void preDestroy() {
        }
    }

    @Configuration
    public static class TestTomatoConfiguration {

        @Bean
        public TomatoExtended originalTomato() {
            return new TomatoExtended("This is a test tomato");
        }
    }
}