/*
Flight Scheduler Project, Module 4
Alyson Futral
CPSC2710
 */
package edu.au.cpsc.module4;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashSet;

public class ScheduledFlight implements Serializable {
    private String flightDesignator;
    private String departureAirportIdent;
    private LocalTime departureTime;
    private String arrivalAirportIdent;
    private LocalTime arrivalTime;
    private HashSet<String> daysOfWeek;

    // constructor to allow for validation and encapsulation
    public ScheduledFlight(String flightDesignator, String departureAirportIdent, LocalTime departureTime,
                           String arrivalAirportIdent, LocalTime arrivalTime, HashSet<String> daysOfWeek) {
        this.flightDesignator = flightDesignator;
        this.departureAirportIdent = departureAirportIdent;
        this.departureTime = departureTime;
        this.arrivalAirportIdent = arrivalAirportIdent;
        this.arrivalTime = arrivalTime;
        this.daysOfWeek = daysOfWeek;
    }

    public String getFlightDesignator() {
        return flightDesignator;
    }
    // include rudimentary validation
    public void setFlightDesignator(String flightDesignator) {
        if (flightDesignator == null) {
            throw new IllegalArgumentException("Flight designator cannot be null.");
        }
        this.flightDesignator = flightDesignator;
    }



    public String getDepartureAirportIdent() {
        return departureAirportIdent;
    }
    // include rudimentary validation
    public void setDepartureAirportIdent(String departureAirportIdent) {
        if (departureAirportIdent == null) {
            throw new IllegalArgumentException("Departure airport identification cannot be null.");
        }
        this.departureAirportIdent = departureAirportIdent;
    }



    public LocalTime getDepartureTime() {
        return departureTime;
    }
    // include rudimentary validation
    public void setDepartureTime(LocalTime departureTime) {
        if (departureTime == null) {
            throw new IllegalArgumentException("Departure time cannot be null.");
        }
        this.departureTime = departureTime;
    }



    public String getArrivalAirportIdent() {
        return arrivalAirportIdent;
    }
    // include rudimentary validation
    public void setArrivalAirportIdent(String arrivalAirportIdent) {
        if (arrivalAirportIdent == null) {
            throw new IllegalArgumentException("Arrival airport identification cannot be null.");
        }
        this.arrivalAirportIdent = arrivalAirportIdent;
    }



    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
    // include rudimentary validation
    public void setArrivalTime(LocalTime arrivalTime) {
        if (arrivalTime == null) {
            throw new IllegalArgumentException("Arrival time cannot be null.");
        }
        this.arrivalTime = arrivalTime;
    }



    public HashSet<String> getDaysOfWeek() {
        return daysOfWeek;
    }
    // include rudimentary validation
    public void setDaysOfWeek(HashSet<String> daysOfWeek) {
        if (daysOfWeek == null) {
            throw new IllegalArgumentException("Days of the week cannot be null.");
        }
        this.daysOfWeek = daysOfWeek;
    }
}

