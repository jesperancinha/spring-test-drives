package org.jesperancinha.sftd.action.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.jesperancinha.sftd.action.aop.beans.Bonito4Service;

public class BonitoAspect4 {

    private final Bonito4Service bonito4Service;

    public BonitoAspect4(Bonito4Service bonito4Service) {
        this.bonito4Service = bonito4Service;
    }

    public void waitPrivatelyForFishCatch(final JoinPoint joinPoint) {
        bonito4Service.waitPrivatelyForFishCatch(joinPoint);
    }

    public void beforeAnyCatch(final JoinPoint joinPoint) {
        bonito4Service.beforeAnyCatch(joinPoint);
    }

}
