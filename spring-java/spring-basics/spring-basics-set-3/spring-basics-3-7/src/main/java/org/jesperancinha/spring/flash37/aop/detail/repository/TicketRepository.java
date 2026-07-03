package org.jesperancinha.spring.flash37.aop.detail.repository;

import org.jesperancinha.spring.flash37.aop.detail.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
