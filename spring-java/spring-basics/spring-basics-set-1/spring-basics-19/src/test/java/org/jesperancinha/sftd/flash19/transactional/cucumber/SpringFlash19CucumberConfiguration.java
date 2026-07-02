package org.jesperancinha.sftd.flash19.transactional.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Posted on twitter as SprinfFlash19CucumberConfiguration, this is the configuration which enables Spring IoC beans to be injected accross the Cucumber Steps.
 */
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringFlash19CucumberConfiguration {
}