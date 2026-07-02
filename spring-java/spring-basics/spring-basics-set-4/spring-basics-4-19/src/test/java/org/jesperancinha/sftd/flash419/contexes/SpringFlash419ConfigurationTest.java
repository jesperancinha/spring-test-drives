package org.jesperancinha.sftd.flash419.contexes;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringFlash419Configuration.class)
class SpringFlash419ConfigurationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testSongObjectWhenContentThenWhatIsIt() {
        final Object songObjext = applicationContext.getBean("songObject");
        ConsolerizerComposer.outSpace()
                .blue("We get bean")
                .red(songObjext)
                .reset();
    }
}