package org.jesperancinha.sftd.flash18.aop.afterthrowing.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.jesperancinha.sftd.flash18.aop.afterthrowing.beans.JoinPointService;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LyricAspect {

    private final JoinPointService joinPointService;

    public LyricAspect(JoinPointService joinPointService) {
        this.joinPointService = joinPointService;
    }

    @AfterThrowing(value = "execution(* org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsService.*(..))",
            throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        joinPointService.afterThrowingAdvice(joinPoint, ex);
    }

    @AfterThrowing(value = "execution(* org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsService.enumerateLyric1(..))",
            throwing = "ex")
    public void afterThrowingAdvice1(JoinPoint joinPoint, Exception ex) {
        joinPointService.afterThrowingAdvice1(joinPoint, ex);
    }

    @AfterThrowing(value = "execution(* org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsService.enumerateLyric2(..))",
            throwing = "ex")
    public void afterThrowingAdvice2(JoinPoint joinPoint, Exception ex) {
        joinPointService.afterThrowingAdvice2(joinPoint, ex);
    }

    @AfterThrowing(value = "execution(* org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsService.enumerateLyric3(..))",
            throwing = "ex")
    public void afterThrowingAdvice3(JoinPoint joinPoint, Exception ex) {
        joinPointService.afterThrowingAdvice3(joinPoint, ex);
    }

    @AfterThrowing(value = "execution(* org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsService.resultLyric*(..))",
            throwing = "ex")
    public void afterThrowingAdviceResults(JoinPoint joinPoint, Exception ex) {
        joinPointService.afterThrowingAdviceResults(joinPoint, ex);
    }
}