package com.flightapp.controller;

import com.flightapp.model.Airline;
import com.flightapp.model.FlightSchedule;
import com.flightapp.model.Ticket;
import com.flightapp.repository.AirlineRepository;
import com.flightapp.repository.FlightRepository;
import com.flightapp.repository.FlightScheduleRepository;
import com.flightapp.service.FlightService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1.0/flight")
public class FlightController {

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private FlightScheduleRepository scheduleRepository;

    @Autowired
    private FlightRepository flightRepository;

    // ✔ Add Inventory for an existing Airline
    @PostMapping("/airline/inventory/add")
    public ResponseEntity<?> addInventory(@RequestBody FlightSchedule schedule) {

        // check if airline exists
        Optional<Airline> airline = airlineRepository.findById(schedule.getAirline().getId());
        if (airline.isEmpty()) {
            return ResponseEntity.badRequest().body("Airline does not exist!");
        }

        schedule.setAirline(airline.get());
        scheduleRepository.save(schedule);
        return ResponseEntity.ok("Inventory (schedule) added successfully!");
    }
}

