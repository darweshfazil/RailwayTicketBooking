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
@Table(name="schedule")
@IdClass(ScheduleId.class)
public class Schedule {

    @Column(name="SN")
    private int SN;

    @Id
    @Column(name="trainno")
    private int trainno;

    @Id
    @Column(name="stationcode")
    private String stationcode;

    @Column(name="A")
    private int A;

    @Column(name="AA")
    private int AA;

    @Column(name="AAA")
    private int AAA;

    @Column(name="SL")
    private int SL;

    @Column(name="Station_Name")
    private String Station_Name;

    @Column(name="Route_Number")
    private String Route;

    @Column(name="Arrival_time")
    private String Arrival_time;

    @Column(name="Departure_Time")
    private String Departure_Time;

    @Column(name="Distance")
    private int Distance;
}
