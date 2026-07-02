package org.jesperancinha.sftd.flash58.conditionals.prod;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

@Profile("prod")
@Configuration
@ConditionalOnBean(JdbcTemplate.class)
public class Dance4Configuration {

    public Dance4Configuration() {
        ConsolerizerComposer.outSpace()
                .black()
                .bgGreen("We started this configuration class %s", this)
                .bgGreen("Push it high")
                .reset();
    }

}
