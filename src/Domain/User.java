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

    public User(){
        this.userID = 0;
        this.name = new Name();     
        this.address = new Address();      
        this.email = ""; 
        this.phoneNumber = ""; 
    }

    public User(Name name, Address address, String email, LocalDate birthDate, String phoneNumber){
        this.userID = 0;
        this.name = name;     
        this.address = address;      
        this.email = email;       
        this.birthDate = birthDate; 
        this.phoneNumber = phoneNumber;
    }

    public User(int userID, Name name, Address address, String email, LocalDate birthDate, String phoneNumber){
        this.userID = userID;
        this.name = name;     
        this.address = address;      
        this.email = email;       
        this.birthDate = birthDate; 
        this.phoneNumber = phoneNumber;
    }
     
    /* SETTERS AND GETTERS */
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
}