package com.adf.railway.TicketBooking.service;

import com.adf.railway.TicketBooking.DTO.UserDTO;
import com.adf.railway.TicketBooking.entity.UserDetails;
import com.adf.railway.TicketBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<UserDetails> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserDetails getUserById(int theId) {
        return userRepository.findById(theId).orElse(null);
    }

    @Override
    @Transactional
    public void addUser(UserDetails userDetails) {
        userRepository.save(userDetails);
    }

    @Override
    @Transactional
    public void deleteUserById(int theId) {
        userRepository.deleteById(theId);
    }

    @Override
    public UserDetails userDTOToUser(UserDTO userDTO) {

        UserDetails tempUserDetails = new UserDetails();
        tempUserDetails.setFirstname(userDTO.getFirstname());
        tempUserDetails.setLastname(userDTO.getLastname());
        tempUserDetails.setAadhaar(userDTO.getAadhaar());
        tempUserDetails.setEmail(userDTO.getEmail());
        tempUserDetails.setPhno(userDTO.getPhno());
        tempUserDetails.setAge(userDTO.getAge());
        tempUserDetails.setGender(userDTO.getGender());
        tempUserDetails.setNationality(userDTO.getNationality());
        tempUserDetails.setAddress(userDTO.getAddress());

        return tempUserDetails;
    }


}
