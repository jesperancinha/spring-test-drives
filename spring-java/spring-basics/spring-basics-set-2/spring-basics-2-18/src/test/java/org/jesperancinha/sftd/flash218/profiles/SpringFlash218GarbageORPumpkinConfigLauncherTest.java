package org.jesperancinha.sftd.flash218.profiles;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"garbage", "pumpkin"})
class SpringFlash218GarbageORPumpkinConfigLauncherTest {

    @Test
    void testContext() {
    }
}