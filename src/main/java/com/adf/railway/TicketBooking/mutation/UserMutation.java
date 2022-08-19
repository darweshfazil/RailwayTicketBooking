package com.adf.railway.TicketBooking.mutation;

import com.adf.railway.TicketBooking.DTO.UserDTO;
import com.adf.railway.TicketBooking.entity.Login;
import com.adf.railway.TicketBooking.entity.UserDetails;
import com.adf.railway.TicketBooking.service.LoginService;
import com.adf.railway.TicketBooking.service.UserDetailsService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private LoginService loginService;

    public UserDetails addUserDetails(UserDTO userDTO) {

        UserDetails userDetails = userDetailsService.userDTOToUser(userDTO);
        userDetailsService.addUser(userDetails);
        loginService.addLogin(new Login(userDetails.getUserid(), userDTO.getUsername(), userDTO.getPassword()));

        return userDetails;
    }

    public UserDetails updateUserDetails(UserDetails userDetails) {

        if(userDetailsService.getUserById(userDetails.getUserid())==null){
            throw new RuntimeException("No User exist - " + userDetails.getUserid());
        }
        UserDetails tempUser = userDetailsService.getUserById(userDetails.getUserid());
        if(userDetails.getFirstname()!=null){tempUser.setFirstname(userDetails.getFirstname());}
        if(userDetails.getLastname()!=null){tempUser.setLastname(userDetails.getLastname());}
        if(userDetails.getAddress()!=null){tempUser.setAddress(userDetails.getAddress());}
        userDetailsService.addUser(tempUser);

        return tempUser;
    }

    public String deleteUserDetails(int userId) {

        UserDetails tempUserDetails = (UserDetails) userDetailsService.getUserById(userId);

        // throw exception if null

        if (tempUserDetails==null) {
            throw new RuntimeException("User not found - " + userId);
        }

        userDetailsService.deleteUserById(userId);

        return "Successfully Deleted user - " + userId;

    }
}
