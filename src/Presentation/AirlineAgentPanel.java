package src.Presentation;

import javax.swing.*;

import java.awt.*;
import java.time.LocalDate; // Import LocalDate

public class AirlineAgentPanel extends JPanel {
    public AirlineAgentPanel(Gui mainFrame) {
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

        // Search Flights Button
        JButton searchFlightButton = new JButton("Search Flights");
        searchFlightButton.addActionListener(e -> mainFrame.switchView("Payment"));
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

        // Cancel Flights Button
        JButton browsePassangerListbutton = new JButton("Browse Passanger List"); //NEEDA DO SOMETHING TO SHOW LIST IDK YET
        // browsePassangerListbutton.addActionListener(e -> mainFrame.switchView("Cancel Flight"));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(browsePassangerListbutton, gbc);
    }
}
