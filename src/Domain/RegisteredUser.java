package src.Domain;

import java.util.ArrayList;

public class RegisteredUser implements User, Observer {
    ArrayList<Flight> registeredFlights;
    float balance;
    void receivePromotion() {}
    void reserveFlight(Flight flight, Seat seatInformation) {}
    void viewReservations() {}
    public void update() {}
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