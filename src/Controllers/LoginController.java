package src.Controllers;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Domain.*;
import Presentation.*;
import src.Controllers.DBController;
import src.Domain.GuestUser;
import src.Domain.User;
import src.Presentation.UserSession;

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
                int promotionID = allUsers.getString("promotionID");
                String firstName = allUsers.getString("firstName");
                String lasttName = allUsers.getString("lastName");
                String address = allUsers.getStrign("address");
                String email = allUsers.getStrign("email");
                Status password = allUsers.getStrign("password");
                Date birthDate = allUsers.getStrign("birthDate");
                String phoneNumber = allUsers.getStrign("phoneNumber");
                float balance = allUsers.getStrign("balance");

                if (accessLevel == 1) {
                    GuestUser guestUser = new GuestUser(userID, accessLevel, promotionID, firstName, lasttName, address, email, password, birthDate, phoneNumber, balance);
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
    
    public void validLogin(String username, String password) {
        if (!username.isEmpty() && !password.isEmpty() ) { // should be checking databse here

            ResultSet result = db.selectUSER(email, password);
            int retrievedAccessLevel = result.getInt("accessLevel");
            userInstance.setUserName(username);
            userInstance.setAccessLevel(retrievedAccessLevel);
            
        }
        
        if (userInstance.getAccessLevel() != 0) {
            mainFrame.setUserLabel(); // Update the user label in the main GUI
            if (userInstance.getAccessLevel() == 1) {
                mainFrame.switchView("Home");
            }
            else if (userInstance.getAccessLevel() == 2) {
                mainFrame.switchView("UserPanel");
            }
            else if (userInstance.getAccessLevel() == 3) {
                mainFrame.switchView("AirlineAgentPanel");
            }
            else if (userInstance.getAccessLevel() == 4) {
                mainFrame.switchView("AdminPanel");
            }
        } else {
            loginPanel.setStatusLabel("Login failed.");
        }
    }

    public void createLogin(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            loginPanel.setStatusLabel("Please fill in all fields.");
        }
        for (RegisteredUsers user : registeredUsers) {
            if (user.getUserName().equals(username)) {
                loginPanel.setStatusLabel("Username already exists.");
                return;
            }
            else {
                // NEED TO CREATE NEW USER IN DATABASE AND OBJECT ADD TO ARRAYLIST AND DB
                // db.insertUser(username, password);
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