package org.jesperancinha.sftd.flash210.factory.configurations;

import org.jesperancinha.console.consolerizer.common.ConsolerizerColor;
import org.springframework.context.annotation.Bean;

public class TheUniverseConfiguration {

    @Bean
    public Material material1() {
        ConsolerizerColor.BLUE.printGenericLn("You will see this login, because it is configured in the spring.factories file");
        final Material material = new Material();
        material.setValue("The world is not enough!");
        return material;
    }
}
