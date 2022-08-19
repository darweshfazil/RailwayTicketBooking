package com.adf.railway.TicketBooking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ticket_details")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticket_no;

    @OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
    @JoinColumn(name="userid")
    private UserDetails userDetails;

    @OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
    @JoinColumn(name="train_no")
    private TrainDetails trainDetails;

    @Column(name="adult_count")
    private int adult_count;

    @Column(name="child_count")
    private int child_count;

    @Column(name="source")
    private String source;

    @Column(name="destination")
    private String destination;

    @Column(name="fare")
    private int fare;

    @Column(name="arrival_date")
    private String arrival_date;

    @Column(name="arrival_time")
    private String arrival_time;

    @Column(name="dept_date")
    private String dept_date;

    @Column(name="dept_time")
    private String dept_time;

    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="ticket")
    private List<PassengerDetails> passengerDetails;
}
