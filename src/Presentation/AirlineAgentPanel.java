
package src.Presentation;

import src.Domain.*;
import src.Controllers.*;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AirlineAgentPanel extends JPanel {
    private Gui mainFrame;
    private FlightController flightController;
    private JTextField flightIdField; // TextField for entering flight ID

    public AirlineAgentPanel(Gui mainFrame, FlightController flightController) {
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

        // Search Flights Button
        JButton searchFlightButton = new JButton("Search Flights");
        searchFlightButton.addActionListener(e -> mainFrame.switchView("Search Flight"));
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

        // Flight ID Label and TextField
        JLabel flightIdLabel = new JLabel("Enter Flight ID:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(flightIdLabel, gbc);

        flightIdField = new JTextField(10); // Specify the width of the text field
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(flightIdField, gbc);

        // Browse Passenger List Button
        JButton browsePassengerListButton = new JButton("Browse Passenger List");
        browsePassengerListButton.addActionListener(e -> browsePassangerList(Integer.valueOf(flightIdField.getText())));
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(browsePassengerListButton, gbc);
    }

    public void browsePassangerList(int flightID) {
        ArrayList<User> passengerArray = flightController.retrievePassengerList(flightID);

        String[] passengerNames = new String[passengerArray.size()];
        for (int i = 0; i < passengerArray.size(); i++) {
            String firstName = passengerArray.get(i).getName().getFirstName();
            String lastName = passengerArray.get(i).getName().getLastName();
            passengerNames[i] = firstName + " " + lastName;
        }
        // Create a JList with the array of Strings
        JList<String> passengerList = new JList<>(passengerNames);

        // Put JList in a JScrollPane for scrollability
        JScrollPane scrollPane = new JScrollPane(passengerList);
        scrollPane.setPreferredSize(new Dimension(250, 150)); // Set the preferred size of the scroll pane

        // Create a new JFrame to show the passenger list
        JFrame frame = new JFrame("Passenger List");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame on close
        frame.getContentPane().add(scrollPane); // Add scroll pane to the frame

        // Display the frame
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

}
