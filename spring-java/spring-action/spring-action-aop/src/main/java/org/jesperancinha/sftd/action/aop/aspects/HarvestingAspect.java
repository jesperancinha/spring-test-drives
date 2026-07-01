package org.jesperancinha.sftd.action.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jesperancinha.sftd.action.aop.beans.HarvestingService;
import org.jesperancinha.sftd.action.aop.fishing.Shrimper;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HarvestingAspect {

    private final HarvestingService harvestingService;

    public HarvestingAspect(HarvestingService harvestingService) {
        this.harvestingService = harvestingService;
    }

    @Before("this(org.jesperancinha.sftd.action.aop.fishing.Harvester)")
    public void thisHarvester(final JoinPoint joinPoint) {
        harvestingService.thisHarvester(joinPoint);
    }

    @Before("target(org.jesperancinha.sftd.action.aop.fishing.Shrimper)")
    public void targetShrimper(final JoinPoint joinPoint) {
        harvestingService.targetShrimper(joinPoint);
    }

    @Before("target(org.jesperancinha.sftd.action.aop.fishing.Harvester)")
    public void targetHarvester(final JoinPoint joinPoint) {
        harvestingService.targetHarvester(joinPoint);
    }

    @Before("target(org.jesperancinha.sftd.action.aop.fishing.Fisher)")
    public void targetFisher(final JoinPoint joinPoint) {
        harvestingService.targetFisher(joinPoint);
    }

    @Before("this(org.jesperancinha.sftd.action.aop.fishing.Fisher)")
    public void thisFisher(final JoinPoint joinPoint) {
        harvestingService.thisFisher(joinPoint);
    }

    @Before("this(org.jesperancinha.sftd.action.aop.fishing.Shrimper)")
    public void thisShrimper(final JoinPoint joinPoint) {
        harvestingService.thisShrimper(joinPoint);
    }

    @Before("execution(* org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()) && target(shrimper)")
    public void passingTargetArgument(final JoinPoint joinPoint, final Shrimper shrimper) {
        harvestingService.passingTargetArgument(joinPoint, shrimper);
    }
}
