package src.Controllers;

import src.Domain.*;
import src.Presentation.*;

import javax.security.auth.login.LoginContext;
import javax.swing.*;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Gui extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardsPanel;

    private HomePanel homePanel;
    private SearchFlightPanel searchFlightPanel;
    private LoginPanel loginPanel;
    private CancelFlightPanel cancelFlightPanel;
    private CreditCardPanel creditCardPanel;
    private UserPanel userPanel;
    private AirlineAgentPanel airlineAgentPanel;
    private AdminPanel adminPanel;

    private JLabel userLabel;
    private JButton loginButton, logoutButton;

    public Gui(LoginController loginController, FlightController flightController, SeatController seatController, TicketController ticketController, DBController dbController) {
        
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
        this.homePanel = new HomePanel(this);
        this.searchFlightPanel = new SearchFlightPanel(this, seatController);
        this.loginPanel = new LoginPanel(this, loginController);
        this.cancelFlightPanel = new CancelFlightPanel(this, seatController);
        this.creditCardPanel = new CreditCardPanel(this, seatController);
        this.userPanel = new UserPanel(this);
        this.airlineAgentPanel = new AirlineAgentPanel(this, flightController);
        this.adminPanel = new AdminPanel(this, flightController);

        cardsPanel.add(homePanel, "Home");
        cardsPanel.add(searchFlightPanel, "Search Flight");
        cardsPanel.add(loginPanel, "Login");
        cardsPanel.add(cancelFlightPanel, "Cancel Flight");
        cardsPanel.add(creditCardPanel, "Payment");
        cardsPanel.add(userPanel, "UserPanel");
        cardsPanel.add(airlineAgentPanel, "AirlineAgentPanel");
        cardsPanel.add(adminPanel, "AdminPanel");

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(cardsPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
    }

    public void switchView(String viewName) {
        cardLayout.show(cardsPanel, viewName);
    }

    public void switchViewBasedOnAccessLevel() {
        int accessLevel = UserSession.getInstance().getAccessLevel();
        if (accessLevel == 2) {
            cardLayout.switchView("UserPanel");
        }
        else if (accessLevel == 3) {
            cardLayout.switchView("AirlineAgentPanel");
        }
        else if (accessLevel == 4) {
            cardLayout.switchView("AdminPanel");
        }
        else {
            cardLayout.switchView("Home");
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

    public HomePanel getHomePanel() {
        return homePanel;
    }
    public SearchFlightPanel getSearchFlightPanel() {
        return searchFlightPanel;
    }
    public LoginPanel getLoginPanel() {
        return loginPanel;
    }
    public CancelFlightPanel getCancelFlightPanel() {
        return cancelFlightPanel;
    }
    public CreditCardPanel getCreditCardPanel() {
        return creditCardPanel;
    }
    public UserPanel getuserPanel() {
        return userPanel;
    }
    public AirlineAgentPanel getAirlineAgentPanel() {
        return airlineAgentPanel;
    }
    public AdminPanel  getAdminPanel() {
        return adminPanel;
    }
}

