package src.Domain;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import java.time.LocalDate;

public class GuestUser extends User{
    
    public GuestUser() {
        super();
        this.accessLevel = 1;
    }

    // USE THIS WHEN CREATING AN OBJECT FOR SQL DATABASE (because the userID is auto-increment)
    public GuestUser(Name name, Address address, String email, LocalDate birthDate, String phoneNumber) {
        super(name, address, email, birthDate, phoneNumber);
        this.accessLevel = 1; 
    }

    // USE THIS FOR LOCAL STORAGE (to manually add userID)
    public GuestUser(int userID, Name name, Address address, String email, LocalDate birthDate, String phoneNumber) {
        super(userID, name, address, email, birthDate, phoneNumber);
        this.accessLevel = 1;
    }
}
