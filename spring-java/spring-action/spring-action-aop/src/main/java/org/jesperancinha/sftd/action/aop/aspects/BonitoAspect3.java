package org.jesperancinha.sftd.action.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jesperancinha.sftd.action.aop.beans.Bonito3Service;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BonitoAspect3 {

    private final Bonito3Service bonito3Service;

    public BonitoAspect3(Bonito3Service bonito3Service) {
        this.bonito3Service = bonito3Service;
    }

    @Pointcut("execution(public org.jesperancinha.sftd.action.aop.model.Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithClaws())")
    public void waitPrivatelyForFishCatchWithClaw() {
        bonito3Service.waitPrivatelyForFishCatchWithClaw();
    }

    @Pointcut("execution(public org.jesperancinha.sftd.action.aop.model.Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchByHand())")
    public void waitPrivatelyForFishCatchWithHand() {
        bonito3Service.waitPrivatelyForFishCatchWithHand();
    }

    @Before("waitPrivatelyForFishCatchWithClaw() || waitPrivatelyForFishCatchWithHand()")
    public void waitForFishCatch() {
        bonito3Service.waitForFishCatch();
    }

    @Before("!waitPrivatelyForFishCatchWithClaw() && waitPrivatelyForFishCatchWithHand()")
    public void waitForFishNoClawCatch() {
        bonito3Service.waitForFishNoClawCatch();
    }

}
