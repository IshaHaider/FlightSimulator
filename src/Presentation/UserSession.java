package Presentation;

public class UserSession {
    private static UserSession instance;
    private String userName;
    private int accessLevel; // 1 for user, 2 for airline agent, 3 for admin

    private UserSession() {
        userName = ""; // Initially, no user is logged in
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
    
    public boolean isLoggedIn() {
        return !userName.isEmpty();
    }

    public void logout() {
        userName = "";
    }
}
