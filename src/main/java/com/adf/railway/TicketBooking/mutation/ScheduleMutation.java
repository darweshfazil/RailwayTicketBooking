package com.adf.railway.TicketBooking.mutation;

import com.adf.railway.TicketBooking.entity.Schedule;
import com.adf.railway.TicketBooking.service.ScheduleService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleMutation implements GraphQLMutationResolver {

    @Autowired
    private ScheduleService scheduleService;

    @PreAuthorize("hasRoles('ADMIN')")
    public Schedule addSchedule(Schedule tempSchedule) {

        scheduleService.addSchedule(tempSchedule);

        return tempSchedule;

    }

    @PreAuthorize("hasRoles('ADMIN')")
    public Schedule updateSchedule(Schedule tempSchedule) {

        Schedule tempScheduleDetails = scheduleService.getScheduleByIdAndStationCode(tempSchedule.getTrainno(), tempSchedule.getStationcode());

        if(tempScheduleDetails==null){
            throw new RuntimeException("Schedule not found for " + tempSchedule.getTrainno() + " of Station Code "+tempSchedule.getStationcode());
        }

        scheduleService.addSchedule(tempSchedule);

        return tempSchedule;
    }

    @PreAuthorize("hasRoles('ADMIN')")
    public String deleteSchedule(int trainNo, String stationCode) {

        Schedule tempSchedule = scheduleService.getScheduleByIdAndStationCode(trainNo, stationCode);

        // throw exception if null

        if (tempSchedule==null) {
            throw new RuntimeException("Schedule not found for " + trainNo + " of Station Code "+stationCode);
        }

        scheduleService.deleteScheduleByIdAndStationCode(trainNo, stationCode);

        return "Successfully Deleted Schedule for - " + trainNo + " with Station Code - " + stationCode;

    }

    @PreAuthorize("hasRoles('ADMIN')")
    public String deleteTrainSchedule(int trainNo) {

        List<Schedule> tempScheduleList = scheduleService.getScheduleById(trainNo);

        // throw exception if null

        if (tempScheduleList==null) {
            throw new RuntimeException("Schedule not found for " + trainNo);
        }

        scheduleService.deleteScheduleById(trainNo);

        return "Successfully Deleted Schedule for train - " + trainNo;

    }
}
