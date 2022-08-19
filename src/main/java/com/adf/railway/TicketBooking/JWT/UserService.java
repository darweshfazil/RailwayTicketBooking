package com.adf.railway.TicketBooking.JWT;

import com.adf.railway.TicketBooking.entity.Login;
import com.adf.railway.TicketBooking.entity.Roles;
import com.adf.railway.TicketBooking.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private org.springframework.security.core.userdetails.UserDetailsService userDetailsService;

    @Autowired
    private LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{

        Login tempCredentials = loginService.getByusername(userName);
        List<Roles> templist= tempCredentials.getRoles();
        Set<GrantedAuthority> authorities = new HashSet<>(templist.size());

        for (Roles role : templist)
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoles()));

        return new User(tempCredentials.getUsername(), tempCredentials.getPassword(), authorities);
    }
}
