/*
Controller class connects to the scenebuilder using FXML to update, create, and delete flight information
Run the program and interact with the flight designator UI
 */

package edu.au.cpsc.module4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
        // load the database
        database = AirlineDatabaseFile.getDatabase();

        // bind date to the table columns
        flightDesignatorColumn.setCellValueFactory(new PropertyValueFactory<>("flightDesignator"));
        departureAirportColumn.setCellValueFactory(new PropertyValueFactory<>("departureAirportIdent"));
        arrivalAirportColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalAirportIdent"));
        daysOfWeekColumn.setCellValueFactory(new PropertyValueFactory<>("daysOfWeek"));

        refreshFlightTable(); // show loaded data

        // uses addListener() to respond when a different flight is selected in the TableView:
        airlineTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showFlightDesignator(newValue);
        });
    }

    // observable list allows for updating of UI TableView or ListView when data changes
    public void showFlightDesignatorTable(List<ScheduledFlight> flightList) {
        ObservableList<ScheduledFlight> observableList = FXCollections.observableList(flightList);
        SortedList<ScheduledFlight> sortedList = new SortedList<>(observableList);
        airlineTable.setItems(sortedList);
        sortedList.comparatorProperty().bind(airlineTable.comparatorProperty());
    }

    // used show fd and to clear fields
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

    // updates flight designator table
    public void updateFlightDesignator(ScheduledFlight fd) {
        if (fd == null) return; // guard against null values

        fd.setFlightDesignator(flightDesignator.getText());
        fd.setDepartureAirportIdent(departureAirportIdent.getText());
        fd.setArrivalAirportIdent(arrivalAirportIdent.getText());

        // Update days of the week
        HashSet<String> daysOfWeekInput = new HashSet<>();
        if (M.isSelected()) daysOfWeekInput.add("M");
        if (T.isSelected()) daysOfWeekInput.add("T");
        if (W.isSelected()) daysOfWeekInput.add("W");
        if (R.isSelected()) daysOfWeekInput.add("R");
        if (F.isSelected()) daysOfWeekInput.add("F");
        if (S.isSelected()) daysOfWeekInput.add("S");
        if (U.isSelected()) daysOfWeekInput.add("U");

        fd.setDaysOfWeek(daysOfWeekInput); // set updated days

        // Call save function to persist changes
        AirlineDatabaseFile.saveDatabase();

        // Refresh table view to reflect updated data
        refreshFlightTable();
    }

    // user inputs data for new flight and updates the table
    @FXML
    public void handleNewFlight(ActionEvent event) {

        String flightDesignatorInput = flightDesignator.getText();
        String departureAirportIdentInput = departureAirportIdent.getText();
        String arrivalAirportIdentInput = arrivalAirportIdent.getText();

        LocalTime departureTimeInput = null;
        LocalTime arrivalTimeInput = null;

        HashSet<String> daysOfWeekInput = new HashSet<>();
        if (M.isSelected())daysOfWeekInput.add("M");
        if (T.isSelected())daysOfWeekInput.add("T");
        if (W.isSelected())daysOfWeekInput.add("W");
        if (R.isSelected())daysOfWeekInput.add("R");
        if (F.isSelected())daysOfWeekInput.add("F");
        if (S.isSelected())daysOfWeekInput.add("S");
        if (U.isSelected())daysOfWeekInput.add("U");

        // new ScheduledFlight object
        ScheduledFlight newFlight = new ScheduledFlight(flightDesignatorInput,
                departureAirportIdentInput,
                departureTimeInput,
                arrivalAirportIdentInput,
                arrivalTimeInput,
                daysOfWeekInput);

        // add new flight
        database.addScheduledFlight(newFlight);

        // save in database
        AirlineDatabaseFile.saveDatabase();

        refreshFlightTable();
    }

    // user inputs data to update flight and updates the table
    @FXML
    public void handleUpdateFlight(ActionEvent event) {
        ScheduledFlight selectedFlight = airlineTable.getSelectionModel().getSelectedItem();

        if (selectedFlight != null) {
            updateFlightDesignator(selectedFlight);

            AirlineDatabaseFile.saveDatabase();

            refreshFlightTable();
        } else {
            alertUser("No Flight Selected", "Please select a flight to update.");
        }
    }

    // user can delete data from table when selected
    @FXML
    public void handleDeleteFlight(ActionEvent event) {
        ScheduledFlight selectedFlight = airlineTable.getSelectionModel().getSelectedItem();
        if (selectedFlight != null) {
            database.removeScheduledFlight(selectedFlight);

            AirlineDatabaseFile.saveDatabase();

            refreshFlightTable();
        } else {
            alertUser("No Flight Selected", "Please select a flight to delete.");
        }
    }

    // method to refresh data in table
    private void refreshFlightTable() {
        showFlightDesignatorTable(database.getScheduledFlights());
    }

    // provide message to user if error occurs after inputting data
    private void alertUser(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

/*
Resources:
https://docs.oracle.com/javafx/2/api/javafx/beans/value/ObservableValue.html
https://stackoverflow.com/questions/42397867/generic-addlistener-in-java
https://edencoding.com/tableview-customization-cellfactory/
https://www.tutorialspoint.com/swingexamples/show_alert_message_dialog.html
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Alert.html
 */
