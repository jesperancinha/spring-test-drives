package org.jesperancinha.sftd.flash37.aop.detail.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jesperancinha.sftd.flash37.aop.detail.beans.TicketBeforeBean;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TicketAspectBefore {

    private final TicketBeforeBean ticketBeforeBean;

    public TicketAspectBefore(TicketBeforeBean ticketBeforeBean) {
        this.ticketBeforeBean = ticketBeforeBean;
    }

    @Before("execution(* org.jesperancinha.sftd.flash37.aop.detail.service.TicketService.createTicket(..))")
    public void logBeforeTicket(JoinPoint joinPoint) {
        ticketBeforeBean.logBeforeTicket(joinPoint);
    }

    @Before("execution(* org.jesperancinha.sftd.flash37.aop.detail.service.TicketService.createTicketNoAround(..))")
    public void logBeforeTicketNoAround(JoinPoint joinPoint) {
        ticketBeforeBean.logBeforeTicketNoAround(joinPoint);
    }
}
