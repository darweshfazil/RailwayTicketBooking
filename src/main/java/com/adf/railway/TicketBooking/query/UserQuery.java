package com.adf.railway.TicketBooking.query;

import com.adf.railway.TicketBooking.entity.UserDetails;
import com.adf.railway.TicketBooking.service.UserDetailsService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQuery implements GraphQLQueryResolver {

    @Autowired
    private UserDetailsService userDetailsService;

    public List<UserDetails> getAllUsers(){
        return userDetailsService.getAllUsers();
    }

    public UserDetails getUserById(int userId) {

        UserDetails userDetails = (UserDetails) userDetailsService.getUserById(userId);

        if(userDetails==null) {
            throw new RuntimeException("User id not found - " + userId);
        }

        return userDetails;
    }
}
