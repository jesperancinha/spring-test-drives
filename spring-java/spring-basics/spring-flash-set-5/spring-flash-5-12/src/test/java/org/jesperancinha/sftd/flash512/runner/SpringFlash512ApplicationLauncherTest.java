package org.jesperancinha.sftd.flash512.runner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(args = {"a", "b", "c"})
@ActiveProfiles("application")
@ContextConfiguration(classes = SpringFlash512ApplicationLauncher.class)
class SpringFlash512ApplicationLauncherTest {

    @Test
    void main() {
    }
}