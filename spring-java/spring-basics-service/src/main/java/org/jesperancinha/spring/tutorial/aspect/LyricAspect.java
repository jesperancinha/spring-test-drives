package org.jesperancinha.spring.tutorial.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.jesperancinha.spring.tutorial.beans.JoinPointService;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LyricAspect {

    private final JoinPointService joinPointService;

    public LyricAspect(JoinPointService joinPointService) {
        this.joinPointService = joinPointService;
    }

    @AfterThrowing(value = "execution(* org.jesperancinha.spring.tutorial.service.LyricsService.*(..))",
            throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        joinPointService.afterThrowingAdvice(joinPoint, ex);
    }

    @AfterThrowing(value = "execution(* org.jesperancinha.spring.tutorial.service.LyricsService.enumerateLyric1(..))",
            throwing = "ex")
    public void afterThrowingAdvice1(JoinPoint joinPoint, Exception ex) {
        joinPointService.afterThrowingAdvice1(joinPoint, ex);
    }

    @AfterThrowing(value = "execution(* org.jesperancinha.spring.tutorial.service.LyricsService.enumerateLyric2(..))",
            throwing = "ex")
    public void afterThrowingAdvice2(JoinPoint joinPoint, Exception ex) {
        joinPointService.afterThrowingAdvice2(joinPoint, ex);
    }

    @AfterThrowing(value = "execution(* org.jesperancinha.spring.tutorial.service.LyricsService.enumerateLyric3(..))",
            throwing = "ex")
    public void afterThrowingAdvice3(JoinPoint joinPoint, Exception ex) {
        joinPointService.afterThrowingAdvice3(joinPoint, ex);
    }

    @AfterThrowing(value = "execution(* org.jesperancinha.spring.tutorial.service.LyricsService.resultLyric*(..))",
            throwing = "ex")
    public void afterThrowingAdviceResults(JoinPoint joinPoint, Exception ex) {
        joinPointService.afterThrowingAdviceResults(joinPoint, ex);
    }
}