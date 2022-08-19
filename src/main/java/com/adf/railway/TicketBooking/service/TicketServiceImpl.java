package com.adf.railway.TicketBooking.service;

import com.adf.railway.TicketBooking.DTO.TicketDTO;
import com.adf.railway.TicketBooking.entity.PassengerDetails;
import com.adf.railway.TicketBooking.entity.Schedule;
import com.adf.railway.TicketBooking.entity.SeatingChart;
import com.adf.railway.TicketBooking.entity.Ticket;
import com.adf.railway.TicketBooking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TrainDetailsService trainDetailsService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SeatingChartService seatingChartService;

    @Override
    @Transactional
    public List<TicketDTO> getAllTickets(){

        List<Ticket> tempTicket = ticketRepository.findAll();
        List<TicketDTO> tempList = new ArrayList<>();

        for(Ticket var : tempTicket){
            TicketDTO tempTicketDTO = new TicketDTO();

            tempTicketDTO.setTicket_no(var.getTicket_no());
            tempTicketDTO.setUser_id(var.getUserDetails().getUserid());
            tempTicketDTO.setTrain_no(var.getTrainDetails().getTrain_no());
            tempTicketDTO.setAdult_count(var.getAdult_count());
            tempTicketDTO.setChild_count(var.getChild_count());
            tempTicketDTO.setSource(var.getSource());
            tempTicketDTO.setDestination(var.getDestination());
            tempTicketDTO.setFare(var.getFare());
            tempTicketDTO.setArrival_date(var.getArrival_date());
            tempTicketDTO.setArrival_time(var.getArrival_time());
            tempTicketDTO.setDept_date(var.getDept_date());
            tempTicketDTO.setDept_time(var.getDept_time());

            tempList.add(tempTicketDTO);
        }

        return tempList;
    }

    @Override
    @Transactional
    public Ticket getTicketById(int theId) {
        return ticketRepository.findById(theId).orElse(null);
    }

    @Override
    @Transactional
    public Ticket addTicket(TicketDTO ticketDTO) {
        checkAvailability(ticketDTO);
        Ticket tempTicket = ticketDTOToTicket(ticketDTO);
        ticketRepository.save(tempTicket);
        return tempTicket;
    }

    @Override
    @Transactional
    public void deleteTicketById(int theId) {
        seatingChartService.freeSeatsOccupied(theId);
        ticketRepository.deleteById(theId);
    }

    @Override
    public Ticket ticketDTOToTicket(TicketDTO ticketDTO){

        Ticket tempTicket = new Ticket();
        tempTicket.setUserDetails(userDetailsService.getUserById(ticketDTO.getUser_id()));
        tempTicket.setTrainDetails(trainDetailsService.getTrainById(ticketDTO.getTrain_no()));
        tempTicket.setAdult_count(ticketDTO.getAdult_count());
        tempTicket.setChild_count(ticketDTO.getChild_count());
        tempTicket.setSource(ticketDTO.getSource());
        tempTicket.setDestination(ticketDTO.getDestination());
        tempTicket.setFare(calculateFare(ticketDTO));
        tempTicket.setArrival_date(ticketDTO.getDept_date());
        tempTicket.setArrival_time(scheduleService.getScheduleByIdAndStationCode(ticketDTO.getTrain_no(), ticketDTO.getSource()).getArrival_time());
        tempTicket.setDept_date(ticketDTO.getDept_date());
        tempTicket.setDept_time(ticketDTO.getDept_time());

        return tempTicket;
    }

    @Override
    public int calculateFare(TicketDTO ticketDTO) {
        int fare = 0;

        Schedule tempSchedule = scheduleService.getScheduleByIdAndStationCode(ticketDTO.getTrain_no(), ticketDTO.getDestination());
        List<PassengerDetails> tempPassengerDetails = ticketDTO.getPassengers();
        for(PassengerDetails var:tempPassengerDetails){
            if(var.getReservation().equalsIgnoreCase("senior citizen")||var.getAge()>=65){
                fare = fare + (int)(tempSchedule.getDistance()*0.65*0.75);
            }
            else if(var.getReservation().equalsIgnoreCase("student")
            ||var.getReservation().equalsIgnoreCase("graduate")){
                fare = fare + (int)(tempSchedule.getDistance()*0.65*0.7);
            }
            else if(var.getReservation().equalsIgnoreCase("ex-military")){
                fare = fare + (int)(tempSchedule.getDistance()*0.65*0.6);
            }
            else {
                fare = fare + (int) (tempSchedule.getDistance() * 0.65);
            }
        }
        if(ticketDTO.getPreferredCoach().equalsIgnoreCase("AAA")){
            fare = fare * 8;
        }
        else if(ticketDTO.getPreferredCoach().equalsIgnoreCase("AA")){
            fare = (int)(fare * 9);
        }
        else if(ticketDTO.getPreferredCoach().equalsIgnoreCase("A")){
            fare = (int)(fare * 10);
        }
        return fare;
    }

    @Override
    public PassengerDetails getCoach(PassengerDetails passengerDetails, String coach, int size) {

        List<SeatingChart> tempSeatingChart = seatingChartService.getAllSeats();

        if(coach.equalsIgnoreCase("A")){
            passengerDetails.setCoach("HA1");
            return passengerDetails;
        }
        else if(coach.equalsIgnoreCase("AA")){
            int a1 = 0;
            int a2 = 0;
            for(SeatingChart var : tempSeatingChart) {
                if (var.getCoach().equalsIgnoreCase("A1")) {a1++;}
                else if (var.getCoach().equalsIgnoreCase("A2")) {a2++;}
            }
            if(a1>=size){
                passengerDetails.setCoach("A1");
                return passengerDetails;
            }
            else if(a2>=size){
                passengerDetails.setCoach("A2");
                return passengerDetails;
            }
            return passengerDetails;
        }
        else if(coach.equalsIgnoreCase("AAA")){
            int b1 = 0;
            int b2 = 0;
            for(SeatingChart var : tempSeatingChart) {
                if (var.getCoach().equalsIgnoreCase("B1")) {b1++;}
                else if (var.getCoach().equalsIgnoreCase("B2")) {b2++;}
            }
            if(b1>=size){
                passengerDetails.setCoach("B1");
                return passengerDetails;
            }
            else if(b2>=size){
                passengerDetails.setCoach("B2");
                return passengerDetails;
            }
            return passengerDetails;
        }
        else{
            int s1 = 0;
            int s2 = 0;
            int s3 = 0;
            int s4 = 0;
            int s5 = 0;
            int s6 = 0;
            int s7 = 0;
            int s8 = 0;
            int s9 = 0;
            int s10 = 0;
            int s11 = 0;
            int s12 = 0;
            int s13 = 0;

            for(SeatingChart var : tempSeatingChart){
                if(var.getCoach().equalsIgnoreCase("S1")){s1++;}
                else if(var.getCoach().equalsIgnoreCase("S2")){s2++;}
                else if(var.getCoach().equalsIgnoreCase("S3")){s3++;}
                else if(var.getCoach().equalsIgnoreCase("S4")){s4++;}
                else if(var.getCoach().equalsIgnoreCase("S5")){s5++;}
                else if(var.getCoach().equalsIgnoreCase("S6")){s6++;}
                else if(var.getCoach().equalsIgnoreCase("S7")){s7++;}
                else if(var.getCoach().equalsIgnoreCase("S8")){s8++;}
                else if(var.getCoach().equalsIgnoreCase("S9")){s9++;}
                else if(var.getCoach().equalsIgnoreCase("S10")){s10++;}
                else if(var.getCoach().equalsIgnoreCase("S11")){s11++;}
                else if(var.getCoach().equalsIgnoreCase("S12")){s12++;}
                else if(var.getCoach().equalsIgnoreCase("S13")){s13++;}
            }
            if(s1>=size){passengerDetails.setCoach("S1");
                return passengerDetails;}
            else if(s2>=size){passengerDetails.setCoach("S2");
                return passengerDetails;}
            else if(s3>=size){passengerDetails.setCoach("S3");
                return passengerDetails;}
            else if(s4>=size){passengerDetails.setCoach("S4");
                return passengerDetails;}
            else if(s5>=size){passengerDetails.setCoach("S5");
                return passengerDetails;}
            else if(s6>=size){passengerDetails.setCoach("S6");
                return passengerDetails;}
            else if(s7>=size){passengerDetails.setCoach("S7");
                return passengerDetails;}
            else if(s8>=size){passengerDetails.setCoach("S8");
                return passengerDetails;}
            else if(s9>=size){passengerDetails.setCoach("S9");
                return passengerDetails;}
            else if(s10>=size){passengerDetails.setCoach("S10");
                return passengerDetails;}
            else if(s11>=size){passengerDetails.setCoach("S11");
                return passengerDetails;}
            else if(s12>=size){passengerDetails.setCoach("S12");
                return passengerDetails;}
            else if(s13>=size){passengerDetails.setCoach("S13");
                return passengerDetails;}

            return passengerDetails;
        }
    }

    @Override
    public PassengerDetails getSeat(PassengerDetails passengerDetails) {
        List<SeatingChart> tempSeatingChart = seatingChartService.getSeating(passengerDetails.getCoach());
        boolean booked = false;
        if(passengerDetails.getAge()>=65||passengerDetails.getAge()<=10||passengerDetails.getGender().equalsIgnoreCase("female")){
            for(SeatingChart var : tempSeatingChart){
                if(var.getOccupied()!=1&&(var.getBerth().equalsIgnoreCase("lower")||var.getBerth().equalsIgnoreCase("side lower")||var.getBerth().equalsIgnoreCase("middle"))){
                    passengerDetails.setBerth(var.getBerth());
                    passengerDetails.setSeat(String.valueOf(var.getSeatno()));
                    var.setOccupied(1);
                    seatingChartService.updateSeatById(var);
                    booked = true;
                    break;
                }
            }
            if(!booked){
                for(SeatingChart var : tempSeatingChart){
                    if(var.getOccupied()!=1&&(var.getBerth().equalsIgnoreCase("lower")||var.getBerth().equalsIgnoreCase("side lower")||var.getBerth().equalsIgnoreCase("middle")||var.getBerth().equalsIgnoreCase("upper"))){
                        passengerDetails.setBerth(var.getBerth());
                        passengerDetails.setSeat(String.valueOf(var.getSeatno()));
                        var.setOccupied(1);
                        seatingChartService.updateSeatById(var);
                        break;
                    }
                }
            }
        }
        else{
            for(SeatingChart var : tempSeatingChart){
                if(var.getOccupied()!=1&&(var.getBerth().equalsIgnoreCase("lower")||var.getBerth().equalsIgnoreCase("side lower"))){
                    passengerDetails.setBerth(var.getBerth());
                    passengerDetails.setSeat(String.valueOf(var.getSeatno()));
                    var.setOccupied(1);
                    seatingChartService.updateSeatById(var);
                    break;
                }
                else if(var.getOccupied()!=1&&(var.getBerth().equalsIgnoreCase("middle"))){
                    passengerDetails.setBerth(var.getBerth());
                    passengerDetails.setSeat(String.valueOf(var.getSeatno()));
                    var.setOccupied(1);
                    seatingChartService.updateSeatById(var);
                    break;
                }
                else if(var.getOccupied()!=1&&(var.getBerth().equalsIgnoreCase("upper")||var.getBerth().equalsIgnoreCase("side upper"))){
                    passengerDetails.setBerth(var.getBerth());
                    passengerDetails.setSeat(String.valueOf(var.getSeatno()));
                    var.setOccupied(1);
                    seatingChartService.updateSeatById(var);
                    break;
                }
            }
        }
        return passengerDetails;
    }

    @Override
    public TicketDTO ticketToTicketDTO(Ticket tempTicket, TicketDTO tempTicketDTO) {

        //update ticketNo and Fare to ticketDTO
        tempTicketDTO.setTicket_no(tempTicket.getTicket_no());
        tempTicketDTO.setFare(tempTicket.getFare());

        return tempTicketDTO;
    }

    public void checkAvailability(TicketDTO ticketDTO){
        Schedule tempSchedule = scheduleService.getScheduleByIdAndStationCode(ticketDTO.getTrain_no(), ticketDTO.getSource());
        if(!ticketDTO.getPreferredCoach().equals("SL")){
            if((ticketDTO.getPreferredCoach().equalsIgnoreCase("A")&&tempSchedule.getA()<ticketDTO.getPassengers().size())
                    ||(ticketDTO.getPreferredCoach().equalsIgnoreCase("AA")&&tempSchedule.getAA()<ticketDTO.getPassengers().size())
                    ||(ticketDTO.getPreferredCoach().equalsIgnoreCase("AAA")&&tempSchedule.getAAA()<ticketDTO.getPassengers().size())){
                throw new RuntimeException("Preferred Coach is Not Available");
            }
        }
        else{
            if(tempSchedule.getSL()<ticketDTO.getPassengers().size()){
                throw new RuntimeException("All seats are Reserved");
            }
        }
    }
}
