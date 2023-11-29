package src.Domain;

import java.util.ArrayList;
 
public class Crew {
    ArrayList<Flight> assignedFlight;

    public Crew() {
        this.accessLevel = "3";
        this.promotionID = 0;
        this.name = new Name();     
        this.address = new Address();      
        this.email = "";       
        this.password = "";   
        this.birthDate = new Date(); 
        this.phoneNumber = "";  
        this.balance = 1000000000.0;
        this.assignedFlights = new ArrayList<>();
    }

    public Crew(Name name, Date birthDate, Address address, String phoneNumber, String email, String password, int accessLevel, ArrayList<Flight> assignedFlight) {
        this.accessLevel = accessLevel;
        this.name = name;     
        this.address = address;      
        this.email = email;       
        this.password = password;   
        this.birthDate = birthDate; 
        this.phoneNumber = phoneNumber;
        this.assignedFlights = assignedFlight;
    }

    public int getUserID() {return userID;}
    public int getAccessLevel() {return accessLevel;}
    public Name getName() {return name;}
    public Address getAddress() {return address;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public Date getBirthDate() {return birthDate;}
    public String getPhoneNumber() {return phoneNumber;}
    public ArrayList<Flight> getAssignedFlights() {return assignedFlights;}

    public void setUserID(final int userID) {this.userID = userID;}
    public void setAccessLevel(final int accessLevel) {this.accessLevel = accessLevel;}
    public void setName(final Name name) {this.name = name;}
    public void setAddress(final Address address) {this.address = address;}
    public void setEmail(final String email) {this.email = email;}
    public void setPassword(final String password) {this.password = password;}
    public void setBirthDate(final Date birthDate) {this.birthDate = birthDate;}
    public void setPhoneNumber(final String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setAssignedFlights(final ArrayList<Flight> assignedFlights) {this.assignedFlights = assignedFlights;}
}