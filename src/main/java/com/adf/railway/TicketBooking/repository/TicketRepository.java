package com.adf.railway.TicketBooking.repository;

import com.adf.railway.TicketBooking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
