package src.Domain;

import java.util.ArrayList;
import java.util.Observer;

public class GuestUser implements User, Observer {
    private ArrayList<Flight> registeredFlights;
    private float balance;

    public GuestUser() {
        this.registeredFlights = new ArrayList<>();
        this.balance = 0.0;
    }

    public GuestUser(ArrayList<Flight> registeredFlights, float balance) {
        this.registeredFlights = new ArrayList<>(registeredFlights);
        this.balance = balance;
    }

    public float getBalance() {
        return balance;
    }

    public ArrayList<Flight> getRegisteredFlights() {
        return registeredFlights;
    }

    public void setBalance(final float balance) {
        this.balance = balance;
    }

    public void setRegisteredFlights(final ArrayList<Flight> registeredFlights) {
        this.registeredFlights = registeredFlights;
    }

    void reserveFlight(final Flight flight, final Seat seat) {
        assignSeat(GuestUser.userID, seat);
        registeredFlights.add(flight);
    }

    void viewReservations() {
        // for (Flight flight : registeredFlights) {
        //     System.out.println(flight.getFlightNumber(), flight.getSeat(GuestUser.userID));
        // }

        // TO Be Implemented
    }

    @Override
    public ArrayList<Flight> viewFlights(final String destination){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewFlight'");
    }
    @Override
    public void viewFlightInformation(Flight flight) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewFlightInformation'");
    }
    @Override
    // this function will only be able to modify the seat of the user
    public void manageReservation(Flight flight, Seat seat){
        if(flight.getSeat(GuestUser.userID).getavailability()){
            assignSeat(null, GuestUser.flight.getSeat() , flight); // unassign the seat
            flight.getSeat().setavailability(true); // change the seat to available
            assignSeat(GuestUser.userID, seat, flight); // assign the seat

            // update the arraylist of registered flights
            for(int i = 0; i < registeredFlights.size(); i++){
                if(registeredFlights.get(i).getFlightNumber() == flight.getFlightNumber()){
                    registeredFlights.set(i, flight);
                }
            }
        }
    }

    public void update(Observable o, Object arg){
        if(o instanceof Flight){
            Flight updatedFlight = (Flight) o;
        }
    }
}
