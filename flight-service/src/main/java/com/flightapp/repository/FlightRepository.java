package com.flightapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.model.Flight;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {}
