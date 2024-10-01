package edu.au.cpsc.module6;

import java.time.LocalDate;

public class SeatReservation {
    // instance variables
    private String flightDesignator;
    private java.time.LocalDate flightDate;
    private String firstName;
    private String lastName;
    private int numberOfBags;
    private boolean flyingWithInfant;

    public String getFlightDesignator() {
        return flightDesignator;
    }

    public void setFlightDesignator(String fd) {

        if (fd == null){
            throw new IllegalArgumentException("flight designator cannot be null");
        }

        if (fd.length() < 4 || fd.length() > 6) {
            throw new IllegalArgumentException("The flightDesignator's length should be between 4 and 6 characters.");
        }

        this.flightDesignator = fd;
    }

    // return using .toString()
    public LocalDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(LocalDate date) {
        this.flightDate = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fn) {
        this.firstName = fn;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String ln) {
        this.lastName = ln;
    }

    public int getNumberOfBags() {
        return numberOfBags;
    }

    public void setNumberOfBags(int nb) {
        this.numberOfBags = nb;
    }

    public boolean isFlyingWithInfant() {
        return flyingWithInfant;
    }

    public void makeFlyingWithInfant(boolean flyingWithInfant) {
        this.flyingWithInfant = flyingWithInfant;
    }

    public String toString() {

        return "SeatReservation{flightDesignator=" +
                (flightDesignator == null ? "null" : flightDesignator) +
                ",flightDate=" + (flightDate == null ? "null" : flightDate.toString()) +
                ",firstName=" + (firstName == null ? "null" : firstName) +
                ",lastName=" + (lastName == null ? "null" : lastName) +
                ", numberOfBags=" + numberOfBags + ", flyingWithInfant=" +
                flyingWithInfant + "}";

    }

}