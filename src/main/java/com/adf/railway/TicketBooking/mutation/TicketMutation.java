package com.adf.railway.TicketBooking.mutation;

import com.adf.railway.TicketBooking.DTO.TicketDTO;
import com.adf.railway.TicketBooking.entity.Ticket;
import com.adf.railway.TicketBooking.service.PassengerDetailsService;
import com.adf.railway.TicketBooking.service.TicketService;
import com.adf.railway.TicketBooking.service.TrainDetailsService;
import com.adf.railway.TicketBooking.service.UserDetailsService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketMutation implements GraphQLMutationResolver {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PassengerDetailsService passengerDetailsService;

    @Autowired
    private TrainDetailsService trainDetailsService;

    @Autowired
    private UserDetailsService userDetailsService;

    public TicketDTO addTicket(TicketDTO ticketDTO) {

        ticketDTO = ticketService.ticketToTicketDTO(ticketService.addTicket(ticketDTO), ticketDTO);

        return passengerDetailsService.addPassengerDetails(ticketDTO);
    }

    public String deleteTicket(int ticketNo) {

        Ticket tempTicket = ticketService.getTicketById(ticketNo);

        // throw exception if null

        if (tempTicket==null) {
            throw new RuntimeException("Ticket not found - " + ticketNo);
        }

        ticketService.deleteTicketById(ticketNo);

        return "Successfully Deleted ticket - " + ticketNo;

    }
}
