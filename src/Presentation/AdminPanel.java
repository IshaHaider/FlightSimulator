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
    private PromotionController promotionController;

    public AdminPanel(Gui mainFrame, FlightController flightController, PromotionController promotionController) {
        this.mainFrame = mainFrame;
        this.flightController = flightController;
        this.promotionController = promotionController;
    
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
    
        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Flight Reservation System", SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // Span across 3 columns
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.PAGE_START;
        add(welcomeLabel, gbc);
    
        // Reset gridwidth for other components
        gbc.gridwidth = 1;
    
        // Browse Flights Button
        JButton searchFlightButton = new JButton("Browse Flights");
        searchFlightButton.addActionListener(e -> DisplayFlights());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(searchFlightButton, gbc);
    
        // Browse Aircrafts Button
        JButton browseAircraftsButton = new JButton("Browse Aircrafts");
        browseAircraftsButton.addActionListener(e -> displayAircrafts());
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(browseAircraftsButton, gbc);
    
        // Add/Remove Aircraft Button
        JButton addRemoveAircraftButton = new JButton("Add/Remove Aircraft");
        addRemoveAircraftButton.addActionListener(e -> addOrRemoveAirplane());
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(addRemoveAircraftButton, gbc);
    
        // Add/Remove/Modify Flights Information Button
        // JButton modifyFlightsInfoButton = new JButton("Add/Remove/Modify Flights Information");
        // modifyFlightsInfoButton.addActionListener(e -> mainFrame.switchView("Modify Flights Information"));
        // gbc.gridx = 0;
        // gbc.gridy = 2;
        // add(modifyFlightsInfoButton, gbc);
    
        // Add/Remove Crew Button
        JButton addRemoveCrewButton = new JButton("Add/Remove Crew");
        addRemoveCrewButton.addActionListener(e -> addOrRemoveCrew());
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(addRemoveCrewButton, gbc);
    
        // Enter Flight ID Label
        JLabel browseCrewLabel = new JLabel("Enter Flight ID:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(browseCrewLabel, gbc);
    
        // Enter Flight ID Text Field
        JTextField browseCrewField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(browseCrewField, gbc);
    
        // Browse Crews Button
        JButton browseCrewsButton = new JButton("Browse Crews");
        browseCrewsButton.addActionListener(
            e -> {
                String text = browseCrewField.getText();
                if (text.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid flight ID", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    int givenFlightID = Integer.parseInt(text);
                    displayCrews(givenFlightID);
                }
            }
        );
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(browseCrewsButton, gbc);
    
        // Add/Remove Flight Destinations Button
        // JButton addRemoveDestinationsButton = new JButton("Add/Remove Flight Destinations");
        // addRemoveDestinationsButton.addActionListener(e -> mainFrame.switchView("Add/Remove Flight Destinations"));
        // gbc.gridx = 0;
        // gbc.gridy = 4;
        // add(addRemoveDestinationsButton, gbc);
    
        // Print List of Users Button
        JButton printUsersButton = new JButton("Print List of Users");
        printUsersButton.addActionListener(e -> DisplayRegisteredUsers());
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(printUsersButton, gbc);
    }


    public void DisplayRegisteredUsers(){
        ArrayList<RegisteredUser> registeredUsers = flightController.browseRegisteredUsers();

        String[] columnNames = {"Name", "Email", "ID", "Phone Number", "Address"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add user data to the table model
        for (RegisteredUser user : registeredUsers) {
            model.addRow(new Object[]{
                user.getName().getFirstName() + " " + user.getName().getLastName(),
                user.getEmail(),
                user.getUserID(),
                user.getPhoneNumber(),
                user.getAddress().getNumber() + " " + user.getAddress().getStreet()
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

    public void DisplayFlights(){
        ArrayList<Flight> flights = flightController.browseFlights();

        String[] columnNames = {"Flight ID", "Departure Date", "Departure Time", "Arrival Date", "Arrival Time", "Departure Location", "Arrival Location", "Flight Status"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add user data to the table model
        for (Flight flight : flights) {
            model.addRow(new Object[]{
                flight.getFlightID(),
                flight.getDepartDate(),
                flight.getDepartTime(),
                flight.getArriveDate(),
                flight.getArriveTime(),
                flight.getDepartLocation(),
                flight.getArriveLocation(),
                flight.getFlightStatus()
            });
        }

        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        JDialog dialog = new JDialog();
        dialog.setTitle("Flights");
        dialog.add(scrollPane);
        dialog.pack(); // Adjusts size to fit the content
        dialog.setLocationRelativeTo(this); // Center relative to the admin panel
        dialog.setVisible(true); // Make it visible
    }
    
    public void displayCrews(int flightID){
        try {
            // ArrayList<Crew> crewArray = flightController.retrieveCrewList(flightId);

            // String[] columnNames = {"Name", "Role", "ID", "Phone Number", "Address"};

            // DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            // // Add user data to the table model
            // for (Crew crew : crewArray) {
            //     model.addRow(new Object[]{
            //         crew.getName().getFirstName() + " " + crew.getName().getLastName(),
            //         crew.getRole(),
            //         crew.getUserID(),
            //         crew.getPhoneNumber(),
            //         crew.getAddress().getNumber() + " " + crew.getAddress().getStreet()
            //     });
            // }

            // JTable table = new JTable(model);
            // table.setPreferredScrollableViewportSize(new Dimension(500, 70));
            // table.setFillsViewportHeight(true);

            // JScrollPane scrollPane = new JScrollPane(table);
            // JDialog dialog = new JDialog();
            // dialog.setTitle("Crews");
            // dialog.add(scrollPane);
            // dialog.pack(); // Adjusts size to fit the content
            // dialog.setLocationRelativeTo(this); // Center relative to the admin panel
            // dialog.setVisible(true); // Make it visible
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid flight ID", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayAircrafts(){
        ArrayList<AirPlane> aircrafts = flightController.browseAircrafts();

        String[] columnNames = {"Aircraft ID", "Aircraft Name"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add user data to the table model
        for (AirPlane aircraft : aircrafts) {
            model.addRow(new Object[]{
                aircraft.getAircraftID(),
                aircraft.getAircraftName(),
            });
        }

        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        JDialog dialog = new JDialog();
        dialog.setTitle("Aircrafts");
        dialog.add(scrollPane);
        dialog.pack(); // Adjusts size to fit the content
        dialog.setLocationRelativeTo(this); // Center relative to the admin panel
        dialog.setVisible(true); // Make it visible
    }

    public void addOrRemoveCrew(){
        JFrame crewFrame = new JFrame("Add or Remove Crew Member");
        crewFrame.setLayout(new GridLayout(0, 2)); // Use GridLayout for form-like structure
    
        // Create labels and text fields for each required field
        crewFrame.add(new JLabel("First Name:"));
        JTextField fnameField = new JTextField(20);
        crewFrame.add(fnameField);
    
        crewFrame.add(new JLabel("Last Name:"));
        JTextField lnameField = new JTextField(20);
        crewFrame.add(lnameField);
    
        crewFrame.add(new JLabel("Address:"));
        JTextField addressField = new JTextField(20);
        crewFrame.add(addressField);
    
        crewFrame.add(new JLabel("Email:"));
        JTextField emailField = new JTextField(20);
        crewFrame.add(emailField);
    
        crewFrame.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField(20);
        crewFrame.add(passwordField);
    
        crewFrame.add(new JLabel("Birthdate (YYYY-MM-DD):"));
        JTextField birthdateField = new JTextField(20);
        crewFrame.add(birthdateField);
    
        crewFrame.add(new JLabel("Phone Number (###-###-####):"));
        JTextField phoneNumField = new JTextField(20);
        crewFrame.add(phoneNumField);
    
        // Submit button
        JButton addButton = new JButton("add Crew Member");
        addButton.addActionListener(e -> {
            // Retrieve data from fields
            String fname = fnameField.getText();
            String lname = lnameField.getText();
            String address = addressField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            LocalDate birthdate = LocalDate.parse(birthdateField.getText()); // Requires error checking
            String phoneNum = phoneNumField.getText();
    
            flightController.addCrew(new Crew(new Name(fname, lname), new Address(address), email, password, birthdate, phoneNum));

            crewFrame.dispose(); // Close the frame after submission
        });

        crewFrame.add(new JLabel()); // Empty label for grid alignment
        crewFrame.add(addButton);

        JButton removeButton = new JButton("remove Crew Member");
        removeButton.addActionListener(e -> {
            // Retrieve data from fields
            String fname = fnameField.getText();
            String lname = lnameField.getText();
            String address = addressField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            LocalDate birthdate = LocalDate.parse(birthdateField.getText()); // Requires error checking
            String phoneNum = phoneNumField.getText();
            
            flightController.removeCrew(new Crew(new Name(fname, lname), new Address(address), email, password, birthdate, phoneNum));
    
            crewFrame.dispose(); // Close the frame after submission
        });
    
        crewFrame.add(new JLabel()); // Empty label for grid alignment
        crewFrame.add(removeButton);
    
        crewFrame.pack(); // Adjusts size to fit the components
        crewFrame.setLocationRelativeTo(null); // Center on screen
        crewFrame.setVisible(true); // Make it visible
    }

    public void addOrRemoveAirplane(){
        JFrame crewFrame = new JFrame("Add or Remove Crew Member");
        crewFrame.setLayout(new GridLayout(0, 2)); // Use GridLayout for form-like structure
        
        // Create labels and text fields for each required field
        crewFrame.add(new JLabel("Aircraft ID:"));
        JTextField craftIDField = new JTextField(20);
        crewFrame.add(craftIDField);
    
        crewFrame.add(new JLabel("Aircraft Name:"));
        JTextField craftNameField = new JTextField(20);
        crewFrame.add(craftNameField);

        // Submit button
        JButton addButton = new JButton("add Airplane");
        addButton.addActionListener(e -> {
            // Retrieve data from fields
            int craftID = Integer.parseInt(craftIDField.getText());
            String craftName = craftNameField.getText();
            
            flightController.addAirCraft(new AirPlane(craftID, craftName));

            crewFrame.dispose(); // Close the frame after submission
        });

        crewFrame.add(new JLabel()); // Empty label for grid alignment
        crewFrame.add(addButton);

        JButton removeButton = new JButton("remove Airplane");
        removeButton.addActionListener(e -> {
            // Retrieve data from fields
            int craftID = Integer.parseInt(craftIDField.getText());
            String craftName = craftNameField.getText();
            
            flightController.removeAirCraft(new AirPlane(craftID, craftName));

            crewFrame.dispose(); // Close the frame after submission
        });
    
        crewFrame.add(new JLabel()); // Empty label for grid alignment
        crewFrame.add(removeButton);
    
        crewFrame.pack(); // Adjusts size to fit the components
        crewFrame.setLocationRelativeTo(null); // Center on screen
        crewFrame.setVisible(true); // Make it visible
    }
}
