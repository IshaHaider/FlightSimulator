package src.Presentation;
import src.Domain.*;
import src.Controllers.*;

import javax.security.auth.login.LoginContext;
import javax.swing.*;
import java.awt.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class LoginPanel extends JPanel {
    // private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField ;
    private JTextField dofbField;
    private JTextField firstNameField ;
    private JTextField lastNameField ;
    private JTextField addressField ;
    private JTextField phoneField ;

    private JLabel statusLabel;
    private Gui mainFrame;
    private LoginController loginController;
    
    public LoginPanel(Gui mainFrame, LoginController loginController) {
        this.mainFrame = mainFrame;
        this.loginController = loginController;
        setLayout(new GridLayout(10, 2)); // Updated grid layout

        // Initializing fields
        // usernameField = new JTextField();
        emailField = new JTextField();
        dofbField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        addressField = new JTextField();
        phoneField = new JTextField();
        passwordField = new JPasswordField();

        // Buttons
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");

        statusLabel = new JLabel(" ");
        // add(new JLabel("Username:"));
        // add(usernameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Birth Date (dd mm year):"));
        add(dofbField);
        add(new JLabel("First Name:"));
        add(firstNameField);
        add(new JLabel("Last Name:"));
        add(lastNameField);
        add(new JLabel("Address (Street City PostalCode Country):"));
        add(addressField);
        add(new JLabel("Phone:"));
        add(phoneField);

        add(loginButton);
        add(registerButton);
        add(backButton);
        add(new JLabel()); // Placeholder
        add(statusLabel);
        add(new JLabel()); // Additional Placeholder

        backButton.addActionListener(e -> {
            mainFrame.switchView("Home");
        });

        loginButton.addActionListener(e -> {
            // String username = usernameField.getText();\
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            loginController.validLogin(email, password);
        });

        registerButton.addActionListener(e -> {
            // String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();
            String dateOfBirth = dofbField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String address = addressField.getText();
            String phoneNum = phoneField.getText();
            loginController.createLogin(password, email, dateOfBirth , firstName, lastName, address, phoneNum);
        });
    }
    
    public void setStatusLabel(String txt) {
        statusLabel.setText(txt);
    }

    public void clearFields() {
        // usernameField.setText("");
        passwordField.setText("");
        emailField.setText("");
        dofbField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        addressField.setText("");
        phoneField.setText("");
    }

    private void logoutPanel() {
        UserSession.getInstance().logout();
        mainFrame.setUserLabel();
        mainFrame.switchView("Home");
    }
}
