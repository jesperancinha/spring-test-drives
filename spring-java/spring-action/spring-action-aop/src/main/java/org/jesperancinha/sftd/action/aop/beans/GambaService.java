package org.jesperancinha.sftd.action.aop.beans;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

@Service
public class GambaService {

    public void beforeWithin(final JoinPoint joinPoint) {

    }

    public void beforeExecution(final JoinPoint joinPoint) {

    }

    public void beforeAnnotation(final JoinPoint joinPoint) {

    }

    public void beforeWithinAnnotation(JoinPoint joinPoint) {

    }

    public void beforeWithinNoAtAnnotation(JoinPoint joinPoint) {

    }
}
