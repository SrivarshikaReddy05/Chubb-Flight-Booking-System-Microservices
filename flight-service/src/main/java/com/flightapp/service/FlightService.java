package com.flightapp.service;

import com.flightapp.model.*;
import com.flightapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final AirlineRepository airlineRepo;
    private final FlightRepository flightRepo;
    private final FlightScheduleRepository scheduleRepo;
    private final TicketRepository ticketRepo;

    // ADD INVENTORY
    public FlightSchedule addInventory(FlightSchedule schedule) {
        return scheduleRepo.save(schedule);
    }

    // SEARCH
    public List<FlightSchedule> search(String from, String to, String date) {
        LocalDateTime start = LocalDateTime.parse(date + "T00:00:00");
        LocalDateTime end = LocalDateTime.parse(date + "T23:59:59");

        return scheduleRepo.findByFlight_FromPlaceAndFlight_ToPlaceAndDepartureTimeBetween(
                from, to, start, end
        );
    }

    // BOOK TICKET
    public Ticket book(Long flightScheduleId, Ticket t) {

        FlightSchedule f = scheduleRepo.findById(flightScheduleId).orElseThrow();

        if (f.getAvailableSeats() < t.getNumberOfSeats()) {
            throw new RuntimeException("Not enough seats");
        }

        f.setAvailableSeats(f.getAvailableSeats() - t.getNumberOfSeats());
        scheduleRepo.save(f);

        t.setPnr(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        t.setFlightId(f.getId());
        t.setJourneyDate(f.getDepartureTime().toLocalDate().toString());

        return ticketRepo.save(t);
    }

    // PNR DETAILS
    public Ticket getTicket(String pnr) {
        return ticketRepo.findById(pnr).orElseThrow();
    }

    // HISTORY
    public List<Ticket> history(String email) {
        return ticketRepo.findByEmail(email);
    }

    // CANCEL
    public String cancel(String pnr) {
        Ticket t = ticketRepo.findById(pnr).orElseThrow();
        t.setCancelled(true);
        ticketRepo.save(t);
        return "Cancelled";
    }
}

