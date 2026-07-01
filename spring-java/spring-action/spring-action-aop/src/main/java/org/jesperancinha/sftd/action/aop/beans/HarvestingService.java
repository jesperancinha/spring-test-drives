package org.jesperancinha.sftd.action.aop.beans;

import org.aspectj.lang.JoinPoint;
import org.jesperancinha.sftd.action.aop.fishing.Shrimper;
import org.springframework.stereotype.Service;

@Service
public class HarvestingService {
    public void thisHarvester(JoinPoint joinPoint) {

    }

    public void targetShrimper(JoinPoint joinPoint) {

    }

    public void targetHarvester(JoinPoint joinPoint) {

    }

    public void targetFisher(JoinPoint joinPoint) {

    }

    public void thisFisher(JoinPoint joinPoint) {

    }

    public void thisShrimper(JoinPoint joinPoint) {

    }

    public void passingTargetArgument(JoinPoint joinPoint, Shrimper shrimper) {

    }
}
