package org.jesperancinha.sftd.action.aop.catchers;

import org.jesperancinha.sftd.action.aop.model.Mackerel;
import org.springframework.stereotype.Service;

@Service
public class MackerelCatcher implements SeaFoodCatcher<Mackerel> {
    @Override
    public Mackerel catchWithNet() {
        return null;
    }

    @Override
    public Mackerel catchWithFishingPole() {
        return null;
    }

    @Override
    public Mackerel catchByHand() {
        return null;
    }

    @Override
    public void catchByHandExtra() {

    }

    @Override
    public Mackerel catchWithClaws() {
        return null;
    }

    @Override
    public Mackerel catchWithSuperSonicWaves() {
        return null;
    }

    @Override
    public Mackerel catchWithABucket() {
        return null;
    }

    @Override
    public Mackerel catchWithLove() {
        return null;
    }
}
