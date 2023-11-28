package src.Presentation;
import Controllers.*;
import Domain.*;

import javax.security.auth.login.LoginContext;
import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;
    private LoginController loginController;
    
    public LoginPanel(Gui mainFrame, LoginController loginController) {
        this.loginController = loginController;
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
        add(backButton); 
        add(new JLabel()); // Placeholder for grid alignment
        add(statusLabel);

        backButton.addActionListener(e -> {
            mainFrame.switchView("Home");
        });

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            loginController.validLogin(username, password);
        });

        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            loginController.createLogin(username, password);
        });
    }
    
    public void setStatusLabel(String txt) {
        statusLabel.setText(txt);
    }

    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }
}
