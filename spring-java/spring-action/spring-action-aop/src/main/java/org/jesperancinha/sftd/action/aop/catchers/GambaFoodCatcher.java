package org.jesperancinha.sftd.action.aop.catchers;

import org.jesperancinha.sftd.action.aop.annotations.Master;
import org.jesperancinha.sftd.action.aop.model.Gamba;
import org.springframework.stereotype.Service;

@Service
@Master
public class GambaFoodCatcher implements SeaFoodCatcher<Gamba> {
    @Override
    public Gamba catchWithNet() {
        return null;
    }

    @Override
    public Gamba catchWithFishingPole() {
        return null;
    }

    @Master
    public Gamba catchByHand() {
        return null;
    }

    @Master
    @Override
    public void catchByHandExtra() {

    }

    @Override
    public Gamba catchWithClaws() {
        return null;
    }

    @Override
    public Gamba catchWithSuperSonicWaves() {
        return null;
    }

    @Override
    public Gamba catchWithABucket() {
        return null;
    }

    @Override
    public Gamba catchWithLove() {
        return null;
    }
}
