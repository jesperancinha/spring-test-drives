package org.jesperancinha.sftd.flash37.aop.detail.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.jesperancinha.sftd.flash37.aop.detail.beans.TicketAfterBean;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TicketAspectAfter {

    private final TicketAfterBean ticketAfterBean;

    public TicketAspectAfter(TicketAfterBean ticketAfterBean) {
        this.ticketAfterBean = ticketAfterBean;
    }

    @After("execution(* org.jesperancinha.sftd.flash37.aop.detail.service.TicketService.createTicket(..))")
    public void logAfterTicket(JoinPoint joinPoint) {
        ticketAfterBean.logAfterTicket(joinPoint);
    }

    @After("execution(* org.jesperancinha.sftd.flash37.aop.detail.service.TicketService.createTicketNoAround(..))")
    public void logAfterTicketNoAround(JoinPoint joinPoint) {
        ticketAfterBean.logAfterTicketNoAround(joinPoint);
    }
}
