package Presentation;


// Search for flights to certain destinations and I want you to be able to search for flights in certain times in my database 
// which is MYSQL. then after that it should print the flights and you click on a flight then it should show a seat map. 
// There are 3 different sections of the seat map economy, business, first class. 
// When you click on a seat it should bring you to a new window that displays all the info about the seat price, 
// where it is, etc. I want you to remember all the info abut the flight and stuff and then display a new window on the c
// reditcardpanel that has all that. 

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Domain.Seat;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.awt.event.*;

public class SearchFlightPanel extends JPanel {
    private Gui mainFrame;
    private JFrame seatMapFrame;
    private Connection databaseConnection; // Database connection reference
    private JTable flightTable; // Table to display flight information
    private JTextField searchField; // Field to enter destination

    public SearchFlightPanel(Gui mainFrame) {
        this.mainFrame = mainFrame;
        this.databaseConnection = mainFrame.getDatabaseConnection(); // Get the database connection

        setLayout(new BorderLayout());

        // Create a panel for search controls
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        searchPanel.add(new JLabel("Destination:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Create a table to display flight information
        flightTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(flightTable);
        add(scrollPane, BorderLayout.CENTER);

        // Create a button to go back to the home screen
        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(e -> mainFrame.switchView("Home"));
        add(backButton, BorderLayout.SOUTH);

        // Add action listener to the search button
        searchButton.addActionListener(e -> {
            String destination = searchField.getText();
            if (destination.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a destination.", "No Destination", JOptionPane.WARNING_MESSAGE);
            } else {
                // executeSearch(destination);
                seatMap(1); // JUST CHECKING SEATMAP HERE DELETE AFTER
            }
        });

        flightTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = flightTable.getSelectedRow();
                int flightIdColumnIndex = 0; // Assuming flightId is in the first column

                // Get the flightID value from the selected row and the flightIdColumnIndex
                Object flightIDObject = flightTable.getValueAt(selectedRow, flightIdColumnIndex);

                if (flightIDObject != null) {
                    int flightID = Integer.parseInt(flightIDObject.toString());
                    seatMap(flightID);
                }
            }
        });
    }

    private void executeSearch(String destination) {
        try {
            // Define your SQL query to retrieve flight information based on destination
            String sql = "SELECT * FROM flights WHERE destination = ?"; // Replace 'flights' with your table name

            // Create a PreparedStatement
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setString(1, destination); // Set destination parameter
            ResultSet resultSet = preparedStatement.executeQuery();

            // Create a DefaultTableModel to hold the data for the JTable
            DefaultTableModel tableModel = new DefaultTableModel();

            // Add columns to the tableModel
            tableModel.addColumn("Flight ID");
            tableModel.addColumn("Departure");
            tableModel.addColumn("Destination");
            tableModel.addColumn("Departure Time");
            tableModel.addColumn("Arrival Time");

            boolean hasResults = false; // Flag to check if there are results

            // Iterate through the ResultSet and add rows to the tableModel
            while (resultSet.next()) {
                hasResults = true;
                int flightId = resultSet.getInt("flight_id");
                String departure = resultSet.getString("departure");
                String destinationCol = resultSet.getString("destination");
                String departureTime = resultSet.getString("departure_time");
                String arrivalTime = resultSet.getString("arrival_time");

                tableModel.addRow(new Object[]{flightId, departure, destinationCol, departureTime, arrivalTime});
            }

            if (!hasResults) {
                // No flights available for the given destination
                JOptionPane.showMessageDialog(this, "No flights available for the specified destination.", "No Flights Found", JOptionPane.INFORMATION_MESSAGE);
            }

            // Set the tableModel to the flightTable
            flightTable.setModel(tableModel);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void seatMap(int flightID) {
        // try {
        //     // Query to get seat information based on flightID
        //     String sql = "SELECT seat_number, class FROM seats WHERE flight_id = ?"; // Replace 'seats' with your table name
        //     PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
        //     preparedStatement.setInt(1, flightID);
        //     ResultSet resultSet = preparedStatement.executeQuery();

        //     // Create a new frame to display seat information
        //     JFrame seatMapFrame = new JFrame("Seat Map for Flight " + flightID);
        //     seatMapFrame.setLayout(new BorderLayout());
        //     JTabbedPane tabbedPane = new JTabbedPane();

        //     // Create tables for each class
        //     JTable economyTable = new JTable(new DefaultTableModel(new Object[]{"Seat Number"}, 0));
        //     JTable firstClassTable = new JTable(new DefaultTableModel(new Object[]{"Seat Number"}, 0));
        //     JTable businessTable = new JTable(new DefaultTableModel(new Object[]{"Seat Number"}, 0));

        //     while (resultSet.next()) {
        //         String seatNumber = resultSet.getString("seat_number");
        //         String seatClass = resultSet.getString("class");

        //         // Add rows to respective tables based on class
        //         switch (seatClass.toLowerCase()) {
        //             case "economy":
        //                 ((DefaultTableModel) economyTable.getModel()).addRow(new Object[]{seatNumber});
        //                 break;
        //             case "first class":
        //                 ((DefaultTableModel) firstClassTable.getModel()).addRow(new Object[]{seatNumber});
        //                 break;
        //             case "business":
        //                 ((DefaultTableModel) businessTable.getModel()).addRow(new Object[]{seatNumber});
        //                 break;
        //         }
        //     }

        //     // Add tables to tabbed pane
        //     tabbedPane.addTab("Economy", new JScrollPane(economyTable));
        //     tabbedPane.addTab("First Class", new JScrollPane(firstClassTable));
        //     tabbedPane.addTab("Business", new JScrollPane(businessTable));

        //     // Add tabbed pane to the frame
        //     seatMapFrame.add(tabbedPane, BorderLayout.CENTER);
        //     seatMapFrame.pack();
        //     seatMapFrame.setVisible(true);

        //     MouseAdapter seatSelectionHandler = new MouseAdapter() {
        //         @Override
        //         public void mouseClicked(MouseEvent e) {
        //             if (e.getClickCount() == 2) { // Double-click to select a seat
        //                 JTable sourceTable = (JTable) e.getSource();
        //                 int selectedRow = sourceTable.getSelectedRow();
        //                 int seatNumberColumnIndex = 0; // Assuming seatNumber is in the first column
        //                 Object seatIDObject = sourceTable.getValueAt(selectedRow, seatNumberColumnIndex);
    
        //                 if (seatIDObject != null) {
        //                     String seatID = seatIDObject.toString();
        //                     seatMapFrame.dispose(); // Close the seat map frame
        //                     String seatClass = ""; // Determine the seat class based on the source table
        //                     if (e.getSource() == economyTable) {
        //                         seatClass = "Economy";
        //                     } else if (e.getSource() == firstClassTable) {
        //                         seatClass = "First Class";
        //                     } else if (e.getSource() == businessTable) {
        //                         seatClass = "Business";
        //                     }
        //                     displayPurchaseOptions(seatID, flightID, seatClass);
        //                 }
        //             }
        //         }
        //     };
    
        //     // Add mouse listeners to each table
        //     economyTable.addMouseListener(seatSelectionHandler);
        //     firstClassTable.addMouseListener(seatSelectionHandler);
        //     businessTable.addMouseListener(seatSelectionHandler);    

        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }

        seatMapFrame = new JFrame("Seat Map for Flight ID: " + flightID);
        seatMapFrame.setLayout(new GridLayout(7, 7)); // 7 columns for seats, 7 rows
    
        JButton[][] seatButtons = new JButton[7][7]; // 2-D array for seat buttons
        
        int seatNumber = 0;
        char seatLetter = (char) ('A');
        int seatLetterCounter = 0;
        for (int row = 0; row < 7; row++) {
            seatNumber += 1;
            seatLetterCounter = 0;
            for (int col = 0; col < 7; col++) {

                if (col == 3) { // Middle column for aisle
                    JLabel aisleLabel = new JLabel("Aisle");
                    aisleLabel.setHorizontalAlignment(JLabel.CENTER);
                    seatMapFrame.add(aisleLabel);
                    seatNumber += 1;
                    seatLetterCounter = 0;
                } 
                
                else {
                    seatLetter = (char) ('A' + seatLetterCounter);
                    seatLetterCounter += 1;
                    String seatName = seatNumber + "" + seatLetter;
                    JButton seatButton = new JButton(seatName);
                    seatButton.addActionListener(e -> {
                        // Save seat name and perform action
                        displayPurchaseOptions(seatName, flightID, "Economy"); // Assume economy class for now
                    });
                    seatMapFrame.add(seatButton);
                    seatButtons[row][col] = seatButton;
                }
            }
        }

        seatMapFrame.pack();
        seatMapFrame.setVisible(true);
    }

    private void displayPurchaseOptions(String seatID, int flightID, String seatClass) {
        JFrame optionsFrame = new JFrame("Purchase Options for Seat ID: " + seatID);
        optionsFrame.setLayout(new FlowLayout());
        optionsFrame.setSize(300, 100);
    
        JLabel seatLabel = new JLabel("Do you want to buy Seat ID: " + seatID + " on Flight ID: " + flightID + "?");
        JButton buyButton = new JButton("Buy");
        JButton noBuyButton = new JButton("No Buy");
    
        buyButton.addActionListener(e -> {
            optionsFrame.dispose();
            CreditCardPanel creditCardPanel = mainFrame.getCreditCardPanel();
            creditCardPanel.setFlightNumber(flightID);
            creditCardPanel.setSeatID(seatID);
            creditCardPanel.setSeatClass(seatClass);
            seatMapFrame.dispose();
            mainFrame.switchView("Payment");
        });
    
        noBuyButton.addActionListener(e -> {
            optionsFrame.dispose();
            // Stay on the current panel, you might want to refresh or update it
        });
    
        optionsFrame.add(seatLabel);
        optionsFrame.add(buyButton);
        optionsFrame.add(noBuyButton);
    
        optionsFrame.setVisible(true);
    }
}

