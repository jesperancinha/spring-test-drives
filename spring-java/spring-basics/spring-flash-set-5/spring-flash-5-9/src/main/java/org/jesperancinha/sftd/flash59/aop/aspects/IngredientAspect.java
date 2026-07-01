package org.jesperancinha.sftd.flash59.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.stereotype.Component;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.YELLOW;

@Aspect
@Component
public class IngredientAspect {

    @Around("execution(* org.jesperancinha.sftd.flash59.aop.service.Recipe.bake())")
    public String aroundBake1(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        YELLOW.printGenericLn("Around with signature -> %s", proceedingJoinPoint.getSignature().getName());
        final String proceed = (String) proceedingJoinPoint.proceed();
        return "Ok1" + proceed;
    }

    @Around("execution(* org.jesperancinha.sftd.flash59.aop.service.Recipe.bake())")
    public String aroundBake2(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        YELLOW.printGenericLn("Around with signature -> %s", proceedingJoinPoint.getSignature().getName());
        final String proceed = (String) proceedingJoinPoint.proceed();
        return "Ok2" + proceed;
    }

    @Around("@annotation(org.jesperancinha.sftd.flash59.aop.annotation.Diet)")
    public String allRound(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        YELLOW.printGenericLn("Around with signature -> %s", proceedingJoinPoint.getSignature().getName());
        final String proceed = (String) proceedingJoinPoint.proceed();
        return "Ok2" + proceed;
    }

    @Before("execution(* org.jesperancinha.sftd.flash59.aop.service.Recipe.bake())")
    public void before(JoinPoint joinPoint) {
        ConsolerizerComposer.outSpace()
                .yellow("Before with signature %s", joinPoint.getSignature().getName())
                .black()
                .bgGreen(joinPoint)
                .green(joinPoint.getSignature())
                .green(joinPoint.getKind())
                .green(joinPoint.getSourceLocation())
                .green(joinPoint.getTarget())
                .green(joinPoint.getStaticPart())
                .green(joinPoint.getArgs())
                .green(joinPoint.getThis());
    }

    @After("execution(* org.jesperancinha.sftd.flash59.aop.service.Recipe.bake())")
    public void after(JoinPoint joinPoint) {
        ConsolerizerComposer.outSpace()
                .yellow("After with signature %s", joinPoint.getSignature().getName())
                .black()
                .bgGreen(joinPoint)
                .green(joinPoint.getSignature())
                .green(joinPoint.getKind());
    }

}
