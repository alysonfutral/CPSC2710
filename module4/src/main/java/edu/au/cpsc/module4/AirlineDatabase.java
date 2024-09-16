package edu.au.cpsc.module4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AirlineDatabase implements Serializable {
    private List<ScheduledFlight> flights;

    public AirlineDatabase() {
        flights = new ArrayList<>();
    }

    public List<ScheduledFlight> getScheduledFlights() {
        return flights;
    }

    public void addScheduledFlight(ScheduledFlight sf) {
        flights.add(sf);
    }

    public void removeScheduledFlight(ScheduledFlight sf) {
        flights.remove(sf);
    }

    public void updateScheduledFlight(ScheduledFlight sf) {

    }
}


