package org.jesperancinha.sftd.config;

import org.jesperancinha.sftd.entities.SpaceRocket;
import org.jesperancinha.sftd.entities.SpaceRocketRepository;
import org.jesperancinha.sftd.service.SpaceRocketService;
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
