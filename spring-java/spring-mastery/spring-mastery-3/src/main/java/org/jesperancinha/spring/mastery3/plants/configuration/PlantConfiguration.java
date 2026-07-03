package org.jesperancinha.spring.mastery3.plants.configuration;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.spring.mastery3.plants.dto.PlantDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PlantConfiguration {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        ConsolerizerComposer.outSpace()
                .cyan("Configuring the PropertySourcesPlaceholderConfigurer")
                .cyan("This is also a PlaceholderConfigurerSupport")
                .reset();
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setLocations(new ClassPathResource("master3-plant.properties"));
        propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        propertySourcesPlaceholderConfigurer.setIgnoreResourceNotFound(true);
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean(name = {"yucca","yohoo","yuppi", "sansevieria"})
    public PlantDto plantDto(){
        return PlantDto
                .builder()
                .name("Yucca")
                .scientificName("Yucca filamentosa")
                .build();
    }

}
