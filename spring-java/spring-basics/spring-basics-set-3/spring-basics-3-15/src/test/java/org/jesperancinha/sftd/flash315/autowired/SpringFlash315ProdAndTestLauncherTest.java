package org.jesperancinha.sftd.flash315.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"prod", "test"})
class SpringFlash315ProdAndTestLauncherTest {

    @Test
    void testContext() {
    }
}