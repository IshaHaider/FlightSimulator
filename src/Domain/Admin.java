package src.Domain;

import java.util.ArrayList;
import java.time.LocalDate;


public class Admin extends User {
    private String password;  

    public Admin(){
        super();
        this.accessLevel = 4;
        this.password = "";
    }

    // USE THIS WHEN CREATING AN OBJECT FOR SQL DATABASE (because the userID is auto-increment)
    public Admin(Name name, Address address, String email, String password, LocalDate birthDate, String phoneNumber){
        super(name, address, email, birthDate, phoneNumber);
        this.accessLevel = 4;
        this.password = password; 
    }

    // USE THIS FOR LOCAL STORAGE (to manually add userID)
    public Admin(int userID, Name name, Address address, String email, String password, LocalDate birthDate, String phoneNumber){
        super(userID, name, address, email, birthDate, phoneNumber);
        this.accessLevel = 4;
        this.password = password; 
    }

    public String getPassword() {return password;}
    public void setPassword(final String password) {this.password = password;}
}
