package com.adf.railway.TicketBooking.repository;

import com.adf.railway.TicketBooking.entity.TrainDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<TrainDetails, Integer> {
}
