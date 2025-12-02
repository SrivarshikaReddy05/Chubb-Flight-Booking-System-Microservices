package com.flightapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class FlightSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;
    private String fromPlace;
    private String toPlace;
    private String departureDate;
    private String departureTime;
    private String arrivalTime;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    // getters + setters
}

