package edu.au.cpsc.module6;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.time.LocalDate;

public class SeatReservationApplication extends Application { //basic application (base class of javaFX)
    // i-var for SeatReservation
    private SeatReservation seatReservation;

    @Override
    public void start(Stage primaryStage) throws IOException { //create instance of class stage
        // initialize seatReservation, call with updateUI() later to populate data
        // prepopulated
        seatReservation = new SeatReservation();
        seatReservation.setFlightDesignator("LAX727");
        seatReservation.setFlightDate(LocalDate.now());
        seatReservation.setFirstName("Alyson");
        seatReservation.setLastName("Futral");
        seatReservation.setNumberOfBags(1);
        seatReservation.makeNotFlyingWithInfant();

        BorderPane borderPane = new BorderPane(); //allow contents to be placed in accordance to the dimensions of the border
        GridPane gridPane = new GridPane(); //allow contents to be placed in grid structure(columns and rows)

        // use textfield to give interactive textbox experience
        TextField flightDesignatorField = new TextField();
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        DatePicker flightDatePicker = new DatePicker();
        TextField numberOfPassengersField = new TextField();
        TextField seatNumberField = new TextField();
        numberOfPassengersField.setEditable(false); //not editable
        CheckBox flyingWithInfantCheckBox = new CheckBox("Flying with Infant");

        //allow elements to be placed in grid structure(columns and rows)
        gridPane.add(new Label("Flight Designator:"), 0, 0);
        gridPane.add(flightDesignatorField, 1, 0);
        gridPane.add(new Label("Flight Date:"), 0, 1);
        gridPane.add(flightDatePicker, 1, 1);
        gridPane.add(new Label("First Name:"), 0, 2);
        gridPane.add(firstNameField, 1, 2);
        gridPane.add(new Label("Last Name:"), 0, 3);
        gridPane.add(lastNameField, 1, 3);
        gridPane.add(new Label("Number of Bags:"), 0, 4);
        gridPane.add(seatNumberField, 1, 4);
        gridPane.add(new Label("Passengers:"), 0, 5);
        gridPane.add(numberOfPassengersField, 1, 5);
        gridPane.add(flyingWithInfantCheckBox, 1, 6);

        // create button bar (ref: m2020 ppt, createButtonBar())
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
        HBox buttonBox = new HBox(saveButton, cancelButton); //allow single row for buttons
        borderPane.setCenter(gridPane);
        borderPane.setBottom(buttonBox);

        // build the stage (ref: m2020 ppt)
        Scene scene = new Scene(borderPane, 400, 300);
        primaryStage.setTitle("Seat Reservation");
        primaryStage.setScene(scene);
        primaryStage.show(); //sends the message to make the stage (interactive window) visible

        // updateUI() to display flight information
        // initialize values to show when program starts
        updateUI(flightDesignatorField, firstNameField, lastNameField, flightDatePicker, numberOfPassengersField, seatNumberField, flyingWithInfantCheckBox);

        // ref:
        // https://docs.oracle.com/javase/8/javafx/api/javafx/event/ActionEvent.html
        // https://docs.oracle.com/javase/8/javafx/api/javafx/event/EventHandler.html
        flyingWithInfantCheckBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (flyingWithInfantCheckBox.isSelected()) {
                    numberOfPassengersField.setText("2");
                } else {
                    numberOfPassengersField.setText("1");
                }
            }
        });


        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    // Populate the seatReservation object with values from fields
                    seatReservation.setFlightDesignator(flightDesignatorField.getText());
                    seatReservation.setFlightDate(flightDatePicker.getValue());
                    seatReservation.setFirstName(firstNameField.getText());
                    seatReservation.setLastName(lastNameField.getText());
                    seatReservation.setNumberOfBags(Integer.parseInt(seatNumberField.getText())); // use wrapper to allow int variable to be inputted
                    seatReservation.makeFlyingWithInfant(flyingWithInfantCheckBox.isSelected());


                    System.out.println(seatReservation);
                    Platform.exit();
                } catch (IllegalArgumentException exit) {
                    System.out.println("Error: " + exit.getMessage()); // helpful error message
                }
            }
        });

        // displays the cancel message when pressed
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Cancel clicked");
                Platform.exit();
            }
        });
    }

    // takes i-var and displays in controls
    private void updateUI(TextField flightDesignatorField, TextField firstNameField, TextField lastNameField, DatePicker flightDatePicker, TextField numberOfPassengersField, TextField seatNumberField, CheckBox flyingWithInfantCheckBox) {
        flightDesignatorField.setText(seatReservation.getFlightDesignator());
        flightDatePicker.setValue(seatReservation.getFlightDate());
        firstNameField.setText(seatReservation.getFirstName());
        lastNameField.setText(seatReservation.getLastName());
        numberOfPassengersField.setText(seatReservation.isFlyingWithInfant() ? "2" : "1");
        seatNumberField.setText(String.valueOf(seatReservation.getNumberOfBags()));
        flyingWithInfantCheckBox.setSelected(seatReservation.isFlyingWithInfant());
    }

    public static void main(String[] args) {
        launch(); //must include launch() to begin app
    }
}