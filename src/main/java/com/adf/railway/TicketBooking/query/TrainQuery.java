package com.adf.railway.TicketBooking.query;

import com.adf.railway.TicketBooking.entity.TrainDetails;
import com.adf.railway.TicketBooking.service.TrainDetailsService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainQuery implements GraphQLQueryResolver {

    @Autowired
    private TrainDetailsService trainDetailsService;

    public List<TrainDetails> getAllTrains(){
        return trainDetailsService.getAllTrains();
    }

    public TrainDetails getTrainById(int trainNo) {

        TrainDetails trainDetails = (TrainDetails) trainDetailsService.getTrainById(trainNo);

        if(trainDetails==null) {
            throw new RuntimeException("Train not found - " + trainNo);
        }

        return trainDetails;
    }
}
