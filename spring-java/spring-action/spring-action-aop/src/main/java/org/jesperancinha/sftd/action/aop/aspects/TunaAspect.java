package org.jesperancinha.sftd.action.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jesperancinha.sftd.action.aop.beans.TunaService;
import org.jesperancinha.sftd.action.aop.catchers.TunaCatcher;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TunaAspect {

    private final TunaService tunaService;

    public TunaAspect(TunaService tunaService) {
        this.tunaService = tunaService;
    }

    /**
     * In this pointcut, we are defining an advice that will be invoked just before calling the catchWithNet method from {@link TunaCatcher} and any of its subclasses.
     * This is done with the + wildcard.
     *
     * @param joinPoint {@link JoinPoint}
     */
    @Before("execution(public * org.jesperancinha.sftd.action.aop.catchers.TunaCatcher+.catchWithNet())")
    public void beforeCatching(final JoinPoint joinPoint) {
        tunaService.beforeCatching(joinPoint);
    }
}
