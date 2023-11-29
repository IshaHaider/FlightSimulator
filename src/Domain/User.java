package src.Domain;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class User {
    protected int userID;
    protected int accessLevel;
    protected Name name;
    protected Address address;
    protected String email;
    protected LocalDate birthDate;
    protected String phoneNumber;
     
    public int getUserID() {return userID;}
    public int getAccessLevel() {return accessLevel;}
    public Name getName() {return name;}
    public Address getAddress() {return address;}
    public String getEmail() {return email;}
    public LocalDate getBirthDate() {return birthDate;}
    public String getPhoneNumber() {return phoneNumber;}
    
    public void setUserID(final int userID) {this.userID = userID;}
    public void setAccessLevel(final int accessLevel) {this.accessLevel = accessLevel;}
    public void setName(final Name name) {this.name = name;}
    public void setAddress(final Address address) {this.address = address;}
    public void setEmail(final String email) {this.email = email;}
    public void setBirthDate(final LocalDate birthDate) {this.birthDate = birthDate;}
    public void setPhoneNumber(final String phoneNumber) {this.phoneNumber = phoneNumber;}

    // public abstract ArrayList<Flight> getFlights(final String destination);
    // public abstract void manageReservation(final Flight flight);   
}