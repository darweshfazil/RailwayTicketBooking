package com.adf.railway.TicketBooking.repository;

import com.adf.railway.TicketBooking.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDetails, Integer> {

}
