package org.jesperancinha.spring.action.aop.beans;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

@Service
public class TunaService {
    public void beforeCatching(JoinPoint joinPoint) {

    }
}
