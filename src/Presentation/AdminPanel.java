package src.Presentation;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {
    public AdminPanel(Gui mainFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Flight Reservation System", SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.PAGE_START;
        add(welcomeLabel, gbc);

        // Configure the position for the buttons
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Search Flights Button
        JButton searchFlightButton = new JButton("Search Flights");
        searchFlightButton.addActionListener(e -> mainFrame.switchView("Payment"));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(searchFlightButton, gbc);

        // Cancel Flights Button
        JButton cancelFlightButton = new JButton("Cancel Flights");
        cancelFlightButton.addActionListener(e -> mainFrame.switchView("Cancel Flight"));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(cancelFlightButton, gbc);

        // Admin Functionalities
        addButton("Browse Flights", 0, 2, gbc);
        addButton("Browse Crew", 1, 2, gbc);
        addButton("Browse Aircrafts", 0, 3, gbc);
        addButton("Manage Crew", 1, 3, gbc);
        addButton("Manage Aircraft", 0, 4, gbc);
        addButton("Manage Destinations", 1, 4, gbc);
        addButton("Manage Flights", 0, 5, gbc);
        addButton("Print User List", 1, 5, gbc);
    }

    private void addButton(String text, int gridx, int gridy, GridBagConstraints gbc) {
        JButton button = new JButton(text);
        button.addActionListener(e -> {
            // Placeholder for functionality
        });
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        add(button, gbc);
    }
}
