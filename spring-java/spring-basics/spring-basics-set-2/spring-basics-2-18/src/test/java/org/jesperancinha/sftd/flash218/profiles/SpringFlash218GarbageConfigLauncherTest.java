package org.jesperancinha.sftd.flash218.profiles;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("garbage")
class SpringFlash218GarbageConfigLauncherTest {

    @Test
    void testContext() {
    }
}