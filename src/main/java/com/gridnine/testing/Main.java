package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightFilter;
import com.gridnine.testing.service.FlightFilterImpl;
import com.gridnine.testing.util.FlightBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter filter = new FlightFilterImpl();

        flights = filter.excludeFlightsByDepartureBefore(flights, LocalDateTime.now());
        flights = filter.excludeFlightsByArrivalBeforeDeparture(flights);
        flights = filter.excludeFlightsByGroundTimeGreaterThan(flights, 2);

        flights.forEach(System.out::println);
    }
}