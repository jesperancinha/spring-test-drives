package org.jesperancinha.sftd.flash59.aop;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash59.aop.service.Recipe;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringFlash59Launcher implements CommandLineRunner {

    private final Recipe recipe;

    public SpringFlash59Launcher(Recipe recipe) {
        this.recipe = recipe;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash59Launcher.class, args);
    }

    @Override
    public void run(String... args) {
        ConsolerizerComposer.outSpace()
                .unicorns(50)
                .yellow("Here is the result of bake:")
                .black()
                .bgYellow(recipe.bake())
                .unicorns(50)
                .reset();
        ConsolerizerComposer.outSpace()
                .unicorns(50)
                .yellow("Here is the result of bakeDiet:")
                .black()
                .bgYellow(recipe.bakeDiet())
                .unicorns(50)
                .reset();
    }
}
