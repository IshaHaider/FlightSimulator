package src.Presentation;

import src.Domain.*;
import src.Controllers.*;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
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

        // Add/Remove/Modify Aircraft Button
        JButton maintainAircraftButton = new JButton("Maintain Aircrafts");
        maintainAircraftButton.addActionListener(e -> maintainAircrafts());
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(maintainAircraftButton, gbc);

        // Add/Remove/Modify Flights Information Button
        JButton maintainFlightsButton = new JButton("Maintain Flights");
        maintainFlightsButton.addActionListener(e -> maintainFlights());
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(maintainFlightsButton, gbc);

        // Add/Remove/Modify Crew Button
        JButton maintainCrewButton = new JButton("Maintain Crew");
        maintainCrewButton.addActionListener(e -> maintainCrew());
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(maintainCrewButton, gbc);

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
                        JOptionPane.showMessageDialog(this, "Please enter a valid flight ID", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        int givenFlightID = Integer.parseInt(text);
                        displayCrews(givenFlightID);
                    }
                });
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(browseCrewsButton, gbc);

        // Print List of Users Button
        JButton printUsersButton = new JButton("Print List of Users");
        printUsersButton.addActionListener(e -> DisplayRegisteredUsers());
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(printUsersButton, gbc);
    }

    public void DisplayRegisteredUsers() {
        ArrayList<RegisteredUser> registeredUsers = flightController.browseRegisteredUsers();
        String[] columnNames = { "Name", "Email", "ID", "Phone Number", "Address" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add user data to the table model
        for (RegisteredUser user : registeredUsers) {
            model.addRow(new Object[] {
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

    public void DisplayFlights() {
        ArrayList<Flight> flights = flightController.browseFlights();

        String[] columnNames = { "Flight ID", "Departure Date", "Departure Time", "Arrival Date", "Arrival Time",
                "Departure Location", "Arrival Location", "Flight Status" };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add user data to the table model
        for (Flight flight : flights) {
            model.addRow(new Object[] {
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

    public void displayCrews(int flightID) {
        try {
            ArrayList<Crew> crewArray = flightController.browseCrew(flightID);
            String[] columnNames = { "Name", "ID", "Phone Number", "Address", "Email" };
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            // Add user data to the table model
            for (Crew crew : crewArray) {
                model.addRow(new Object[] {
                        crew.getName().getFirstName() + " " + crew.getName().getLastName(),
                        crew.getUserID(),
                        crew.getPhoneNumber(),
                        crew.getAddress().getNumber() + " " + crew.getAddress().getStreet(),
                        crew.getEmail()
                });
            }

            JTable table = new JTable(model);
            table.setPreferredScrollableViewportSize(new Dimension(500, 70));
            table.setFillsViewportHeight(true);

            JScrollPane scrollPane = new JScrollPane(table);
            JDialog dialog = new JDialog();
            dialog.setTitle("Crews");
            dialog.add(scrollPane);
            dialog.pack(); // Adjusts size to fit the content
            dialog.setLocationRelativeTo(this); // Center relative to the admin panel
            dialog.setVisible(true); // Make it visible
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid flight ID", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayAircrafts() {
        ArrayList<AirPlane> aircrafts = flightController.browseAircrafts();
        String[] columnNames = { "Aircraft ID", "Aircraft Name" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add user data to the table model
        for (AirPlane aircraft : aircrafts) {
            model.addRow(new Object[] {
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

    public void maintainCrew() {
        JFrame crewFrame = new JFrame("Maintain Crew Members");
        crewFrame.setLayout(new GridLayout(0, 2)); // Use GridLayout for form-like structure

        // Create labels and text fields for each required field
        crewFrame.add(new JLabel("CrewID:"));
        JTextField crewIDField = new JTextField(20);
        crewFrame.add(crewIDField);

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
        JButton addButton = new JButton("Add/Update Crew Member");
        addButton.addActionListener(e -> {
            // Retrieve data from fields
            int crewID = crewIDField.getText().isEmpty() ? 0 : Integer.valueOf(crewIDField.getText());
            String fname = fnameField.getText().isEmpty() ? null : fnameField.getText();
            String lname = lnameField.getText().isEmpty() ? null : lnameField.getText();
            String address = addressField.getText().isEmpty() ? null : addressField.getText();
            String email = emailField.getText().isEmpty() ? null : emailField.getText();

            char[] passwordChars = passwordField.getPassword();
            String password = (passwordChars.length == 0) ? null : new String(passwordChars);

            LocalDate birthdate = birthdateField.getText().isEmpty() ? null : LocalDate.parse(birthdateField.getText());
            String phoneNum = phoneNumField.getText().isEmpty() ? null : phoneNumField.getText();

            if (flightController.checkIDExists(crewID, "Crew")) {
                Crew crewObject = flightController.createCrewObject(crewID);
                fname = (fname != null) ? fname : crewObject.getName().getFirstName();
                lname = (lname != null) ? lname : crewObject.getName().getLastName();
                address = (address != null) ? address : crewObject.getAddress().getString();
                email = (email != null) ? email : crewObject.getEmail();
                password = (password != null) ? password : crewObject.getPassword();
                birthdate = (birthdate != null) ? birthdate : crewObject.getBirthDate();
                phoneNum = (phoneNum != null) ? phoneNum : crewObject.getPhoneNumber();
                flightController.updateCrewUser(new Crew(crewID, new Name(fname, lname), new Address(address), email,
                        password, birthdate, phoneNum));
            } else {
                flightController.addCrew(
                        new Crew(new Name(fname, lname), new Address(address), email, password, birthdate, phoneNum));
            }
            crewFrame.dispose(); // Close the frame after submission
        });

        crewFrame.add(new JLabel()); // Empty label for grid alignment
        crewFrame.add(addButton);

        JButton removeButton = new JButton("Remove Crew Member");
        removeButton.addActionListener(e -> {
            // Retrieve data from fields
            int crewID = Integer.parseInt(crewIDField.getText());
            flightController.removeCrew(crewID);

            crewFrame.dispose(); // Close the frame after submission
        });

        crewFrame.add(new JLabel()); // Empty label for grid alignment
        crewFrame.add(removeButton);

        crewFrame.pack(); // Adjusts size to fit the components
        crewFrame.setLocationRelativeTo(null); // Center on screen
        crewFrame.setVisible(true); // Make it visible
    }

    public void maintainAircrafts() {
        JFrame airplaneFrame = new JFrame("Maintain Aircrafts");
        airplaneFrame.setLayout(new GridLayout(0, 2)); // Use GridLayout for form-like structure

        // Create labels and text fields for each required field
        airplaneFrame.add(new JLabel("Aircraft ID:"));
        JTextField craftIDField = new JTextField(20);
        airplaneFrame.add(craftIDField);

        airplaneFrame.add(new JLabel("Aircraft Name:"));
        JTextField craftNameField = new JTextField(20);
        airplaneFrame.add(craftNameField);

        // Submit button
        JButton addButton = new JButton("Add/Update Airplane");
        addButton.addActionListener(e -> {
            // Retrieve data from fields
            int craftID = craftIDField.getText().isEmpty() ? 0 : Integer.parseInt(craftIDField.getText());
            String craftName = craftNameField.getText().isEmpty() ? null : craftNameField.getText();

            if (flightController.checkIDExists(craftID, "Aircraft")) {
                AirPlane airplaneObject = flightController.createAircraftObject(craftID);
                craftName = (craftName != null) ? craftName : airplaneObject.getAircraftName();
                flightController.updateAircraft(new AirPlane(craftID, craftName));
            } else {
                flightController.addAirCraft(new AirPlane(craftName));
            }
            airplaneFrame.dispose(); // Close the frame after submission
        });

        airplaneFrame.add(new JLabel()); // Empty label for grid alignment
        airplaneFrame.add(addButton);

        JButton removeButton = new JButton("Remove Airplane");
        removeButton.addActionListener(e -> {
            // Retrieve data from fields
            int craftID = Integer.parseInt(craftIDField.getText());
            String craftName = craftNameField.getText();
            flightController.removeAirCraft(new AirPlane(craftID, craftName));
            airplaneFrame.dispose(); // Close the frame after submission
        });

        airplaneFrame.add(new JLabel()); // Empty label for grid alignment
        airplaneFrame.add(removeButton);

        airplaneFrame.pack(); // Adjusts size to fit the components
        airplaneFrame.setLocationRelativeTo(null); // Center on screen
        airplaneFrame.setVisible(true); // Make it visible
    }

    public void maintainFlights() {
        JFrame flightFrame = new JFrame("Maintain Flights");
        flightFrame.setLayout(new GridLayout(0, 2)); // Use GridLayout for form-like structure

        // Create labels and text fields for each required field
        flightFrame.add(new JLabel("Flight ID:"));
        JTextField flightIDField = new JTextField(20);
        flightFrame.add(flightIDField);

        flightFrame.add(new JLabel("Aircraft ID:"));
        JTextField aircraftIDField = new JTextField(20);
        flightFrame.add(aircraftIDField);

        flightFrame.add(new JLabel("Depart Date (yyyy-mm-dd):"));
        JTextField departDateField = new JTextField(20);
        flightFrame.add(departDateField);

        flightFrame.add(new JLabel("Depart Time (##:##):"));
        JTextField departTimeField = new JTextField(20);
        flightFrame.add(departTimeField);

        flightFrame.add(new JLabel("Depart Location:"));
        JTextField departLocationField = new JTextField(20);
        flightFrame.add(departLocationField);

        flightFrame.add(new JLabel("Arrival Date:"));
        JTextField arrivalDateField = new JTextField(20);
        flightFrame.add(arrivalDateField);

        flightFrame.add(new JLabel("Arrival Time:"));
        JTextField arrivalTimeField = new JTextField(20);
        flightFrame.add(arrivalTimeField);

        flightFrame.add(new JLabel("Arrival Location:"));
        JTextField arrivalLocationField = new JTextField(20);
        flightFrame.add(arrivalLocationField);

        flightFrame.add(new JLabel("Flight Status:"));
        JTextField flightStatusField = new JTextField(20);
        flightFrame.add(flightStatusField);

        flightFrame.add(new JLabel("Cost:"));
        JTextField costField = new JTextField(20);
        flightFrame.add(costField);

        flightFrame.add(new JLabel("Meal:"));
        JTextField mealField = new JTextField(20);
        flightFrame.add(mealField);

        flightFrame.add(new JLabel("Crew Member 1:"));
        JTextField crewMember1Field = new JTextField(20);
        flightFrame.add(crewMember1Field);

        flightFrame.add(new JLabel("Crew Member 2:"));
        JTextField crewMember2Field = new JTextField(20);
        flightFrame.add(crewMember2Field);

        flightFrame.add(new JLabel("Crew Member 3:"));
        JTextField crewMember3Field = new JTextField(20);
        flightFrame.add(crewMember3Field);

        // Submit button
        JButton addButton = new JButton("Add/Update Flight");
        addButton.addActionListener(e -> {
            // Retrieve data from fields
            int flightID = flightIDField.getText().isEmpty() ? 0 : Integer.valueOf(flightIDField.getText());
            int aircraftID = aircraftIDField.getText().isEmpty() ? 0 : Integer.valueOf(aircraftIDField.getText());
            LocalDate departDate = departDateField.getText().isEmpty() ? null
                    : LocalDate.parse(departDateField.getText()); // Requires error checking
            LocalTime departTime = departTimeField.getText().isEmpty() ? null
                    : LocalTime.parse(departTimeField.getText()); // Requires error checking
            String departLocation = departLocationField.getText().isEmpty() ? null : departLocationField.getText();
            LocalDate arrivalDate = arrivalDateField.getText().isEmpty() ? null
                    : LocalDate.parse(arrivalDateField.getText()); // Requires error checking
            LocalTime arrivalTime = arrivalTimeField.getText().isEmpty() ? null
                    : LocalTime.parse(arrivalTimeField.getText()); // Requires error checking

            String arrivalLocation = arrivalLocationField.getText().isEmpty() ? null : arrivalLocationField.getText();
            int crewMember1 = crewMember1Field.getText().isEmpty() ? 0 : Integer.valueOf(crewMember1Field.getText());
            int crewMember2 = crewMember2Field.getText().isEmpty() ? 0 : Integer.valueOf(crewMember2Field.getText());
            int crewMember3 = crewMember3Field.getText().isEmpty() ? 0 : Integer.valueOf(crewMember3Field.getText());

            String flightStatus = flightStatusField.getText().isEmpty() ? null : flightStatusField.getText();
            Status flightStatusEnum = null;
            if (flightStatus != null) {
                switch (flightStatus.toLowerCase()) { // Switch flightstatus to enum
                    case "on time":
                        flightStatusEnum = Status.OnTime;
                        break;
                    case "delayed":
                        flightStatusEnum = Status.Delayed;
                        break;
                    default:
                        flightStatusEnum = Status.OnTime;
                        break;
                }
            }
            float cost = costField.getText().isEmpty() ? 0.0f : Float.parseFloat(costField.getText());
            boolean meal = mealField.getText().isEmpty() ? false : Boolean.parseBoolean(mealField.getText());

            if (flightController.checkIDExists(flightID, "Flight")) {
                Flight flightObject = flightController.createFlightObject(flightID);
                aircraftID = (aircraftID != 0) ? aircraftID : flightObject.getAircraftID();
                departDate = (departDate != null) ? departDate : flightObject.getDepartDate();
                departTime = (departTime != null) ? departTime : flightObject.getDepartTime();
                departLocation = (departLocation != null) ? departLocation : flightObject.getDepartLocation();
                arrivalDate = (arrivalDate != null) ? arrivalDate : flightObject.getArriveDate();
                arrivalTime = (arrivalTime != null) ? arrivalTime : flightObject.getArriveTime();
                arrivalLocation = (arrivalLocation != null) ? arrivalLocation : flightObject.getArriveLocation();
                crewMember1 = (crewMember1 != 0) ? crewMember1 : flightObject.getCrewMember1();
                crewMember2 = (crewMember2 != 0) ? crewMember2 : flightObject.getCrewMember2();
                crewMember3 = (crewMember3 != 0) ? crewMember3 : flightObject.getCrewMember3();
                cost = (cost != 0.0) ? cost : flightObject.getCost();
                meal = (meal != false) ? meal : flightObject.getMeal();
                flightStatusEnum = (flightStatusEnum != null) ? flightStatusEnum : flightObject.getFlightStatus();

                flightController.updateFlight(new Flight(flightID, aircraftID, departDate, departTime, departLocation,
                        arrivalDate, arrivalTime, arrivalLocation, flightStatusEnum, cost, meal, crewMember1,
                        crewMember2, crewMember3));
            } else {
                Flight tmpFlight = new Flight(aircraftID, departDate, departTime, departLocation, arrivalDate,
                        arrivalTime, arrivalLocation, flightStatusEnum, cost, meal, crewMember1, crewMember2,
                        crewMember3);
                flightController.addFlight(tmpFlight);
            }
            flightFrame.dispose(); // Close the frame after submission
        });

        flightFrame.add(new JLabel()); // Empty label for grid alignment
        flightFrame.add(addButton);

        JButton removeButton = new JButton("Remove Flight");
        removeButton.addActionListener(e -> {
            // Retrieve data from fields
            int flightID = Integer.valueOf(flightIDField.getText());
            flightController.removeFlight(flightID);
            flightFrame.dispose(); // Close the frame after submission
        });

        flightFrame.add(new JLabel()); // Empty label for grid alignment
        flightFrame.add(removeButton);

        flightFrame.pack(); // Adjusts size to fit the components
        flightFrame.setLocationRelativeTo(null); // Center on screen
        flightFrame.setVisible(true); // Make it visible
    }

}
