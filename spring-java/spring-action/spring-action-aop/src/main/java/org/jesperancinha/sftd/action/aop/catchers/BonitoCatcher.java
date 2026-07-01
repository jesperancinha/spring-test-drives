package org.jesperancinha.sftd.action.aop.catchers;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.action.aop.model.Bonito;
import org.springframework.stereotype.Service;

@Service
public class BonitoCatcher implements SeaFoodCatcher<Bonito> {
    @Override
    public Bonito catchWithNet() {
        return null;
    }

    @Override
    public Bonito catchWithFishingPole() {
        return null;
    }

    @Override
    public Bonito catchByHand() {
        ConsolerizerComposer.outSpace()
                .brightGreen("You just caught a Bonito by hand!")
                .reset();
        return new Bonito();
    }

    @Override
    public void catchByHandExtra() {
        ConsolerizerComposer.outSpace()
                .brightGreen("You goofed around and didn't catch anything")
                .reset();
    }

    @Override
    public Bonito catchWithClaws() {
        ConsolerizerComposer.outSpace()
                .brightGreen("You just caught a Bonito with your fine claws")
                .reset();
        return new Bonito();
    }

    @Override
    public Bonito catchWithSuperSonicWaves() {
        return null;
    }

    @Override
    public Bonito catchWithABucket() {
        return null;
    }

    @Override
    public Bonito catchWithLove() {
        return null;
    }
}
