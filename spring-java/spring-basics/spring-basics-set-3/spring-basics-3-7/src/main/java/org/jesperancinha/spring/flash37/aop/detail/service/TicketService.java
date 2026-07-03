package org.jesperancinha.spring.flash37.aop.detail.service;

import org.jesperancinha.spring.flash37.aop.detail.dto.TicketDto;
import org.jesperancinha.spring.flash37.aop.detail.model.Ticket;
import org.jesperancinha.spring.flash37.aop.detail.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TicketDto createTicket(TicketDto ticketDto) {
        return toDto(ticketRepository.save(toData(ticketDto)));
    }

    public TicketDto createTicketNoAround(TicketDto ticketDto) {
        return toDto(ticketRepository.save(toData(ticketDto)));
    }

    private TicketDto toDto(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.getId())
                .localDateTime(ticket.getLocalDateTime())
                .artist(ticket.getArtist())
                .uuid(ticket.getUuid())
                .show(ticket.getShow())
                .build();
    }

    private Ticket toData(TicketDto ticketDto) {
        return Ticket.builder()
                .id(ticketDto.id())
                .localDateTime(ticketDto.localDateTime())
                .artist(ticketDto.artist())
                .uuid(ticketDto.uuid())
                .show(ticketDto.show())
                .build();
    }

}
