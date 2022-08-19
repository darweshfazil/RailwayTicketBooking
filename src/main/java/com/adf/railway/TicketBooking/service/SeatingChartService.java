package com.adf.railway.TicketBooking.service;

import com.adf.railway.TicketBooking.entity.PassengerDetails;
import com.adf.railway.TicketBooking.entity.SeatingChart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatingChartService {

    List<SeatingChart> getAllSeats();

    SeatingChart getSeatById(int seatId);

    SeatingChart updateSeatById(SeatingChart seatingChart);

    List<SeatingChart> getSeating(String coach);

    SeatingChart getSeatByCoachAndSeat(String coach, int seat);

    void freeSeatsOccupied(int theId);

    void freeSeatsOccupied(PassengerDetails passengerDetails);
}
