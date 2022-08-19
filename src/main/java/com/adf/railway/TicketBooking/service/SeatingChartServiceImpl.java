package com.adf.railway.TicketBooking.service;

import com.adf.railway.TicketBooking.entity.PassengerDetails;
import com.adf.railway.TicketBooking.entity.SeatingChart;
import com.adf.railway.TicketBooking.entity.Ticket;
import com.adf.railway.TicketBooking.repository.SeatingChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatingChartServiceImpl implements SeatingChartService{

    @Autowired
    private SeatingChartRepository seatingChartRepository;

    @Autowired
    private TicketService ticketService;

    @Override
    public List<SeatingChart> getAllSeats() {
        return seatingChartRepository.findAll();
    }

    @Override
    public SeatingChart getSeatById(int seatId) {
        return seatingChartRepository.findById(seatId).orElse(null);
    }

    @Override
    public SeatingChart updateSeatById(SeatingChart seatingChart) {
        return seatingChartRepository.save(seatingChart);
    }

    @Override
    public List<SeatingChart> getSeating(String coach){
        return seatingChartRepository.getSeating(coach);
    }

    @Override
    public SeatingChart getSeatByCoachAndSeat(String coach, int seat) {
        return seatingChartRepository.getSeatByCoachAndSeat(coach, seat);
    }

    @Override
    public void freeSeatsOccupied(int theId){
        Ticket tempTicket = ticketService.getTicketById(theId);
        for(PassengerDetails var : tempTicket.getPassengerDetails()){
            SeatingChart tempSeatingChart = getSeatByCoachAndSeat(var.getCoach(), Integer.parseInt(var.getSeat()));
            tempSeatingChart.setOccupied(0);
            updateSeatById(tempSeatingChart);
        }
    }

    @Override
    public void freeSeatsOccupied(PassengerDetails passengerDetails){
        SeatingChart tempSeatingChart = getSeatByCoachAndSeat(passengerDetails.getCoach(), Integer.parseInt(passengerDetails.getSeat()));
        tempSeatingChart.setOccupied(0);
        updateSeatById(tempSeatingChart);
    }
}
