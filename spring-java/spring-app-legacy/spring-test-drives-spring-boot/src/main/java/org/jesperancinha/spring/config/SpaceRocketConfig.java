package org.jesperancinha.spring.config;

import org.jesperancinha.spring.entities.SpaceRocket;
import org.jesperancinha.spring.entities.SpaceRocketRepository;
import org.jesperancinha.spring.service.SpaceRocketService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackageClasses = SpaceRocketService.class)
@EntityScan(basePackageClasses = SpaceRocket.class)
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = SpaceRocketRepository.class)
public class SpaceRocketConfig {
}
