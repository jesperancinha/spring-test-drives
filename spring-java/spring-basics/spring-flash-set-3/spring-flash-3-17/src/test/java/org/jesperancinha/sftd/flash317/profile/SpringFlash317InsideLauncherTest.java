package org.jesperancinha.sftd.flash317.profile;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("inside")
class SpringFlash317InsideLauncherTest {

    @Test
    void testContext() {
    }
}