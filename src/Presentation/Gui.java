package Presentation;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Gui extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardsPanel;

    private JLabel userLabel; // Label to display the user's name
    private JButton loginButton, logoutButton;

    private Connection databaseConnection;

    private CreditCardPanel creditCardPanel;

    public Gui() {
        setTitle("Flight Reservation System");
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        // Login label setup
        JPanel topPanel = new JPanel(new BorderLayout());
        userLabel = new JLabel(" ");
        loginButton = new JButton("Login/Register");
        logoutButton = new JButton("Logout");
        logoutButton.setVisible(false); // Initially invisible
        
        topPanel.add(loginButton, BorderLayout.EAST);
        topPanel.add(userLabel, BorderLayout.WEST);
        topPanel.add(logoutButton, BorderLayout.CENTER);

        loginButton.addActionListener(e -> switchView("Login"));
        logoutButton.addActionListener(e -> logout());

        // Add the panels (views)
        cardsPanel.add(new HomePanel(this), "Home");
        cardsPanel.add(new SearchFlightPanel(this), "Search Flight");
        cardsPanel.add(new LoginPanel(this), "Login");
        cardsPanel.add(new CancelFlightPanel(this), "Cancel Flight");
        creditCardPanel = new CreditCardPanel(this);
        cardsPanel.add(creditCardPanel, "Payment");
        cardsPanel.add(new UserPanel(this), "UserPanel");
        cardsPanel.add(new AirlineAgentPanel(this), "AirlineAgentPanel");
        cardsPanel.add(new AdminPanel(this), "AdminPanel");

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(cardsPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        try {
            databaseConnection = establishDatabaseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void switchView(String viewName) {
        cardLayout.show(cardsPanel, viewName);
    }

    public void switchViewBasedOnAccessLevel() {
        int accessLevel = UserSession.getInstance().getAccessLevel();
        switch (accessLevel) {
            case 1:
                switchView("UserPanel");
                break;
            case 2:
                switchView("AgentPanel");
                break;
            case 3:
                switchView("AdminPanel");
                break;
            default:
                switchView("Home");
                break;
        }
    }

    public void setUserLabel() {
        UserSession session = UserSession.getInstance();
        if (session.isLoggedIn()) {
            userLabel.setText("Logged in as: " + session.getUserName());
            loginButton.setVisible(false);
            logoutButton.setVisible(true);
        } else {
            userLabel.setText(" ");
            loginButton.setVisible(true);
            logoutButton.setVisible(false);
        }
    }
    
    private void logout() {
        UserSession.getInstance().logout();
        setUserLabel();
        switchView("Home");
    }    

    private Connection establishDatabaseConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/your_database"; // Replace "your_database" with your database name
        String username = "your_username"; // Replace "your_username" with your username
        String password = "your_password"; // Replace "your_password" with your password
        return DriverManager.getConnection(url, username, password);
    }

    // Getter method to retrieve the database connection
    public Connection getDatabaseConnection() {
        return databaseConnection;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Gui frame = new Gui();
            frame.setVisible(true);
        });
    }

    public CreditCardPanel getCreditCardPanel() {
        return creditCardPanel;
    }
}

