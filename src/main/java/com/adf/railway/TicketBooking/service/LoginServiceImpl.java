package com.adf.railway.TicketBooking.service;

import com.adf.railway.TicketBooking.entity.Login;
import com.adf.railway.TicketBooking.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public Login getByusername(String user) {
        return loginRepository.getByusername(user);
    }

    @Override
    public Login addLogin(Login login) {
        return loginRepository.save(login);
    }

    @Override
    public String allocateRole(String email){
        if(email.contains("@irctc")){
            return "Admin";
        }
        else{
            return "User";
        }
    }
}
