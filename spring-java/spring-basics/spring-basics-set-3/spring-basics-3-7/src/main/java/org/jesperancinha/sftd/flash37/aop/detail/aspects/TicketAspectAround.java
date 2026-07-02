package org.jesperancinha.sftd.flash37.aop.detail.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jesperancinha.sftd.flash37.aop.detail.beans.TicketAroundBean;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TicketAspectAround {

    private final TicketAroundBean ticketAroundBean;

    public TicketAspectAround(TicketAroundBean ticketAroundBean) {
        this.ticketAroundBean = ticketAroundBean;
    }

    @Around("execution(* org.jesperancinha.sftd.flash37.aop.detail.service.TicketService.createTicket(..))")
    public Object aroundTicket(ProceedingJoinPoint proceedingJoinPoint) {
        return ticketAroundBean.aroundTicket(proceedingJoinPoint);
    }
}
