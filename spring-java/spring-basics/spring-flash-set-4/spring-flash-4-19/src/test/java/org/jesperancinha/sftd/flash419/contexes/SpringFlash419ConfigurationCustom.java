package org.jesperancinha.sftd.flash419.contexes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.integration.test.util.TestUtils;
import org.springframework.integration.test.util.TestUtils.TestApplicationContext;

@Configuration
public class SpringFlash419ConfigurationCustom {

    @Bean
    @Primary
    public TestApplicationContext testApplicationContext() {
        final TestApplicationContext testApplicationContext = TestUtils.createTestApplicationContext();
        testApplicationContext.refresh();
        testApplicationContext.registerBean("songObject", SongObject.builder().build());
        return testApplicationContext;
    }
}
