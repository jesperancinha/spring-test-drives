package org.jesperancinha.sftd.flash59.aop.service;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RecipeImpl implements Recipe {
    private final List<String> ingredients;

    public RecipeImpl() {
        ingredients = Arrays.asList("6 Eggs", "100g Flower", "1L Milk");
    }

    @Override
    public String bake() {
        ConsolerizerComposer.outSpace()
                .none()
                .magenta("I just baked a nice cake with only")
                .brightMagenta("these ingredients")
                .newLine()
                .yellow(ingredients)
                .reset();

        return "Baked Cake";
    }

    @Override
    public String bakeDiet() {
        ConsolerizerComposer.outSpace()
                .none()
                .magenta("I just baked a nice diet cake with only")
                .brightMagenta("these ingredients")
                .newLine()
                .yellow(ingredients)
                .reset();

        return "Diet Baked Cake";
    }
}
