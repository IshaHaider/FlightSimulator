package src.Controllers;
import Domain.*;
import Presentation.*;

public class LoginController {
    private DBController db;
    private UserSession userInstance;
    private LoginPanel loginPanel;

    public LoginController (DBController db) {
        this.db = db;
        this.userInstance = UserSession.getInstance();
        
        ResultSet allUsers = db.selectTable("allusers");
    }

    public boolean validLogin(String username, String password) {
        // For list if the given user is not in list retrun false otherwise return true

        if (!username.isEmpty() && !password.isEmpty() ) { // should be checking databse here
            userInstance.setUserName(username);
            // userInstance.setAccessLevel(retrievedAccessLevel); // Set access level after checking with the database
            return true;
        }
        return false;
    }

    public void setLoginPanel(LoginPanel loginPanel) {
        this.loginPanel = loginPanel;
    }
}