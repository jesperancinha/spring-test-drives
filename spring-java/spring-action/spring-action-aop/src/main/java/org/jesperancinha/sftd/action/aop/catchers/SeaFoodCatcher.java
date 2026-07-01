package org.jesperancinha.sftd.action.aop.catchers;

public interface SeaFoodCatcher<T> {

    T catchWithNet();

    T catchWithFishingPole();

    T catchByHand();

    void catchByHandExtra();

    T catchWithClaws();

    T catchWithSuperSonicWaves();

    T catchWithABucket();

    T catchWithLove();
}
