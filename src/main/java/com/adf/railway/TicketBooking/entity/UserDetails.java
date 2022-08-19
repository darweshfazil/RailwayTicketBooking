package com.adf.railway.TicketBooking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int userid;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name="aadhaar")
    private String aadhaar;

    @Column(name="email")
    private String email;

    @Column(name="phno")
    private String phno;

    @Column(name="age")
    private int age;

    @Column(name="gender")
    private String gender;

    @Column(name="nationality")
    private String nationality;

    @Column(name="address")
    private String address;

    @OneToOne
    @JoinColumn(name="userid")
    private Login login;
}
