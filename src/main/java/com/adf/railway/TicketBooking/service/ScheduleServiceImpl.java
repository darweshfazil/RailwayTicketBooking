package com.adf.railway.TicketBooking.service;

import com.adf.railway.TicketBooking.entity.Schedule;
import com.adf.railway.TicketBooking.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getScheduleById(int trainNo) {
        return scheduleRepository.findBytrainno(trainNo);
    }

    @Override
    public Schedule getScheduleByIdAndStationCode(int trainNo, String stationCode){
        return scheduleRepository.findByTrainNoStationCode(trainNo, stationCode);
    }

    @Override
    public void addSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public void deleteScheduleById(int trainNo) {
        scheduleRepository.deleteById(trainNo);
    }

    @Override
    public void deleteScheduleByIdAndStationCode(int trainNo, String stationCode) {
        scheduleRepository.deleteByIdAndStationCode(trainNo, stationCode);
    }
}
