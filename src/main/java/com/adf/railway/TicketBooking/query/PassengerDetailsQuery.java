package com.adf.railway.TicketBooking.query;

import com.adf.railway.TicketBooking.entity.PassengerDetails;
import com.adf.railway.TicketBooking.service.PassengerDetailsService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PassengerDetailsQuery implements GraphQLQueryResolver {

    @Autowired
    private PassengerDetailsService passengerDetailsService;

    public List<PassengerDetails> getAllPassengers(){
        return passengerDetailsService.getAllPassengerDetails();
    }

    public PassengerDetails getPassengerById(int ticketNo, String passengerName){
        return passengerDetailsService.getPassengerDetailsById(ticketNo,passengerName);
    }
}
