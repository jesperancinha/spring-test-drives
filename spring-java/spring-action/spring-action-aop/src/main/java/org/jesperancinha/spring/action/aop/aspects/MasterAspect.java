package org.jesperancinha.spring.action.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jesperancinha.spring.action.aop.annotations.Master;
import org.jesperancinha.spring.action.aop.beans.MasterService;
import org.springframework.stereotype.Component;

@Master
@Aspect
@Component
public class MasterAspect {

    private final MasterService masterService;

    public MasterAspect(MasterService masterService) {
        this.masterService = masterService;
    }

    @Before("within(@org.jesperancinha.spring.action.aop.annotations.Master org.jesperancinha.spring.action.aop.catchers.*)")
    public void masterize(final JoinPoint joinPoint) {
        masterService.masterize(joinPoint);
    }
}
