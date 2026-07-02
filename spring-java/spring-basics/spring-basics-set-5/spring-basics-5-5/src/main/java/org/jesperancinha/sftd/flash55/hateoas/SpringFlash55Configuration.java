package org.jesperancinha.sftd.flash55.hateoas;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan
@Configuration
@EnableHypermediaSupport(type = {EnableHypermediaSupport.HypermediaType.HAL})
public class SpringFlash55Configuration {
}
