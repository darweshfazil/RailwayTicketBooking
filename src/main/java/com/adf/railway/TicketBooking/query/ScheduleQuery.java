package com.adf.railway.TicketBooking.query;

import com.adf.railway.TicketBooking.entity.Schedule;
import com.adf.railway.TicketBooking.service.ScheduleService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleQuery implements GraphQLQueryResolver {

    @Autowired
    private ScheduleService scheduleService;

    public List<Schedule> getAllSchedule(){
        return scheduleService.getAllSchedule();
    }

    public List<Schedule> getScheduleById(int trainNo){
        return scheduleService.getScheduleById(trainNo);
    }

    public Schedule getScheduleByIdAndStationCode(int trainNo, String stationCode){
        return scheduleService.getScheduleByIdAndStationCode(trainNo, stationCode);
    }
}
