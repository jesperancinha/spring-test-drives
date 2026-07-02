package org.jesperancinha.sftd.flash18.aop.afterthrowing.beans;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.*;
import static org.jesperancinha.console.consolerizer.console.Consolerizer.printRainbowTitleLn;

/**
 * Join Point service
 * Collection of methods that serve the {@link Aspect} class
 */
@Service
public class JoinPointService {
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        BRIGHT_MAGENTA.printGenericLn("This is the whole joinPoint -> %s", joinPoint);
        BRIGHT_MAGENTA.printGenericLn("This is the jointPoint signature -> %s", joinPoint.getSignature());
        BRIGHT_MAGENTA.printGenericLn("The is the exception being thrown -> %s", ex.getMessage());
    }

    public void afterThrowingAdvice1(JoinPoint joinPoint, Exception ex) {
        GREEN.printGenericLn("This is the whole joinPoint -> %s", joinPoint);
        GREEN.printGenericLn("This is the jointPoint signature -> %s", joinPoint.getSignature());
        GREEN.printGenericLn("The is the exception being thrown -> %s", ex.getMessage());
    }

    public void afterThrowingAdvice2(JoinPoint joinPoint, Exception ex) {
        YELLOW.printGenericLn("This is the whole joinPoint -> %s", joinPoint);
        YELLOW.printGenericLn("This is the jointPoint signature -> %s", joinPoint.getSignature());
        YELLOW.printGenericLn("The is the exception being thrown -> %s", ex.getMessage());
    }

    public void afterThrowingAdvice3(JoinPoint joinPoint, Exception ex) {
        RED.printGenericLn("This is the whole joinPoint -> %s", joinPoint);
        RED.printGenericLn("This is the jointPoint signature -> %s", joinPoint.getSignature());
        RED.printGenericLn("The is the exception being thrown -> %s", ex.getMessage());
    }

    public void afterThrowingAdviceResults(JoinPoint joinPoint, Exception ex) {
        printRainbowTitleLn("This is the whole joinPoint -> %s", joinPoint);
        printRainbowTitleLn("This is the jointPoint signature -> %s", joinPoint.getSignature());
        printRainbowTitleLn("The is the exception being thrown -> %s", ex.getMessage());
    }
}
