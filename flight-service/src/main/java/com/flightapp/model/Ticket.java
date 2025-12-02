package com.flightapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ticket {

    @Id
    private String pnr;     // generated unique ID

    private Long flightId;
    private String email;
    private int numberOfSeats;

    private String passengers;  // NAME:GENDER:AGE | NAME:GENDER:AGE
    private String mealType;
    private String seatNumbers; // "12A,12B"

    private String bookingDate;
    private String journeyDate;

    private boolean cancelled = false;
}
