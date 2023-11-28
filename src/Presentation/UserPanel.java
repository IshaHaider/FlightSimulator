package src.Presentation;

import javax.swing.*;

import java.awt.*;
import java.time.LocalDate; // Import LocalDate

public class UserPanel extends JPanel {
    public UserPanel(Gui mainFrame) {
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

        // Check if today is the first day of the month
        LocalDate today = LocalDate.now();
        if (today.getDayOfMonth() == 1) {
            JLabel promotionsLabel = new JLabel("Here's your monthly promotion news! Discounts at airport lounges and 1 free companion ticket once a year.", SwingConstants.CENTER);
            gbc.gridy = 1; // Adjust grid position for the label
            gbc.insets = new Insets(20, 10, 10, 10);
            add(promotionsLabel, gbc);
        }

        // Configure the position for the buttons
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Search Flights Button
        JButton searchFlightButton = new JButton("Search Flights");
        searchFlightButton.addActionListener(e -> mainFrame.switchView("SearchFlightPanel"));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(searchFlightButton, gbc);

        // Cancel Flights Button
        JButton cancelFlightButton = new JButton("Cancel Flights");
        cancelFlightButton.addActionListener(e -> mainFrame.switchView("Cancel Flight"));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(cancelFlightButton, gbc);
    }
}
