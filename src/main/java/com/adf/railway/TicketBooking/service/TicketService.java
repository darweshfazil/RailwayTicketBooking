package com.adf.railway.TicketBooking.service;

import com.adf.railway.TicketBooking.DTO.TicketDTO;
import com.adf.railway.TicketBooking.entity.PassengerDetails;
import com.adf.railway.TicketBooking.entity.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    List<TicketDTO> getAllTickets();

    Ticket getTicketById(int theId);

    Ticket addTicket(TicketDTO ticketDTO);

    void deleteTicketById(int theId);

    Ticket ticketDTOToTicket(TicketDTO ticketDTO);

    int calculateFare(TicketDTO ticket);

    PassengerDetails getCoach(PassengerDetails passengerDetails, String coach, int size);

    PassengerDetails getSeat(PassengerDetails passengerDetails);

    TicketDTO ticketToTicketDTO(Ticket tempTicket, TicketDTO tempTicketDTO);
}
