package org.jesperancinha.sftd.flash515.factories;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(args = " ")
class SpringFlash515LauncherTest {

    @BeforeAll
    public static void setup() {
        SpringFlash515Launcher.application = new SpringApplication(SpringFlash515Launcher.class);
    }

    @Test
    void testContext() {
    }
}