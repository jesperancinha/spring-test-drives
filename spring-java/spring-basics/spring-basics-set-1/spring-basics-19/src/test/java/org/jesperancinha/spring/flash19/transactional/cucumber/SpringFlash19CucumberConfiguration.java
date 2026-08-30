package org.jesperancinha.spring.flash19.transactional.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;

/**
 * Posted on twitter as SprinfFlash19CucumberConfiguration, this is the configuration which enables Spring IoC beans to be injected accross the Cucumber Steps.
 */
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Execution(SAME_THREAD)
public class SpringFlash19CucumberConfiguration {
}