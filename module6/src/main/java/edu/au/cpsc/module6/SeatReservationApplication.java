/**
 * Module 6 Assignment
 * CPSC 2710 - Software Construction
 * Alyson Futral
 * This project produces a Scene (window) that provides users to
 * interact with a program that acts as an airplane ticket purchase.
 *
 * UPDATED, 9/29/24
 * USES PROPERTY BINDINGS, ENABLE/DISABLE, VALIDATION LOGIC
 *
 */


package edu.au.cpsc.module6;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.time.LocalDate;

public class SeatReservationApplication extends Application { //basic application (base class of javaFX
    // i-var for SeatReservation
    private SeatReservation seatReservation;

    @Override
    public void start(Stage primaryStage) throws IOException { //create instance of class stage
        // initialize seatReservation, call with updateUI() later to populate data
        // prepopulated
        // UPDATED with property bindings
        seatReservation = new SeatReservation();
        seatReservation.setFlightDesignator("LAX727");
        seatReservation.setFlightDate(LocalDate.now());
        seatReservation.setFirstName("");
        seatReservation.setLastName("");
        seatReservation.setNumberOfBags(1);
        seatReservation.makeFlyingWithInfant(false);

        BorderPane borderPane = new BorderPane(); //allow contents to be placed in accordance to the dimensions of the border
        GridPane gridPane = new GridPane(); //allow contents to be placed in grid structure(columns and rows)

        TextField flightDesignatorField = new TextField();

        // use editableProperty() to disable textfield
        flightDesignatorField.setText(seatReservation.getFlightDesignator());
        flightDesignatorField.editableProperty().set(false);
        // used setStyle instead of CSS (small changes in class), allows for 'disable button' look
        flightDesignatorField.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: #333333;");
        // use disableProperty() to disable button
        Button changeFlight = new Button("Update Flight");
        changeFlight.disableProperty().bind(flightDesignatorField.textProperty().isEmpty());
        changeFlight.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: #333333; -fx-opacity: 1.0;");

        TextField arrivalTimeField = new TextField();
        // preset textfield
        arrivalTimeField.setText("6:00PM EST");
        // use editableProperty() to disable textfield
        arrivalTimeField.editableProperty().set(false);
        arrivalTimeField.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: #333333;");

        TextField departureTimeField = new TextField();
        departureTimeField.setText("8:00AM PST");
        departureTimeField.editableProperty().set(false);
        departureTimeField.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: #333333;");

        Label changeFlightLabel = new Label("**Notice: If you need to update your flight, \n contact customer service at 555-1287.");
        changeFlightLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #666666;");

        TextField firstNameField = new TextField();
        // promptText shows in textfield prior to user input
        firstNameField.setPromptText("Verify First Name Matches Passport");
        // textfields too small, widened to show full promptText()
        firstNameField.setPrefWidth(300);

        TextField lastNameField = new TextField();
        // promptText shows in textfield prior to user input
        lastNameField.setPromptText("Verify Last Name Matches Passport");
        // textfields too small, widened to show full promptText()
        lastNameField.setPrefWidth(300);

        // validation logic for character count for first and last names
        Label validityLabel = new Label();
        validityLabel.textProperty().bind(
                Bindings.when(
                                firstNameField.textProperty().length().greaterThan(0)
                                        .and(lastNameField.textProperty().length().greaterThan(0))
                        )
                        .then("\tValid. Verify Names \n\t Match Passport.")
                        .otherwise("\tInvalid. Please fill in \n\t first and last name.")
        );

        DatePicker flightDatePicker = new DatePicker();
        // use editableProperty() to disable textfield
        TextField numberOfPassengersField = new TextField();
        numberOfPassengersField.editableProperty().set(false);
        numberOfPassengersField.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: #333333;");
        TextField seatNumberField = new TextField();
        numberOfPassengersField.setEditable(false);
        CheckBox flyingWithInfantCheckBox = new CheckBox("Flying with Infant");
        gridPane.add(new Label("Flight Designator:"), 0, 0);
        gridPane.add(flightDesignatorField, 1, 0);
        gridPane.add(new Label("Flight Date:"), 0, 1);
        gridPane.add(flightDatePicker, 1, 1);
        gridPane.add(new Label("Arrival Time:"), 0, 2);
        gridPane.add(arrivalTimeField, 1, 2);
        gridPane.add(new Label("Departure Time:"), 0, 3);
        gridPane.add(departureTimeField, 1, 3);
        gridPane.add(new Label("First Name:"), 0, 4);
        gridPane.add(firstNameField, 1, 4);
        gridPane.add(new Label("Last Name:"), 0, 5);
        gridPane.add(lastNameField, 1, 5);
        gridPane.add(new Label("Number of Bags:"), 0, 6);
        gridPane.add(seatNumberField, 1, 6);
        gridPane.add(new Label("Passengers:"), 0, 7);
        gridPane.add(numberOfPassengersField, 1, 7);
        gridPane.add(flyingWithInfantCheckBox, 1, 8);
        gridPane.add(new Label(""), 1, 10);
        gridPane.add(changeFlightLabel, 1, 12);
        gridPane.add(changeFlight, 3, 12);
        gridPane.add(validityLabel, 3, 5);

        // create button bar (ref: m2020 ppt, createButtonBar())
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
        HBox buttonBox = new HBox(saveButton, cancelButton); //allow single row for buttons
        borderPane.setCenter(gridPane);
        borderPane.setBottom(buttonBox);

        // build the stage (ref: m2020 ppt)
        Scene scene = new Scene(borderPane, 550, 400);
        primaryStage.setTitle("Seat Reservation");
        primaryStage.setScene(scene);
        primaryStage.show(); //sends the message to make the stage (interactive window) visible

        // updateUI() to display flight information
        // initialize values to show when program starts
        updateUI(flightDesignatorField, firstNameField, lastNameField, flightDatePicker, numberOfPassengersField, seatNumberField, flyingWithInfantCheckBox);


        // EVENT HANDLERS USED PRIOR TO MODULE6 ASSIGNMENT, KEPT FOR CLEANER UI, CAN BE COMMENTED/DELETED OUT
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
    private void updateUI(TextField flightDesignatorField, TextField firstNameField, TextField lastNameField, DatePicker flightDatePicker,
                          TextField numberOfPassengersField, TextField seatNumberField, CheckBox flyingWithInfantCheckBox) {
        flightDesignatorField.setText(seatReservation.getFlightDesignator());
        flightDatePicker.setValue(seatReservation.getFlightDate());
        firstNameField.setText(seatReservation.getFirstName());
        lastNameField.setText(seatReservation.getLastName());
        seatNumberField.setText(String.valueOf(seatReservation.getNumberOfBags()));
        numberOfPassengersField.setText(seatReservation.isFlyingWithInfant() ? "2" : "1");
        flyingWithInfantCheckBox.setSelected(seatReservation.isFlyingWithInfant());
    }


    public static void main(String[] args) {
        launch(); //must include launch() to begin app
    }
}