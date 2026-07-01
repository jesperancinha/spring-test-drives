package org.jesperancinha.sftd.action.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jesperancinha.sftd.action.aop.beans.OysterService;
import org.jesperancinha.sftd.action.aop.model.Oyster;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OysterAspect {

    private final OysterService oysterService;

    public OysterAspect(OysterService oysterService) {
        this.oysterService = oysterService;
    }

    @Before("args(oyster)")
    public void oysterProcessing(final JoinPoint joinPoint, final Oyster oyster) {
        oysterService.oysterProcessing(joinPoint, oyster);
    }

    @Before("@args(org.jesperancinha.sftd.action.aop.annotations.HighQuality)")
    public void oysterQualityProcessing(final JoinPoint joinPoint) {
        oysterService.oysterQualityProcessing(joinPoint);
    }
}
