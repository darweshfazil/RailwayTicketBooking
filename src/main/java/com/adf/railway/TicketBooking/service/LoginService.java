package com.adf.railway.TicketBooking.service;

import com.adf.railway.TicketBooking.entity.Login;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    Login getByusername(String user);

    Login addLogin(Login login);

    String allocateRole(String email);
}
