package com.gridnine.testing.service;


import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.List;

public class FlightFilterImpl implements FlightFilter {
    @Override
    public List<Flight> excludeFlightsByDepartureBefore(List<Flight> flights, LocalDateTime time) {
        return flights.stream().filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getDepartureDate().isBefore(time)))
                .toList();
    }

    @Override
    public List<Flight> excludeFlightsByArrivalBeforeDeparture(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .toList();
    }

    @Override
    public List<Flight> excludeFlightsByGroundTimeGreaterThan(List<Flight> flights, int hours) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    int groundHours = 0;

                    for (int i = 0; i < segments.size() - 1; i++) {
                        int arrivalHour = segments.get(i).getArrivalDate().getHour(), departureHour = segments.get(i + 1).getDepartureDate().getHour();

                        if (arrivalHour != departureHour)
                            groundHours += Math.abs(arrivalHour - departureHour);
                    }

                    return groundHours <= hours;
                }).toList();
    }
}