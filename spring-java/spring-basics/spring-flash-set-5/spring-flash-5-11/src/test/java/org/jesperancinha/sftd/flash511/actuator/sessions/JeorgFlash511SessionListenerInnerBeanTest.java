package org.jesperancinha.sftd.flash511.actuator.sessions;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JeorgFlash511SessionListenerInnerBeanTest {

    @Autowired(required = false)
    private String pepper;

    @Test
    void testSessionCreatedWhenInjectedThenNoPeppers() {
        ConsolerizerComposer.outSpace()
                .unicorns(100)
                .black()
                .bgOrange("We get peppers if we have a static class within the @SpringBootTest annotated top level class")
                .green(pepper)
                .reset();
        assertThat(pepper).isEqualTo("Give it away, give it away, give it away, now");
    }

    @Test
    void testSessionDestroyedWhenInjectedThenNoPeppers() {
        ConsolerizerComposer.outSpace()
                .unicorns(100)
                .black()
                .bgOrange("We get peppers if we have a static class within the @SpringBootTest annotated top level class")
                .green(pepper)
                .reset();
        assertThat(pepper).isEqualTo("Give it away, give it away, give it away, now");
    }

    @TestConfiguration
    static class JeorgFlash511InnerConfiguration {

        @Bean
        public String pepper() {
            return "Give it away, give it away, give it away, now";
        }
    }
}