package org.jesperancinha.sftd.flash37.aop.detail.beans;

import org.aspectj.lang.ProceedingJoinPoint;
import org.jesperancinha.sftd.flash37.aop.detail.model.Ticket;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.YELLOW;

@Component
public class TicketAroundBean {
    public Object aroundTicket(ProceedingJoinPoint proceedingJoinPoint) {
        YELLOW.printGenericLn("Around -> TicketService.createTicket() -> %s", proceedingJoinPoint.getSignature().getName());
        final Ticket ticket = (Ticket) proceedingJoinPoint.getArgs()[0];
        ticket.setUuid(UUID.randomUUID());
        return ticket;
    }
}
