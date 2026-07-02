package org.jesperancinha.sftd.flash37.aop.detail.controller;

import org.jesperancinha.console.consolerizer.console.Consolerizer;
import org.jesperancinha.sftd.flash37.aop.detail.dto.TicketDto;
import org.jesperancinha.sftd.flash37.aop.detail.service.TicketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @PostMapping("/")
    public UUID createTicket(
            @RequestBody
            final TicketDto ticket) {
        final TicketDto ticket1 = ticketService.createTicket(ticket);
        Consolerizer.printRainbowTitleLn("Created ticket %s", ticket1);
        return ticket1.getUuid();
    }

    @PostMapping("/no-around")
    public UUID createTicketNoAround(
            @RequestBody
            final TicketDto ticket) {
        final TicketDto ticket1 = ticketService.createTicketNoAround(ticket);
        Consolerizer.printRainbowTitleLn("Created ticket %s", ticket1);
        return ticket1.getUuid();
    }
}
