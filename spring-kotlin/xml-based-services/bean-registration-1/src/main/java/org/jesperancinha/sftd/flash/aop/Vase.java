package org.jesperancinha.sftd.flash.aop;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;

public class Vase {

    public Plant seed(final Seed seed) {
        ConsolerizerComposer.outSpace()
                .brightRed("Seeds %s are in the vase!", seed.getName())
                .reset();
        return new Plant(seed.getName() + " plant");
    }
}
