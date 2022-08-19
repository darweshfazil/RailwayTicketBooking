package com.adf.railway.TicketBooking.service;

import com.adf.railway.TicketBooking.entity.Schedule;

import java.util.List;

public interface ScheduleService {

    List<Schedule> getAllSchedule();

    Schedule getScheduleByIdAndStationCode(int trainNo, String stationCode);

    List<Schedule> getScheduleById(int trainNo);

    void addSchedule(Schedule schedule);

    void deleteScheduleById(int trainNo);

    void deleteScheduleByIdAndStationCode(int trainNo, String stationCode);

}
