package src.Domain;

import java.util.ArrayList;

public class Admin extends User {
    private String password;  

    public Admin(){
        this.accessLevel = 4;
        this.name = new Name();     
        this.address = new Address();      
        this.email = "";  
        this.password = "";  
        this.birthDate = new DDate(); 
        this.phoneNumber = ""; 
    }

    public Admin(Name name, Address address, String email, String password, DDate birthDate, String phoneNumber){
        this.accessLevel = 4;
        this.name = name;     
        this.address = address;      
        this.email = email;    
        this.password = password; 
        this.birthDate = birthDate; 
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {return password;}
    public void setPassword(final String password) {this.password = password;}
    
    @Override
    public ArrayList<Flight> getFlights(final String destination) {return new ArrayList<Flight>(); };
    @Override
    public void manageReservation(final Flight flight) {};  

    
    // public ArrayList<User> getFlightPassengers(Flight flight) {
    //     ArrayList<User> passengers = flight.getPassengerList();
    //     return passengers;
    // }

    // public ArrayList<RegisteredUser> getRegisteredUsers() {
        
    // }

    // public void addAircraft(AirPlane airplane) {}

    // public void addFlight(Flight flight) {}

    // public void removeAircraft(AirPlane airplane) {}

    // public void removeFlight(Flight flight) {}

    // public void modifyAircraft(AirPlane airplane) {}

    // public void modifyFlight(Flight flight) {}

    // public void modifyUser(AirPlane airplane) {}

    // public void modifyTicket(Flight flight) {}

    // public void assignCrew(Crew crew) {}
}
