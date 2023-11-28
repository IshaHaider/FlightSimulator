package Presentation;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;
    
    public LoginPanel(Gui mainFrame) {
        setLayout(new GridLayout(5, 2));
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");
        statusLabel = new JLabel(" ");

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);
        add(statusLabel);
        add(backButton); // Add the back button to the panel
        add(new JLabel()); // Placeholder for grid alignment
        add(statusLabel);

        backButton.addActionListener(e -> {
            mainFrame.switchView("Home");
        });

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            // int retrievedAccessLevel = // get access level from database
            if (!username.isEmpty()) {
                UserSession.getInstance().setUserName(username);
                // UserSession.getInstance().setAccessLevel(retrievedAccessLevel);
                mainFrame.setUserLabel();
                mainFrame.switchView("Home");
                // mainFrame.switchViewBasedOnAccessLevel();
            } else {
                statusLabel.setText("Login failed.");
            }
        });

        registerButton.addActionListener(e -> {
            // Simulated login logic
            String username = usernameField.getText();
            // int retrievedAccessLevel = // get access level from database
            // Here, you would normally check the password, etc.
            if (!username.isEmpty()) { // Simple check for demonstration
                UserSession.getInstance().setUserName(username); // Set the user name in the UserSession
                // UserSession.getInstance().setAccessLevel(retrievedAccessLevel);
                mainFrame.setUserLabel(); // Update the user label in the main GUI
                mainFrame.switchView("Home");
                // mainFrame.switchViewBasedOnAccessLevel();
            } else {
                statusLabel.setText("Login failed.");
            }
        });
    }
}
