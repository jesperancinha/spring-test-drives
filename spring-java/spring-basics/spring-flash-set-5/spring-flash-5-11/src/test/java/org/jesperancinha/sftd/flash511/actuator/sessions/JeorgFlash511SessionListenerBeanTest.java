package org.jesperancinha.sftd.flash511.actuator.sessions;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash511.actuator.sessions.configuration.JeorgFlash511Configuration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@Import(JeorgFlash511Configuration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JeorgFlash511SessionListenerBeanTest {

    @Autowired(required = false)
    private String pepper;

    @Test
    void testSessionCreatedWhenInjectedThenNoPeppers() {
        ConsolerizerComposer.outSpace()
                .unicorns(100)
                .black()
                .bgGreen("We get peppers if we import the @TestConfiguration annotated class")
                .green(pepper)
                .reset();
        assertThat(pepper).isEqualTo("Give it away, give it away, give it away, now");
    }

    @Test
    void testSessionDestroyedWhenInjectedThenNoPeppers() {
        ConsolerizerComposer.outSpace()
                .unicorns(100)
                .black()
                .bgGreen("We get peppers if we import the @TestConfiguration annotated class")
                .green(pepper)
                .reset();
        assertThat(pepper).isEqualTo("Give it away, give it away, give it away, now");
    }
}