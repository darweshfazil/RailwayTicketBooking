package com.adf.railway.TicketBooking.repository;

import com.adf.railway.TicketBooking.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findBytrainno(int trainNo);

    @Query("from Schedule where trainno = :Train_No and stationcode = :Station_Code")
    Schedule findByTrainNoStationCode(@Param("Train_No")int trainNo, @Param("Station_Code")String stationCode);

    @Query("delete from Schedule where trainno = :Train_No and stationcode = :Station_Code")
    void deleteByIdAndStationCode(@Param("Train_No")int trainNo, @Param("Station_Code")String stationCode);
}
