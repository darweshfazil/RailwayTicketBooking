package com.adf.railway.TicketBooking.service;

import com.adf.railway.TicketBooking.entity.TrainDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainDetailsService {

    List<TrainDetails> getAllTrains();

    TrainDetails getTrainById(int theId);

    void addTrain(TrainDetails trainDetails);

    void deleteTrainById(int theId);
}
