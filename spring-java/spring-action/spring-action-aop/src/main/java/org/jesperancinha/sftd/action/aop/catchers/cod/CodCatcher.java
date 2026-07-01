package org.jesperancinha.sftd.action.aop.catchers.cod;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.action.aop.catchers.SeaFoodCatcher;
import org.jesperancinha.sftd.action.aop.model.Cod;
import org.springframework.stereotype.Service;

@Service
public class CodCatcher implements SeaFoodCatcher<Cod> {
    @Override
    public Cod catchWithNet() {
        return null;
    }

    @Override
    public Cod catchWithFishingPole() {
        return null;
    }

    @Override
    public Cod catchByHand() {
        ConsolerizerComposer.outSpace()
                .cyan()
                .bgBlue("Catched a Cod Fish by hand")
                .reset();
        return new Cod();
    }

    @Override
    public void catchByHandExtra() {

    }

    @Override
    public Cod catchWithClaws() {
        return null;
    }

    @Override
    public Cod catchWithSuperSonicWaves() {
        return null;
    }

    @Override
    public Cod catchWithABucket() {
        return null;
    }

    @Override
    public Cod catchWithLove() {
        return null;
    }
}
