package org.jesperancinha.sftd.flash19.transactional.cucumber;


import io.cucumber.junit.CucumberOptions;
import org.jesperancinha.sftd.flash19.transactional.configuration.HikariConfiguration;
import org.jesperancinha.sftd.flash19.transactional.containers.AbstractTestContainersIT;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.Suite;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@Suite
@IncludeEngines("cucumber")
@CucumberOptions(features = {"classpath:/features/albums.save.feature"})
@Import(HikariConfiguration.class)
@ContextConfiguration(initializers = AbstractTestContainersIT.DockerPostgresDataInitializer.class)
public class SpringFlash19CucumberLauncher {


}
