package com.adf.railway.TicketBooking.service;

import com.adf.railway.TicketBooking.DTO.TicketDTO;
import com.adf.railway.TicketBooking.entity.PassengerDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PassengerDetailsService {

    List<PassengerDetails> getAllPassengerDetails();

    PassengerDetails getPassengerDetailsById(int ticketNo, String passengerName);

    TicketDTO addPassengerDetails(TicketDTO ticketDTO);

    void deletePassengerDetailsById(int theId);

    List<PassengerDetails> ticketDTOToPassengerDetails(TicketDTO ticketDTO);
}
