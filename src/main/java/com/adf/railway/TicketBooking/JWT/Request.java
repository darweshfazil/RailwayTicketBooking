package com.adf.railway.TicketBooking.JWT;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    private String username;
    private String password;
}
