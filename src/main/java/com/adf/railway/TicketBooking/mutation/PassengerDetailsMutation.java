package com.adf.railway.TicketBooking.mutation;

import com.adf.railway.TicketBooking.entity.PassengerDetails;
import com.adf.railway.TicketBooking.service.PassengerDetailsService;
import com.adf.railway.TicketBooking.service.SeatingChartService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PassengerDetailsMutation implements GraphQLMutationResolver {

    @Autowired
    private PassengerDetailsService passengerDetailsService;

    @Autowired
    private SeatingChartService seatingChartService;

    public String deletePassengerDetails(int ticketNo, String passengerName) {

        PassengerDetails tempPassengerDetails = passengerDetailsService.getPassengerDetailsById(ticketNo, passengerName);

        // throw exception if null

        if (tempPassengerDetails==null) {
            throw new RuntimeException("Passenger not found - " + passengerName);
        }

        int tempId = tempPassengerDetails.getPassengerid();

        seatingChartService.freeSeatsOccupied(tempPassengerDetails);

        passengerDetailsService.deletePassengerDetailsById(tempId);

        return "Successfully Deleted passenger - " + passengerName;

    }
}
