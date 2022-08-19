package com.adf.railway.TicketBooking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="passenger_details")
public class PassengerDetails {

    @ManyToOne(fetch= FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="ticket_no")
    private Ticket ticket;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="passenger_id")
    private int passengerid;

    @Column(name="passenger_name")
    private String passenger_name;

    @Column(name="aadhaar")
    private String aadhaar;

    @Column(name="age")
    private int age;

    @Column(name="gender")
    private String gender;

    @Column(name="coach")
    private String coach;

    @Column(name="seat")
    private String seat;

    @Column(name="berth")
    private String berth;

    @Column(name="reservation")
    private String reservation;
}
