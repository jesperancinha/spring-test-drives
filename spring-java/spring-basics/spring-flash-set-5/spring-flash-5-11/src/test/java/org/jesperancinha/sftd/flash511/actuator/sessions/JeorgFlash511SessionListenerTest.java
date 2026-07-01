package org.jesperancinha.sftd.flash511.actuator.sessions;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JeorgFlash511SessionListenerTest {

    @Autowired(required = false)
    private String pepper;

    @Test
    void testSessionCreatedWhenInjectedThenNoPeppers() {
        ConsolerizerComposer.outSpace()
                .unicorns(100)
                .black()
                .bgBlue("We don't get peppers if the configuration class isn't annotated with @TestConfiguration or if this class is either not @Import-ed nor is it annotated as an internal static non-private class")
                .green(pepper)
                .reset();
        assertThat(pepper).isNull();
    }

    @Test
    void testSessionDestroyedWhenInjectedThenNoPeppers() {
        ConsolerizerComposer.outSpace()
                .unicorns(100)
                .black()
                .bgBlue("We don't get peppers if the configuration class isn't annotated with @TestConfiguration or if this class is either not @Import-ed nor is it annotated as an internal static non-private class")
                .green(pepper)
                .reset();
        assertThat(pepper).isNull();
    }
}