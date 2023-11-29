package src.Domain;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import java.time.LocalDate;

public class GuestUser extends User{
    
    public GuestUser() {
        this.accessLevel = 1;
        this.name = new Name();     
        this.address = new Address();      
        this.email = ""; 
        this.phoneNumber = "";  
    }

    public GuestUser(Name name, Address address, String email, LocalDate birthDate, String phoneNumber) {
        this.accessLevel = 1;
        this.name = name;     
        this.address = address;      
        this.email = email;       
        this.birthDate = birthDate; 
        this.phoneNumber = phoneNumber;
    }

    @Override
    public ArrayList<Flight> getFlights(final String destination) {return new ArrayList<Flight>(); };
    @Override
    public void manageReservation(final Flight flight) {};  

    // void reserveFlight(final Flight flight, final Seat seat) {
    //     if(payBill(flight, this)) {
    //         Ticket ticket = new Ticket(flight.getFlightNumber(), flight.getAssignedPlane().getAirPlaneID() ,this.userID);
    //         registeredFlights.add(ticket);
    //         ticket.addTicket(ticket);
    //     }
    // }

    // @Override
    // public ArrayList<Flight> getFlights(final String destination) {
    //     ArrayList<Flight> flights = new ArrayList<>();
    //     for (Flight flight : registeredFlights) {
    //         if (flight.getArrivalLocation().equals(destination)) {
    //             flights.add(flight);
    //         }
    //     }
    //     return flights;
    // }

    // @Override
    // // this function will only be able to modify the seat of the user
    // public void manageReservation(Flight flight, Seat seat){
    //     if(flight.getSeat(this.userID).getavailability()){
    //         assignSeat(null, tbis.flight.getSeat() , flight); // unassign the seat
    //         flight.getSeat().setavailability(true); // change the seat to available
    //         assignSeat(this.userID, seat, flight); // assign the seat

    //         // update the arraylist of registered flights
    //         for(int i = 0; i < registeredFlights.size(); i++){
    //             if(registeredFlights.get(i).getFlightNumber() == flight.getFlightNumber()){
    //                 registeredFlights.set(i, flight);
    //             }
    //         }
    //     }
    // }

    // public void update(Observable o, Object arg){
    //     if(o instanceof Flight){
    //         Flight updatedFlight = (Flight) o;
    //     }
    // }
}
