package org.jesperancinha.sftd.flash19.transactional.configuration.cucumber

import io.cucumber.junit.CucumberOptions
import org.jesperancinha.sftd.flash19.transactional.configuration.HikariConfiguration
import org.jesperancinha.sftd.flash19.transactional.configuration.containers.AbstractTestContainersKotlinIT
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.Suite
import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration

@Suite
@IncludeEngines("cucumber")
@CucumberOptions(features = ["classpath:/features/albums.save.feature"])
@Import(
    HikariConfiguration::class
)
@ContextConfiguration(initializers = [AbstractTestContainersKotlinIT.DockerPostgresDataInitializer::class])
class SpringFlash19CucumberLauncher 