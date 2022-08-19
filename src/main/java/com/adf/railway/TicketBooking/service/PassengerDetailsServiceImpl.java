package com.adf.railway.TicketBooking.service;

import com.adf.railway.TicketBooking.DTO.TicketDTO;
import com.adf.railway.TicketBooking.entity.PassengerDetails;
import com.adf.railway.TicketBooking.entity.TrainDetails;
import com.adf.railway.TicketBooking.repository.PassengerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerDetailsServiceImpl implements PassengerDetailsService {

    @Autowired
    private PassengerDetailsRepository passengerDetailsRepository;

    @Autowired
    private TrainDetailsService trainDetailsService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ScheduleService scheduleService;

    @Override
    @Transactional
    public List<PassengerDetails> getAllPassengerDetails(){
        return passengerDetailsRepository.findAll();
    }

    @Override
    @Transactional
    public PassengerDetails getPassengerDetailsById(int ticketNo, String passengerName) {
        int theId = 0;
        List<PassengerDetails> tempPassengerDetails = ticketService.getTicketById(ticketNo).getPassengerDetails();
        for(PassengerDetails var : tempPassengerDetails){
            if(var.getPassenger_name().equalsIgnoreCase(passengerName)){
                theId = var.getPassengerid();
                break;
            }
        }

        return passengerDetailsRepository.findById(theId).orElse(null);
    }

    @Override
    @Transactional
    public TicketDTO addPassengerDetails(TicketDTO ticketDTO) {

        TrainDetails tempTrainDetails =  trainDetailsService.getTrainById(ticketDTO.getTrain_no());

        ticketDTO.setArrival_date(ticketDTO.getDept_date());
        ticketDTO.setArrival_time(scheduleService.getScheduleByIdAndStationCode(ticketDTO.getTrain_no(), ticketDTO.getSource()).getArrival_time());

        List<PassengerDetails> tempPassengerList = ticketDTOToPassengerDetails(ticketDTO);

        for(PassengerDetails var : tempPassengerList){
            passengerDetailsRepository.save(var);
        }

        ticketDTO.setPassengers(tempPassengerList);

        return ticketDTO;
    }

    @Override
    @Transactional
    public void deletePassengerDetailsById(int theId) {
        passengerDetailsRepository.deleteById(theId);
    }

    @Override
    public List<PassengerDetails> ticketDTOToPassengerDetails(TicketDTO ticketDTO) {

        List<PassengerDetails> passengerDetailsList = new ArrayList<PassengerDetails>();
        
        for(PassengerDetails var : ticketDTO.getPassengers()){

            PassengerDetails tempPassengerDetails = new PassengerDetails();

            tempPassengerDetails.setTicket(ticketService.getTicketById(ticketDTO.getTicket_no()));
            tempPassengerDetails.setPassenger_name(var.getPassenger_name());
            tempPassengerDetails.setAadhaar(var.getAadhaar());
            tempPassengerDetails.setAge(var.getAge());
            tempPassengerDetails.setGender(var.getGender());
            tempPassengerDetails.setCoach((ticketService.getCoach(var, ticketDTO.getPreferredCoach(), ticketDTO.getPassengers().size())).getCoach());
            var = ticketService.getSeat(var);
            tempPassengerDetails.setBerth(var.getBerth());
            tempPassengerDetails.setSeat(var.getSeat());
            tempPassengerDetails.setReservation(var.getReservation());

            passengerDetailsList.add(tempPassengerDetails);
        }
        return passengerDetailsList;
    }
}
