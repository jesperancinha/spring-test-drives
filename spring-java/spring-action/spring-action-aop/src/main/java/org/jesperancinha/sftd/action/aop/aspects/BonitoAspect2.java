package org.jesperancinha.sftd.action.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jesperancinha.sftd.action.aop.beans.Bonito2Service;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BonitoAspect2 {

    private final Bonito2Service bonito2Service;

    public BonitoAspect2(Bonito2Service bonito2Service) {
        this.bonito2Service = bonito2Service;
    }

    @Pointcut("execution(public org.jesperancinha.sftd.action.aop.model.Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithClaws())")
    public void waitPrivatelyForFishCatch() {
        bonito2Service.waitPrivatelyForFishCatch();
    }

    @Before("waitPrivatelyForFishCatch()")
    public void waitForFishCatch(final JoinPoint joinPoint) {
        bonito2Service.waitForFishCatch(joinPoint);
    }

    @Before("execution(public * org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catch*()) && !@within(org.jesperancinha.sftd.action.aop.annotations.Master)")
    public void beforeAnyCatch(final JoinPoint joinPoint) {
        bonito2Service.beforeAnyCatch(joinPoint);
    }

}
