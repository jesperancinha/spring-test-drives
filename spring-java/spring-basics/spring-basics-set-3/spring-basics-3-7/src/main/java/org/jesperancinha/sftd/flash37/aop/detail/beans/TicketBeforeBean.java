package org.jesperancinha.sftd.flash37.aop.detail.beans;

import org.aspectj.lang.JoinPoint;
import org.jesperancinha.sftd.flash37.aop.detail.model.Ticket;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.YELLOW;

@Component
public class TicketBeforeBean {
    public void logBeforeTicket(JoinPoint joinPoint) {
        YELLOW.printGenericLn("Before -> TicketService.createTicket() -> %s", joinPoint.getSignature().getName());
    }

    public void logBeforeTicketNoAround(JoinPoint joinPoint) {
        YELLOW.printGenericLn("After -> TicketService.createTicket() -> %s", joinPoint.getSignature().getName());
        final Ticket ticket = (Ticket) joinPoint.getArgs()[0];
        ticket.setUuid(UUID.randomUUID());
    }
}
