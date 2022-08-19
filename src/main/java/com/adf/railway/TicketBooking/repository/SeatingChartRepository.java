package com.adf.railway.TicketBooking.repository;

import com.adf.railway.TicketBooking.entity.SeatingChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatingChartRepository extends JpaRepository<SeatingChart, Integer> {

    @Query("from SeatingChart where coach = :coach")
    List<SeatingChart> getSeating(@Param("coach")String coach);

    @Query("from SeatingChart where coach = :coach and seatno = :seat")
    SeatingChart getSeatByCoachAndSeat(@Param("coach")String coach, @Param("seat")int seat);
}
