package com.adf.railway.TicketBooking.service;

import com.adf.railway.TicketBooking.DTO.UserDTO;
import com.adf.railway.TicketBooking.entity.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserDetailsService {

    List<UserDetails> getAllUsers();

    UserDetails getUserById(int theId);

    void addUser(UserDetails userDetails);

    void deleteUserById(int theId);

    UserDetails userDTOToUser(UserDTO userDTO);
}