package org.jesperancinha.sftd.flash52.domain;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;

public class Tomato {
    private String description;

    public Tomato(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Tomato{" +
                "description='" + description + '\'' +
                '}';
    }

    private void growTomato() {
        ConsolerizerComposer.out(" ")
                .red("Growing tomato 🍅")
                .toConsoleLn();
    }

    private void eatTomato() {
        ConsolerizerComposer.out(" ")
                .red("Eating tomato 🍅")
                .toConsoleLn();
    }
}
