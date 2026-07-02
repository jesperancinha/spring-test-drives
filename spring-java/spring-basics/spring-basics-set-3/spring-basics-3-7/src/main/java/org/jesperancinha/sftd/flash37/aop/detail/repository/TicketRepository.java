package org.jesperancinha.sftd.flash37.aop.detail.repository;

import org.jesperancinha.sftd.flash37.aop.detail.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
