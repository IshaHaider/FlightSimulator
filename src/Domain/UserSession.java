package src.Domain;

public class UserSession {
    private static UserSession instance;
    private String email;
    private int accessLevel; // 1 for user, 2 for airline agent, 3 for admin

    private UserSession() {
        email = ""; // Initially, no user is logged in
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public String getUserName() {
        return email;
    }

    public void setUserName(String email) {
        this.email = email;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
    
    public boolean isLoggedIn() {
        return !email.isEmpty();
    }

    public void logout() {
        email = "";
    }
}