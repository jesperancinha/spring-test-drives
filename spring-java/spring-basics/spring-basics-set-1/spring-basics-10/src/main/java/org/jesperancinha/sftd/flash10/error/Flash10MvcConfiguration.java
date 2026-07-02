package org.jesperancinha.sftd.flash10.error;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.BRIGHT_GREEN;

@Configuration
@EnableWebMvc
@Profile("prod")
public class Flash10MvcConfiguration {
    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver
    createSimpleMappingExceptionResolver() {
        final SimpleMappingExceptionResolver flash10MappingExceptionResolver =
                new Flash10MappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("RuntimeException", "runtimeError");
        flash10MappingExceptionResolver.setExceptionMappings(mappings);
        flash10MappingExceptionResolver.setDefaultErrorView("error");
        flash10MappingExceptionResolver.setExceptionAttribute("ex");
        flash10MappingExceptionResolver.setWarnLogCategory("example.MvcLogger");
        BRIGHT_GREEN.printGenericLn("Initializing simpleMappingExceptionResolver -> %s", flash10MappingExceptionResolver);
        return flash10MappingExceptionResolver;
    }
}