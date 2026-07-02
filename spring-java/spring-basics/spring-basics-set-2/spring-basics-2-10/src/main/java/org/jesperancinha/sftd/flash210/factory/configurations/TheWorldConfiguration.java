package org.jesperancinha.sftd.flash210.factory.configurations;

import org.jesperancinha.console.consolerizer.common.ConsolerizerColor;
import org.springframework.context.annotation.Bean;

public class TheWorldConfiguration {

    @Bean
    public Material material2() {
        ConsolerizerColor.BLUE.printGenericLn("You will not see this login, because it is not configured in the spring.factories file");
        final Material material = new Material();
        material.setValue("If you are strong enough!");
        return material;
    }
}
