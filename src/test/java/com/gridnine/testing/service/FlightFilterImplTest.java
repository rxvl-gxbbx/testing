package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.util.FlightBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FlightFilterImplTest {
    private FlightFilter filter;
    private List<Flight> flights;

    @BeforeAll
    void setUp() {
        filter = new FlightFilterImpl();
        flights = FlightBuilder.createFlights();
    }

    @Test
    void whenFilterDepartureBeforeNowResultFive() {
        List<Flight> filterFlights = filter.excludeFlightsByDepartureBefore(flights, LocalDateTime.now());

        Assertions.assertEquals(5, filterFlights.size());
    }

    @Test
    void whenFilterArrivalBeforeDepartureThenResultFive() {
        List<Flight> filterFlights = filter.excludeFlightsByArrivalBeforeDeparture(flights);

        Assertions.assertEquals(5, filterFlights.size());
    }

    @Test
    void whenFilterGroundTimeGreaterThanTwoHoursResultFour() {
        List<Flight> filterFlights = filter.excludeFlightsByGroundTimeGreaterThan(flights, 2);

        Assertions.assertEquals(4, filterFlights.size());
    }

    @Test
    void whenFilterAllResultTwo() {
        List<Flight> filterFlights;

        filterFlights = filter.excludeFlightsByDepartureBefore(flights, LocalDateTime.now());
        filterFlights = filter.excludeFlightsByArrivalBeforeDeparture(filterFlights);
        filterFlights = filter.excludeFlightsByGroundTimeGreaterThan(filterFlights, 2);

        Assertions.assertEquals(2, filterFlights.size());
    }
}