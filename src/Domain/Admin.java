package src.Domain;

import java.util.ArrayList;

public class Admin extends User {

    public Admin(){
        this.accessLevel = "4";
        this.promotionID = 0;
        this.name = new Name();     
        this.address = new Address();      
        this.email = "";       
        this.password = "";   
        this.birthDate = new Date(); 
        this.phoneNumber = "";  
        this.balance = 1000000000.0;
    }

    public Admin(Name name, Date birthDate, Address address, String phoneNumber, String email, String password, int accessLevel) {
        this.accessLevel = accessLevel;
        this.name = name;     
        this.address = address;      
        this.email = email;       
        this.password = password;   
        this.birthDate = birthDate; 
        this.phoneNumber = phoneNumber;
    }

    public int getUserID() {return userID;}
    public int getAccessLevel() {return accessLevel;}
    public Name getName() {return name;}
    public Address getAddress() {return address;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public Date getBirthDate() {return birthDate;}
    public String getPhoneNumber() {return phoneNumber;}

    public void setUserID(final int userID) {this.userID = userID;}
    public void setAccessLevel(final int accessLevel) {this.accessLevel = accessLevel;}
    public void setName(final Name name) {this.name = name;}
    public void setAddress(final Address address) {this.address = address;}
    public void setEmail(final String email) {this.email = email;}
    public void setPassword(final String password) {this.password = password;}
    public void setBirthDate(final Date birthDate) {this.birthDate = birthDate;}
    public void setPhoneNumber(final String phoneNumber) {this.phoneNumber = phoneNumber;}

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
