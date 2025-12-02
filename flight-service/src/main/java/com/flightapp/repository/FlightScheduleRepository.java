package com.flightapp.repository;


import com.flightapp.model.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {

    List<FlightSchedule> findByFlight_FromPlaceAndFlight_ToPlaceAndDepartureTimeBetween(
            String from, String to, LocalDateTime start, LocalDateTime end);
}
