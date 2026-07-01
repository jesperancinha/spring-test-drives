package org.jesperancinha.sftd.flash211.logs;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@SpringBootTest
class SpringFlash211LauncherTest {

    @Test
    void testContext() {
        ConsolerizerComposer.outSpace()
                .magenta(title("Log4J2 example"))
                .magenta("In this example we check how Spring Framework can use Log4J2 when it finds a log4j2 file")
                .reset();
        SpringFlash211Launcher.main(new String[0]);
    }
}