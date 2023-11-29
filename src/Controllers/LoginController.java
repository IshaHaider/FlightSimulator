package src.Controllers;

import src.Domain.*;
import src.Presentation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;

public class LoginController {
    private Gui mainFrame; 
    private UserSession userInstance;
    private LoginPanel loginPanel;
    private DBController db = DBController.getOnlyInstance();

    private ArrayList<GuestUser> guestUsers = new ArrayList<GuestUser>();
    private ArrayList<RegisteredUser> registeredUsers = new ArrayList<RegisteredUser>();
    private ArrayList<Crew> crewUsers = new ArrayList<Crew>();
    private ArrayList<Admin> adminUsers = new ArrayList<Admin>();

    public LoginController () {
        this.userInstance = UserSession.getInstance();
        browseUsers();
    }

    public LoginController (Gui mainFrame) {
        this.mainFrame = mainFrame;
        this.userInstance = UserSession.getInstance();
        browseUsers();
    }
    
    private void browseUsers(){
        try {
            ResultSet allUsers = db.selectTable("allusers");

            while (allUsers.next()) {
                int userID = allUsers.getInt("userID");
                int accessLevel = allUsers.getInt("accessLevel");
                int promotionID = allUsers.getInt("promotionID");
                Name name = new Name(allUsers.getString("firstName"), allUsers.getString("lastName"));
                Address address = new Address(allUsers.getString("address"));
                String email = allUsers.getString("email");
                String password = allUsers.getString("password");
                LocalDate birthDate = allUsers.getDate("birthDate").toLocalDate();
                String phoneNumber = allUsers.getString("phoneNumber");
                float balance = allUsers.getFloat("balance");

                if (accessLevel == 1) {
                    GuestUser guestUser = new GuestUser(userID, name, address, email, birthDate, phoneNumber);
                    guestUsers.add(guestUser);
                }
                else if (accessLevel == 2) {
                    RegisteredUser regUser = new RegisteredUser(userID, promotionID, name, address, email, password, birthDate, phoneNumber, balance);
                    registeredUsers.add(regUser);
                }
                else if (accessLevel == 3) {
                    Crew crew = new Crew(userID, name, address, email, password, birthDate, phoneNumber);
                    crewUsers.add(crew);
                }
                else if (accessLevel == 4) {
                    Admin admin = new Admin(userID, name, address, email, password, birthDate, phoneNumber);
                    adminUsers.add(admin);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void validLogin(final String email, final String password) {
        if (!email.isEmpty() && !password.isEmpty() ) { // should be checking database here
            try {
            ResultSet result = db.selectTableFromTwoAttributes("ALLUSERS", "email", email, "password", password);
            int retrievedAccessLevel = result.getInt("accessLevel");
            userInstance.setUserName(email);
            userInstance.setAccessLevel(retrievedAccessLevel);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        loginPanel.clearFields();
        mainFrame.switchViewBasedOnAccessLevel();
    }

    public void createLogin(final String DOFB, final String password, final String email, final String fName, final String lName, final String address, final String phoneNum) {
        if (email.isEmpty() || password.isEmpty() || DOFB.isEmpty() || fName.isEmpty() || lName.isEmpty() || address.isEmpty() || phoneNum.isEmpty()) {
            loginPanel.setStatusLabel("Please fill in all fields.");
        }
        for (RegisteredUser user : registeredUsers) {
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
                LocalDate tmpDate = LocalDate.of(year, month, day);
                
                Address tmpAddy = new Address(address);
                
                RegisteredUser tmpRegUser = new RegisteredUser(2, tmpName, tmpAddy, email, password, tmpDate, phoneNum, 0.0f);
                db.insertRegisteredUser(tmpRegUser);
                mainFrame.switchView("UserPanel");
                loginPanel.setStatusLabel("Registration successful.");
                loginPanel.clearFields();
                return;
            }
        }
    }


    
    /* SETTERS AND GETTERS */
    public void setLoginPanel(LoginPanel panel) { this.loginPanel = panel;}
    public void setGuestUsers(ArrayList<GuestUser> gu) { this.guestUsers = gu; }
    public void setRegisteredUsers(ArrayList<RegisteredUser> ru) { this.registeredUsers = ru; }
    public void setCrewUsers(ArrayList<Crew> cu) { this.crewUsers = cu; }
    public void setAdminUsers(ArrayList<Admin> au) { this.adminUsers = au; }
    
    
    public LoginPanel getLoginPanel() { return this.loginPanel;}
    public ArrayList<GuestUser> getGuestUsers() { return this.guestUsers; }
    public ArrayList<RegisteredUser> getRegisteredUsers() { return this.registeredUsers; }
    public ArrayList<Crew> getCrewUsers() { return this.crewUsers; }
    public ArrayList<Admin> getAdminUsers() { return this.adminUsers; }

}