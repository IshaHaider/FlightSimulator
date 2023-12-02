package src.Domain;

import java.sql.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import java.time.LocalDate;
import src.Controllers.DBController;

public class RegisteredUser extends User{
    private int promotionID;
    private String password;  
    private float balance; 

    public RegisteredUser() {
        super();
        this.accessLevel = 2;
        this.promotionID = 0;
        this.password = "";
        this.balance = 0.0f;
    }

    // USE THIS WHEN CREATING AN OBJECT FOR SQL DATABASE (because the userID is auto-increment)
    public RegisteredUser (int promotionID, Name name, Address address, String email, String password, LocalDate birthDate, String phoneNumber, float balance)  {
        super(name, address, email, birthDate, phoneNumber);
        this.accessLevel = 2;
        this.promotionID = promotionID;
        this.password = password;
        this.balance = balance;
    }

    // USE THIS FOR LOCAL STORAGE (to manually add userID)
    public RegisteredUser (int userID, int promotionID, Name name, Address address, String email, String password, LocalDate birthDate, String phoneNumber, float balance)  {
        super(userID, name, address, email, birthDate, phoneNumber);
        this.accessLevel = 2;
        this.promotionID = promotionID;
        this.password = password;
        this.balance = balance;
    }
    
    public final int getPromotionID() {return promotionID;}
    public final String getPassword() {return password;}
    public final float getBalance() {return balance;}

    public void setPromotionID(final int promotionID) {this.promotionID = promotionID;}
    public void setPassword(final String password) {this.password = password;}
    public void setBalance(final float balance) {this.balance = balance;}
}