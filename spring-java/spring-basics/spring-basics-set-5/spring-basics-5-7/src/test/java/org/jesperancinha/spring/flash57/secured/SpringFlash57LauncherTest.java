package org.jesperancinha.spring.flash57.secured;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;

@SpringBootTest
@Execution(SAME_THREAD)
class SpringFlash57LauncherTest {

    @Test
    void testContext() {
    }
}