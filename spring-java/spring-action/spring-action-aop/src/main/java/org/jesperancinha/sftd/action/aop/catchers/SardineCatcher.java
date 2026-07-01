package org.jesperancinha.sftd.action.aop.catchers;

import org.jesperancinha.sftd.action.aop.model.Sardine;
import org.springframework.stereotype.Service;

@Service
public class SardineCatcher implements SeaFoodCatcher<Sardine> {
    @Override
    public Sardine catchWithNet() {
        return null;
    }

    @Override
    public Sardine catchWithFishingPole() {
        return null;
    }

    @Override
    public Sardine catchByHand() {
        return null;
    }

    @Override
    public void catchByHandExtra() {

    }

    @Override
    public Sardine catchWithClaws() {
        return null;
    }

    @Override
    public Sardine catchWithSuperSonicWaves() {
        return null;
    }

    @Override
    public Sardine catchWithABucket() {
        return null;
    }

    @Override
    public Sardine catchWithLove() {
        return null;
    }
}
