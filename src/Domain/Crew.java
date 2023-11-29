package src.Domain;

import java.util.ArrayList;
import java.time.LocalDate;

public class Crew extends User{
    private String password;  

    public Crew() {
        this.accessLevel = 3;
        this.name = new Name();     
        this.address = new Address();      
        this.email = "";  
        this.password = ""; 
        this.phoneNumber = "";
    }

    // USE THIS WHEN CREATING AN OBJECT FOR SQL DATABASE (because the userID is auto-increment)
    public Crew (Name name, Address address, String email, String password, LocalDate birthDate, String phoneNumber){
        this.accessLevel = 3;
        this.name = name;     
        this.address = address;      
        this.email = email;    
        this.password = password; 
        this.birthDate = birthDate; 
        this.phoneNumber = phoneNumber;
    }

    // USE THIS FOR LOCAL STORAGE (to manually add userID)
    public Crew (int userID, Name name, Address address, String email, String password, LocalDate birthDate, String phoneNumber){
        this.userID = userID;
        this.accessLevel = 3;
        this.name = name;     
        this.address = address;      
        this.email = email;    
        this.password = password; 
        this.birthDate = birthDate; 
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {return password;}
    public void setPassword(final String password) {this.password = password;}
}