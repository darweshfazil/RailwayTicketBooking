package com.adf.railway.TicketBooking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="train_details")
public class TrainDetails implements Serializable {

    @Id
    @Column(name="train_no")
    private int train_no;

    @Column(name="train_name")
    private String train_name;

    @Column(name="source")
    private String source;

    @Column(name="destination")
    private String destination;

    @Column(name="days")
    private String days;

    // mappedBy must be the name of the variable defined in the TrainDetails class
    //private TrainDetails trainDetails;

    @OneToMany(fetch= FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "trainno")
    private List<Schedule> schedules;
}