
//open folder not java project redo git init/ branches etc

import java.time.LocalDate;

public class SeatReservation {
    // instance variables
    private String flightDesignator;
    private java.time.LocalDate flightDate;
    private String firstName;
    private String lastName;

    public String getFlightDesignator() {
        return flightDesignator;
    }

    public void setFlightDesignator(String fd) {
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

    public String toString() {
        /*return a string representing this object using 
        *"null" for all instance variables that are null.
        *
        * ternary operator for null checks
        */

        return "SeatReservation{flightDesignator=" + 
        (flightDesignator == null ? "null" : flightDesignator) +
        ",flightDate=" + (flightDate == null ? "null" : flightDate.toString()) +
        ",firstName=" + (firstName == null ? "null" : firstName) +
        ",lastName=" + (lastName == null ? "null" : lastName);

    }


}


