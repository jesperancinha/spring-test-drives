package org.jesperancinha.std.flash419.contexes;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.test.util.TestUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringFlash419ConfigurationCustom.class)
class SpringFlash419ConfigurationCustomTest {

    @Autowired
    private TestUtils.TestApplicationContext testApplicationContext;

    @Test
    void testSongObject_whenContent_thenWhatIsIt() {
        final Object songObject = testApplicationContext.getBean("songObject");
        ConsolerizerComposer.out(" ")
                .blue("We get bean")
                .red(songObject).toConsoleLn();

    }
}