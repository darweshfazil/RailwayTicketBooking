package com.adf.railway.TicketBooking.repository;

import com.adf.railway.TicketBooking.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<Login, Integer> {

    @Query("from Login where username = :username")
    Login getByusername(@Param("username")String username);
}
