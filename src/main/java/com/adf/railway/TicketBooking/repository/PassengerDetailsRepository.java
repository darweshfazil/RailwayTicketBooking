package com.adf.railway.TicketBooking.repository;

import com.adf.railway.TicketBooking.entity.PassengerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerDetailsRepository extends JpaRepository<PassengerDetails, Integer> {

}
