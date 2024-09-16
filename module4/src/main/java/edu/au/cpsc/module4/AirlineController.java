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


    public void initialize() {
        flightDesignatorColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight,String>("flightDesignator"));
        departureAirportColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight,String>("departureAirportIdent"));
        arrivalAirportColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight,String>("arrivalAirportIdent"));
        daysOfWeekColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight,String>("daysOfWeek"));

        //airlineTable.getSelectionModel().selectedItemProperty().addListener(c -> tableSelectionChanged());
    }

    public void showFlightDesignatorTable(List<ScheduledFlight> flight) {
        SortedList<ScheduledFlight> sortedList = new SortedList<>(FXCollections.observableList(flight));
        airlineTable.setItems(sortedList);
        sortedList.comparatorProperty().bind(airlineTable.comparatorProperty());
        airlineTable.refresh();
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




}