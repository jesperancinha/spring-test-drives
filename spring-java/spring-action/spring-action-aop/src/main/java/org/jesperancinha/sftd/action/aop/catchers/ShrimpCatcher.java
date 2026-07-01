package org.jesperancinha.sftd.action.aop.catchers;

import org.jesperancinha.sftd.action.aop.model.Shrimp;
import org.springframework.stereotype.Service;

@Service
public class ShrimpCatcher implements SeaFoodCatcher<Shrimp> {
    @Override
    public Shrimp catchWithNet() {
        return null;
    }

    @Override
    public Shrimp catchWithFishingPole() {
        return null;
    }

    @Override
    public Shrimp catchByHand() {
        return null;
    }

    @Override
    public void catchByHandExtra() {

    }

    @Override
    public Shrimp catchWithClaws() {
        return null;
    }

    @Override
    public Shrimp catchWithSuperSonicWaves() {
        return null;
    }

    @Override
    public Shrimp catchWithABucket() {
        return null;
    }

    @Override
    public Shrimp catchWithLove() {
        return null;
    }
}
