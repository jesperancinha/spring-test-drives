package org.jesperancinha.spring.action.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jesperancinha.spring.action.aop.beans.CodService;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CodAspect {

    private final CodService codService;

    public CodAspect(CodService codService) {
        this.codService = codService;
    }

    @Before("within(org.jesperancinha.spring.action.aop.catchers..*)")
    public void withinTheCode() {
        codService.withinTheCode();
    }
}
