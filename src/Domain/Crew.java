package src.Domain;

import java.util.ArrayList;
import java.time.LocalDate;

public class Crew extends User{
    private String password;  

    public Crew() {
        super();
        this.accessLevel = 3;
        this.password = ""; 
    }

    // USE THIS WHEN CREATING AN OBJECT FOR SQL DATABASE (because the userID is auto-increment)
    public Crew (Name name, Address address, String email, String password, LocalDate birthDate, String phoneNumber){
        super(name, address, email, birthDate, phoneNumber);
        this.accessLevel = 3;
        this.password = password; 
    }

    // USE THIS FOR LOCAL STORAGE (to manually add userID)
    public Crew (int userID, Name name, Address address, String email, String password, LocalDate birthDate, String phoneNumber){
        super(userID, name, address, email, birthDate, phoneNumber);
        this.accessLevel = 3;
        this.password = password; 
    }

    /* SETTERS AND GETTERS */
    public String getPassword() {return password;}
    public void setPassword(final String password) {this.password = password;}
}