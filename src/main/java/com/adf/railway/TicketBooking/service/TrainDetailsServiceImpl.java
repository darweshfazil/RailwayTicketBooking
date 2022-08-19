package com.adf.railway.TicketBooking.service;

import com.adf.railway.TicketBooking.entity.TrainDetails;
import com.adf.railway.TicketBooking.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainDetailsServiceImpl implements TrainDetailsService{

    @Autowired
    private TrainRepository trainRepository;

    @Override
    @Transactional
    public List<TrainDetails> getAllTrains(){
        return trainRepository.findAll();
    }

    @Override
    @Transactional
    public TrainDetails getTrainById(int theId) {
        return trainRepository.findById(theId).orElse(null);
    }

    @Override
    @Transactional
    public void addTrain(TrainDetails trainDetails) {
        trainRepository.save(trainDetails);
    }

    @Override
    @Transactional
    public void deleteTrainById(int theId) {
        trainRepository.deleteById(theId);
    }
}
