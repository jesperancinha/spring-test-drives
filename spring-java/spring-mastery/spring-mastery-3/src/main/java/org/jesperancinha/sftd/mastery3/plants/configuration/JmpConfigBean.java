package org.jesperancinha.sftd.mastery3.plants.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConditionalOnClass(PlantConfiguration.class)
public class JmpConfigBean {

    @Value("${jmp.plant}")
    private String plant;

    @Value("${jmp.height}")
    private Long height;

    @Value("${jmp.lifespan}")
    private Long lifespan;

}

