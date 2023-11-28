package src.Domain;

import java.util.ArrayList;

public class Admin implements User {
    void viewFlightPassengers(Flight flight) {}
    void manageAircraft() {}
    void manageFlight(Flight flight) {}
    void assignCrew(Crew crew) {}
    @Override
    public void viewFlight() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewFlight'");
    }
    @Override
    public void viewFlightInformation(Flight flight) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewFlightInformation'");
    }
    @Override
    public void manageReservation(Flight flight) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'manageReservation'");
    }
}
