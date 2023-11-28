package src.Domain;

import java.util.ArrayList;

public class Admin extends User {

    public Admin(int userID, Name name, Date dateOfBirth, Address address, int phoneNumber, Email email, String password, int accessLevel) {
        this.userID = userID; 
        this.name = name;     
        this.dateOfBirth = dateOfBirth; 
        this.address = address;       
        this.phoneNumber = phoneNumber; 
        this.email = email;            
        this.password = password;     
        this.accessLevel = accessLevel; 
    }

    @Override
    public void viewFlightInformation(final Flight flight) {
    }

    @Override
    public void manageReservation(Flight flight, Seat seat){
        if(flight.getSeat(Admin.getUserID()).getavailability()){
            assignSeat(null, flight.getSeat(this.userId) , flight); // unassign the seat
            flight.getSeat().setavailability(true); // change the seat to available
            assignSeat(Admin.getUserID(), seat, flight); // assign the seat

            // update the arraylist of registered flights
            for(int i = 0; i < registeredFlights.size(); i++){
                if(registeredFlights.get(i).getFlightNumber() == flight.getFlightNumber()){
                    registeredFlights.set(i, flight);
                }
            }
        }
    }

    void viewFlightPassengers(Flight flight) {
    }

    void manageAircraft() {
    }

    void manageFlight(Flight flight) {
    }

    void assignCrew(Crew crew) {
    }
}
