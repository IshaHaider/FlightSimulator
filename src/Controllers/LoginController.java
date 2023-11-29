package src.Controllers;

import src.Domain.*;
import src.Presentation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;

public class LoginController {
    private Gui mainFrame; 
    private DBController db;
    private UserSession userInstance;
    private LoginPanel loginPanel;

    public LoginController (Gui mainFrame, DBController db) {
        this.mainFrame = mainFrame;
        this.db = db;
        this.userInstance = UserSession.getInstance();

        ArrayList<GuestUser> guestUsers = new ArrayList<GuestUser>();
        ArrayList<RegisteredUser> registeredUsers = new ArrayList<RegisteredUser>();
        ArrayList<Crew> crewUsers = new ArrayList<Crew>();
        ArrayList<Admin> adminUsers = new ArrayList<Admin>();
        
        try {
            ResultSet allUsers = db.selectTable("allusers");

            while (allUsers.next()) {
                int userID = allUsers.getInt("userID");
                int accessLevel = allUsers.getInt("accessLevel");
                int promotionID = allUsers.getInt("promotionID");
                String firstName = allUsers.getString("firstName");
                String lastName = allUsers.getString("lastName");
                String address = allUsers.getString("address");
                String email = allUsers.getString("email");
                String password = allUsers.getString("password");
                LocalDate birthDate = allUsers.getDate("birthDate").toLocalDate();
                String phoneNumber = allUsers.getString("phoneNumber");
                float balance = allUsers.getFloat("balance");

                if (accessLevel == 1) {
                    GuestUser guestUser = new GuestUser(userID, accessLevel, promotionID, firstName, lastName, address, email, password, birthDate, phoneNumber, balance);
                    guestUsers.addUser(guestUser);
                }
                else if (accessLevel == 2) {
                    RegisteredUser regUser = new RegisteredUser(userID, accessLevel, promotionID, firstName, lasttName, address, email, password, birthDate, phoneNumber, balance);
                    registeredUser.addUser(admin);
                }
                else if (accessLevel == 3) {
                    Crew crew = new Crew(userID, accessLevel, promotionID, firstName, lasttName, address, email, password, birthDate, phoneNumber, balance);
                    crewUsers.addUser(crew);
                }
                else if (accessLevel == 4) {
                    Admin admin = new Admin(userID, accessLevel, promotionID, firstName, lasttName, address, email, password, birthDate, phoneNumber, balance);
                    adminUsers.addUser(admin);
                }

                currentFlights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void validLogin(final String email, final String password) {
        if (!email.isEmpty() && !password.isEmpty() ) { // should be checking database here
            ResultSet result = db.selectTableFromTwoAttributes("ALLUSERS", "email", email, "password", password);
            int retrievedAccessLevel = result.getInt("accessLevel");
            userInstance.setUserName(email);
            userInstance.setAccessLevel(retrievedAccessLevel);
        }
        loginPanel.clearFields();
        mainFrame.switchViewBasedOnAccessLevel();
    }

    public void createLogin(final String DOFB, final String password, final String email, final String fName, final String lName, final String address, final String phoneNum) {
        if (email.isEmpty() || password.isEmpty() || DOFB.isEmpty() || fName.isEmpty() || lName.isEmpty() || address.isEmpty() || phoneNum.isEmpty()) {
            loginPanel.setStatusLabel("Please fill in all fields.");
        }
        for (RegisteredUsers user : registeredUsers) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                loginPanel.setStatusLabel("User already exists.");
                return;
            }
            else {                
                Name tmpName = new Name(fName, lName);
                
                String[] components = DOFB.split(" "); // Split the string by space
                int day = Integer.parseInt(components[0]);
                int month = Integer.parseInt(components[1]);
                int year = Integer.parseInt(components[2]);
                LocalDate tmpDate;

                String[] components2 = address.split(" "); // Split the string by space
                String street = components2[0];
                String city = components2[1];
                String postalCode = components2[2];
                String Country = components2[3];
                Address tmpAddy = new Address(street, city, postalCode, Country);
                
                RegisteredUser tmpRegUser = new RegisteredUser(tmpName, tmpDate, tmpAddy, phoneNum, email, password, 2, null);
                db.insertRegisteredUser(tmpRegUser);
                mainFrame.switchView("UserPanel");
                loginPanel.setStatusLabel("Registration successful.");
                loginPanel.clearFields();
                return;
            }
        }
    }

    public void setLoginPanel(LoginPanel loginPanel) {
        this.loginPanel = loginPanel;
    }
}