package src.Presentation;

import javax.swing.*;

import java.awt.*;
import java.time.LocalDate; // Import LocalDate

public class AirlineAgentPanel extends JPanel {
    private Gui mainFrame;
    private FlightController flightController;

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
        browsePassangerListbutton.addActionListener( browsePassangerList() );
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(browsePassangerListbutton, gbc);
    }

    public void browsePassangerList() {
        String[] passengers = {"Passenger 1", "Passenger 2", "Passenger 3", "Passenger 4"};

        // Create a JList
        JList<String> passengerList = new JList<>(passengers);

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
