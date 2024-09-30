package edu.au.cpsc.module6;

import javafx.beans.property.*;

import java.time.LocalDate;

public class SeatReservation {
    // instance variables
    // modify module2 assignment to use property bindings
    private StringProperty flightDesignator = new SimpleStringProperty();
    private ObjectProperty<LocalDate> flightDate = new SimpleObjectProperty<>();
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private IntegerProperty numberOfBags = new SimpleIntegerProperty();
    private BooleanProperty flyingWithInfant = new SimpleBooleanProperty();

    // validation logic, use boolean properties
    private BooleanProperty validFlightDesignator = new SimpleBooleanProperty(true);
    private BooleanProperty validFirstName = new SimpleBooleanProperty(true);
    private BooleanProperty validLastName = new SimpleBooleanProperty(true);


    public StringProperty getFlightDesignator() {
        return flightDesignator;
    }

    public String getFlightDesignatorProperty() {
        return flightDesignator.get();
    }

    public void setFlightDesignator(String fd) {

        if (fd == null){
            throw new IllegalArgumentException("Flight designator cannot be null");
        }

        if (fd.length() < 4 || fd.length() > 6) {
            throw new IllegalArgumentException("The flightDesignator's length should be between 4 and 6 characters.");
        }

        validFlightDesignator.set(true);
        flightDesignator.set(fd);
    }


//    public String toString() {
//
//        return "SeatReservation{flightDesignator=" +
//                (flightDesignator == null ? "null" : flightDesignator) +
//                ",flightDate=" + (flightDate == null ? "null" : flightDate.toString()) +
//                ",firstName=" + (firstName == null ? "null" : firstName) +
//                ",lastName=" + (lastName == null ? "null" : lastName) +
//                ", numberOfBags=" + numberOfBags + ", flyingWithInfant=" +
//                flyingWithInfant + "}";
//
//    }

}

/*
Resources:
https://edencoding.com/javafx-properties-and-binding-a-complete-guide/
 */