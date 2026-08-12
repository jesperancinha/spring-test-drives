package org.jesperancinha.spring.flash19.transactional.cucumber;


import io.cucumber.junit.CucumberOptions;
import org.jesperancinha.spring.flash19.transactional.configuration.HikariConfiguration;
import org.jesperancinha.spring.flash19.transactional.containers.AbstractTestContainersIT;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.Suite;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;

@Suite
@IncludeEngines("cucumber")
@CucumberOptions(features = {"classpath:/features/albums.save.feature"})
@Import(HikariConfiguration.class)
@ContextConfiguration(initializers = AbstractTestContainersIT.DockerPostgresDataInitializer.class)
@Execution(SAME_THREAD)
public class SpringFlash19CucumberLauncher {


}
