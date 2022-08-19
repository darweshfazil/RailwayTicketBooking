package com.adf.railway.TicketBooking.mutation;

import com.adf.railway.TicketBooking.entity.TrainDetails;
import com.adf.railway.TicketBooking.service.TrainDetailsService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class TrainMutation implements GraphQLMutationResolver {

    @Autowired
    private TrainDetailsService trainDetailsService;

    @PreAuthorize("hasRoles('ADMIN')")
    public TrainDetails addTrainDetails(TrainDetails trainDetails) {

        // also just in case they pass an id in the JSON... set id to 0
        // this is to force a save of new item... instead of update

        trainDetails.setTrain_no(0);

        trainDetailsService.addTrain(trainDetails);

        return trainDetails;

    }

    @PreAuthorize("hasRoles('ADMIN')")
    public TrainDetails updateTrainDetails(TrainDetails trainDetails) {

        if(trainDetailsService.getTrainById(trainDetails.getTrain_no())==null){
            throw new RuntimeException("Train do not exist - " + trainDetails.getTrain_no());
        }

        trainDetailsService.addTrain(trainDetails);

        return trainDetails;
    }

    @PreAuthorize("hasRoles('ADMIN')")
    public String deleteTrainDetails(int trainNo) {

        TrainDetails tempTrainDetails = (TrainDetails) trainDetailsService.getTrainById(trainNo);

        // throw exception if null

        if (tempTrainDetails==null) {
            throw new RuntimeException("Train not found - " + trainNo);
        }

        trainDetailsService.deleteTrainById(trainNo);

        return "Successfully Deleted train - " + trainNo;

    }
}
