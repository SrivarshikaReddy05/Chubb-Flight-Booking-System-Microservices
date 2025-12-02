package com.flightapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromPlace;
    private String toPlace;

    @ManyToOne
    private Airline airline;
}
