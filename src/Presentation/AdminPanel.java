package src.Presentation;
import src.Domain.*;
import src.Controllers.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import java.time.LocalDate;
import java.time.LocalTime;

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
        // JButton searchFlightButton = new JButton("Browse Flights");
        // searchFlightButton.addActionListener(e -> DisplayFlights());
        // gbc.gridx = 0;
        // gbc.gridy = 1;
        // gbc.anchor = GridBagConstraints.LINE_START;
        // add(searchFlightButton, gbc);

        // // Browse Crews Button
        // JButton browseCrewsButton = new JButton("Browse Crews");
        // browseCrewsButton.addActionListener(e -> mainFrame.switchView("Browse Crews"));
        // gbc.gridx = 1;
        // gbc.gridy = 1;
        // gbc.anchor = GridBagConstraints.LINE_END;
        // add(browseCrewsButton, gbc);

        // // Browse Aircrafts Button
        // JButton browseAircraftsButton = new JButton("Browse Aircrafts");
        // browseAircraftsButton.addActionListener(e -> mainFrame.switchView("Browse Aircrafts"));
        // gbc.gridx = 0;
        // gbc.gridy = 2;
        // gbc.anchor = GridBagConstraints.LINE_START;
        // add(browseAircraftsButton, gbc);

        // // Add/Remove Crew Button
        // JButton addRemoveCrewButton = new JButton("Add/Remove Crew");
        // addRemoveCrewButton.addActionListener(e -> mainFrame.switchView("Add/Remove Crew"));
        // gbc.gridx = 1;
        // gbc.gridy = 2;
        // gbc.anchor = GridBagConstraints.LINE_END;
        // add(addRemoveCrewButton, gbc);

        // // Add/Remove Aircraft Button
        // JButton addRemoveAircraftButton = new JButton("Add/Remove Aircraft");
        // addRemoveAircraftButton.addActionListener(e -> mainFrame.switchView("Add/Remove Aircraft"));
        // gbc.gridx = 0;
        // gbc.gridy = 3;
        // gbc.anchor = GridBagConstraints.LINE_START;
        // add(addRemoveAircraftButton, gbc);

        // // Add/Remove Flight Destinations Button
        // JButton addRemoveDestinationsButton = new JButton("Add/Remove Flight Destinations");
        // addRemoveDestinationsButton.addActionListener(e -> mainFrame.switchView("Add/Remove Flight Destinations"));
        // gbc.gridx = 1;
        // gbc.gridy = 3;
        // gbc.anchor = GridBagConstraints.LINE_END;
        // add(addRemoveDestinationsButton, gbc);

        // // Add/Remove/Modify Flights Information Button
        // JButton modifyFlightsInfoButton = new JButton("Add/Remove/Modify Flights Information");
        // modifyFlightsInfoButton.addActionListener(e -> mainFrame.switchView("Modify Flights Information"));
        // gbc.gridx = 0;
        // gbc.gridy = 4;
        // gbc.anchor = GridBagConstraints.LINE_START;
        // add(modifyFlightsInfoButton, gbc);

        // Print List of Users Button
        JButton printUsersButton = new JButton("Print List of Users");
        printUsersButton.addActionListener(e ->DisplayRegisteredUsers());
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(printUsersButton, gbc);
    }

    public void DisplayRegisteredUsers(){
        ArrayList<RegisteredUser> registeredUsers = flightController.browseRegisteredUsers();

        String[] columnNames = {"Name", "Email", "ID", "Phone Number", "Address"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add user data to the table model
        for (RegisteredUser user : registeredUsers) {
            model.addRow(new Object[]{
                user.getName(),
                user.getEmail(),
                user.getUserID(),
                user.getPhoneNumber(),
                user.getAddress()
            });
        }

        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        JDialog dialog = new JDialog();
        dialog.setTitle("Registered Users");
        dialog.add(scrollPane);
        dialog.pack(); // Adjusts size to fit the content
        dialog.setLocationRelativeTo(this); // Center relative to the admin panel
        dialog.setVisible(true); // Make it visible

    }
    
}
