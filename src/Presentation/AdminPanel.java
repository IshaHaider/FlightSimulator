package src.Presentation;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {
    private Gui mainFrame;
    private FlightController flightController;

    public AdminPanel(Gui mainFrame, FlightController flightController) {
        this.mainFrame = mainFrame;
        this.flightController = flightController;
        
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
        JButton searchFlightButton = new JButton("Browse Flights");
        searchFlightButton.addActionListener(e -> mainFrame.switchView("Browse Flights"));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(searchFlightButton, gbc);

        // Browse Crews Button
        JButton browseCrewsButton = new JButton("Browse Crews");
        browseCrewsButton.addActionListener(e -> mainFrame.switchView("Browse Crews"));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(browseCrewsButton, gbc);

        // Browse Aircrafts Button
        JButton browseAircraftsButton = new JButton("Browse Aircrafts");
        browseAircraftsButton.addActionListener(e -> mainFrame.switchView("Browse Aircrafts"));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(browseAircraftsButton, gbc);

        // Add/Remove Crew Button
        JButton addRemoveCrewButton = new JButton("Add/Remove Crew");
        addRemoveCrewButton.addActionListener(e -> mainFrame.switchView("Add/Remove Crew"));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(addRemoveCrewButton, gbc);

        // Add/Remove Aircraft Button
        JButton addRemoveAircraftButton = new JButton("Add/Remove Aircraft");
        addRemoveAircraftButton.addActionListener(e -> mainFrame.switchView("Add/Remove Aircraft"));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(addRemoveAircraftButton, gbc);

        // Add/Remove Flight Destinations Button
        JButton addRemoveDestinationsButton = new JButton("Add/Remove Flight Destinations");
        addRemoveDestinationsButton.addActionListener(e -> mainFrame.switchView("Add/Remove Flight Destinations"));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(addRemoveDestinationsButton, gbc);

        // Add/Remove/Modify Flights Information Button
        JButton modifyFlightsInfoButton = new JButton("Add/Remove/Modify Flights Information");
        modifyFlightsInfoButton.addActionListener(e -> mainFrame.switchView("Modify Flights Information"));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(modifyFlightsInfoButton, gbc);

        // Print List of Users Button
        JButton printUsersButton = new JButton("Print List of Users");
        printUsersButton.addActionListener(e -> mainFrame.switchView("Print List of Users"));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(printUsersButton, gbc);
    }
}
