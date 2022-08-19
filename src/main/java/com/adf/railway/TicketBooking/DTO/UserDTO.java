package com.adf.railway.TicketBooking.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String firstname;

    private String lastname;

    private String aadhaar;

    private String email;

    private String phno;

    private int age;

    private String gender;

    private String nationality;

    private String address;

    private String username;

    private String password;

    private String role;
}
