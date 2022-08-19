package com.adf.railway.TicketBooking.query;

import com.adf.railway.TicketBooking.DTO.TicketDTO;
import com.adf.railway.TicketBooking.entity.Ticket;
import com.adf.railway.TicketBooking.service.TicketService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketQuery implements GraphQLQueryResolver {

    @Autowired
    private TicketService ticketService;

    public List<TicketDTO> getAllTickets(){
        return ticketService.getAllTickets();
    }

    public Ticket getTicketById(int ticketNo) {

        Ticket ticket = ticketService.getTicketById(ticketNo);

        if(ticket==null) {
            throw new RuntimeException("Ticket not found - " + ticketNo);
        }

        return ticket;
    }
}
