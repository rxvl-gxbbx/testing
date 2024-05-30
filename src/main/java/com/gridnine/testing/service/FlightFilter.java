package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightFilter {
    List<Flight> excludeFlightsByDepartureBefore(List<Flight> flights, LocalDateTime time);

    List<Flight> excludeFlightsByArrivalBeforeDeparture(List<Flight> flights);

    List<Flight> excludeFlightsByGroundTimeGreaterThan(List<Flight> flights, int hours);
}