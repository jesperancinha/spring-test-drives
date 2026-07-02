package org.jesperancinha.sftd.flash18.aop.afterthrowing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.jesperancinha.sftd.flash18.aop.afterthrowing.SpringFlash18Launcher.mutateLyricsServiceImpl;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class SpringFlash18LauncherTest {

    @Autowired
    public ApplicationContext applicationContext;

    @Test
    void testContext() {
        mutateLyricsServiceImpl(applicationContext);
    }
}