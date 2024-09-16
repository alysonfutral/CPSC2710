package edu.au.cpsc.module4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

public class AirlineController {

    @FXML
    private TableView<ScheduledFlight> airlineTable;

    @FXML
    private TableColumn<ScheduledFlight, String> flightDesignatorColumn;

    @FXML
    private TableColumn<ScheduledFlight, String> departureAirportColumn;

    @FXML
    private TableColumn<ScheduledFlight, String> arrivalAirportColumn;

    @FXML
    private TableColumn<ScheduledFlight, String> daysOfWeekColumn;

    @FXML
    private TextField flightDesignator;

    @FXML
    private TextField departureAirportIdent;

    @FXML
    private TextField arrivalAirportIdent;

    @FXML
    private HBox daysOfWeekBox;

    @FXML
    private ToggleButton M;
    @FXML
    private ToggleButton T;
    @FXML
    private ToggleButton W;
    @FXML
    private ToggleButton R;
    @FXML
    private ToggleButton F;
    @FXML
    private ToggleButton S;
    @FXML
    private ToggleButton U;

    private AirlineDatabase database;

    public void initialize() {
        // Load the database instead of creating a new one
        database = AirlineDatabaseFile.getDatabase();

        flightDesignatorColumn.setCellValueFactory(new PropertyValueFactory<>("flightDesignator"));
        departureAirportColumn.setCellValueFactory(new PropertyValueFactory<>("departureAirportIdent"));
        arrivalAirportColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalAirportIdent"));
        daysOfWeekColumn.setCellValueFactory(new PropertyValueFactory<>("daysOfWeek"));

        // Load flights into the table
        refreshFlightTable(); // Updated to reflect loaded data

        airlineTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showFlightDesignator(newValue);
        });
    }

    public void showFlightDesignatorTable(List<ScheduledFlight> flightList) {
        ObservableList<ScheduledFlight> observableList = FXCollections.observableList(flightList);
        SortedList<ScheduledFlight> sortedList = new SortedList<>(observableList);
        airlineTable.setItems(sortedList);
        sortedList.comparatorProperty().bind(airlineTable.comparatorProperty());
    }

    public void showFlightDesignator(ScheduledFlight fd) {
        if (fd == null) {
            flightDesignator.clear();
            departureAirportIdent.clear();
            arrivalAirportIdent.clear();
            return;
        }
        flightDesignator.setText(fd.getFlightDesignator());
        departureAirportIdent.setText(fd.getDepartureAirportIdent());
        arrivalAirportIdent.setText(fd.getArrivalAirportIdent());
    }

    public void updateFlightDesignator(ScheduledFlight fd) {
        fd.setFlightDesignator(flightDesignator.getText());
        fd.setDepartureAirportIdent(departureAirportIdent.getText());
        fd.setArrivalAirportIdent(arrivalAirportIdent.getText());


    }

    @FXML
    public void handleNewFlight(ActionEvent event) {
        // Get user input from TextFields
        String flightDesignatorInput = flightDesignator.getText();
        String departureAirportIdentInput = departureAirportIdent.getText();
        String arrivalAirportIdentInput = arrivalAirportIdent.getText();


        LocalTime departureTimeInput = LocalTime.now();
        LocalTime arrivalTimeInput = LocalTime.now();

        // Create a HashSet for days of the week
        HashSet<String> daysOfWeekInput = new HashSet<>();
        if (M.isSelected()) daysOfWeekInput.add("M");
        if (T.isSelected()) daysOfWeekInput.add("T");
        if (W.isSelected()) daysOfWeekInput.add("W");
        if (R.isSelected()) daysOfWeekInput.add("R");
        if (F.isSelected()) daysOfWeekInput.add("F");
        if (S.isSelected()) daysOfWeekInput.add("S");
        if (U.isSelected()) daysOfWeekInput.add("U");

        // Create a new ScheduledFlight object
        ScheduledFlight newFlight = new ScheduledFlight(flightDesignatorInput,
                departureAirportIdentInput,
                departureTimeInput,
                arrivalAirportIdentInput,
                arrivalTimeInput,
                daysOfWeekInput);

        // Add the new flight to the database
        database.addScheduledFlight(newFlight);

        // Save the database after adding a new flight
        AirlineDatabaseFile.saveDatabase();

        // Refresh the table view
        refreshFlightTable();
    }

    @FXML
    public void handleUpdateFlight(ActionEvent event) {
        ScheduledFlight selectedFlight = airlineTable.getSelectionModel().getSelectedItem();
        if (selectedFlight != null) {
            updateFlightDesignator(selectedFlight);
            database.updateScheduledFlight(selectedFlight);  // Update the flight in the database

            // Save the database after updating a flight
            AirlineDatabaseFile.saveDatabase();

            // Refresh the table view
            refreshFlightTable();
        } else {
            showAlert("No Flight Selected", "Please input a flight to update.");
        }
    }

    @FXML
    public void handleDeleteFlight(ActionEvent event) {
        ScheduledFlight selectedFlight = airlineTable.getSelectionModel().getSelectedItem();
        if (selectedFlight != null) {
            database.removeScheduledFlight(selectedFlight);  // Remove the flight from the database

            // Save the database after deleting a flight
            AirlineDatabaseFile.saveDatabase();

            // Refresh the table view
            refreshFlightTable();
        } else {
            showAlert("No Flight Selected", "Please select a flight to delete.");
        }
    }

    private void refreshFlightTable() {
        showFlightDesignatorTable(database.getScheduledFlights());  // Refresh the table with updated flight list
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

