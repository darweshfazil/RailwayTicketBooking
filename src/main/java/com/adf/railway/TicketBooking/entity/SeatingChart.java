package com.adf.railway.TicketBooking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="seating22649")
public class SeatingChart {

    @Id
    private int seatid;

    @Column(name="seatno")
    private int seatno;

    @Column(name="berth")
    private String berth;

    @Column(name="coach")
    private String coach;

    @Column(name="occupied")
    private int occupied;
}
