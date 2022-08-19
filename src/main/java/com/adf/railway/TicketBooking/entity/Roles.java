package com.adf.railway.TicketBooking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="roles")
public class Roles {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int roleid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userid")
    private Login login;

    @Column(name="roles")
    private String roles;

}
