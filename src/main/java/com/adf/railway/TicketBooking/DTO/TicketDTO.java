package com.adf.railway.TicketBooking.DTO;

import com.adf.railway.TicketBooking.entity.PassengerDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {

    private int user_id;

    private int train_no;

    private int ticket_no;

    private String source;

    private String destination;

    private List<PassengerDetails> passengers;

    private int adult_count;

    private int child_count;

    private int fare;

    private String arrival_date;

    private String arrival_time;

    private String dept_date;

    private String dept_time;

    private String preferredCoach;

    private int seatid;
}
