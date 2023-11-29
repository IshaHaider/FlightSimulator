package src.Presentation;


// Search for flights to certain destinations and I want you to be able to search for flights in certain times in my database 
// which is MYSQL. then after that it should print the flights and you click on a flight then it should show a seat map. 
// There are 3 different sections of the seat map economy, business, first class. 
// When you click on a seat it should bring you to a new window that displays all the info about the seat price, 
// where it is, etc. I want you to remember all the info abut the flight and stuff and then display a new window on the c
// reditcardpanel that has all that. 

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.Domain.Seat;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.awt.event.*;

public class SearchFlightPanel extends JPanel {
    private Gui mainFrame;
    private SeatController seatController;
    private JFrame seatMapFrame;
    private Connection databaseConnection; // Database connection reference
    private JTable flightTable; // Table to display flight information
    private JTextField searchField; // Field to enter destination


    public SearchFlightPanel(Gui mainFrame, SeatController seatController) {
        this.mainFrame = mainFrame;
        this.seatController = seatController;

        setLayout(new BorderLayout());
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        searchPanel.add(new JLabel("Destination:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        flightTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(flightTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(e -> mainFrame.switchView("Home"));
        add(backButton, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> {
            String destination = searchField.getText();
            if (destination.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a destination.", "No Destination", JOptionPane.WARNING_MESSAGE);
            } else {
                executeSearch(destination);
            }
        });

        flightTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = flightTable.getSelectedRow();
                int flightIdColumnIndex = 0; // Assuming flightId is in the first column
                Object flightIDObject = flightTable.getValueAt(selectedRow, flightIdColumnIndex);
                if (flightIDObject != null) {
                    int flightID = Integer.parseInt(flightIDObject.toString());
                    seatMap(flightID);
                }
            }
        });
    }

    private void executeSearch(String destination) {
        ArrayList<Flight> currFlights = seatController.getCertainFlights(destination);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Flight ID");
        tableModel.addColumn("Departure");
        tableModel.addColumn("Destination");
        tableModel.addColumn("Departure Time");
        tableModel.addColumn("Arrival Time");

        boolean hasResults = false; // Flag to check if there are results

        // Iterate through the ResultSet and add rows to the tableModel
        for (Flight flight : currFlights) {
            hasResults = true;

            int flightId = flight.getFlightID("flight_id");
            String departure = flight.getDeparture("departure");
            String destinationCol = flight.getDestination("destination");
            String departureTime = flight.getDepartureTime("departure_time");
            String arrivalTime = flight.getArrivalTime("arrival_time");

            tableModel.addRow(new Object[]{flightId, departure, destinationCol, departureTime, arrivalTime});
        }

        if (!hasResults) {
            JOptionPane.showMessageDialog(this, "No flights available for the specified destination.", "No Flights Found", JOptionPane.INFORMATION_MESSAGE);
        }

        flightTable.setModel(tableModel);
    }

    private void seatMap(int flightID) {

        ArrayList<Seat> allSeats = seatController.getCertainSeats(flightID);

        seatMapFrame = new JFrame("Seat Map for Flight ID: " + flightID);
        seatMapFrame.setLayout(new GridLayout(9, 7)); // 7 columns for seats, 7 rows

        JButton[][] seatButtons = new JButton[9][7]; // 2-D array for seat buttons

        int seatNumber = 0;
        char seatLetter = (char) ('A');
        int seatLetterCounter = 0;
        for (int row = 0; row < 9; row++) {
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
                    boolean isAvailable = false;
                    seatButton.addActionListener(e -> {
                        // Save seat name and perform action
                        for (Seat seats : allSeats) {
                            if (seats.getSeatName().equals(seatName)) {
                                if (seats.isAvailable()) {
                                    seatButton.setBackground(Color.GREEN);
                                    String clickedSeat = seats.getSeatName();
                                    int clickedSeatID = seats.getSeatID();
                                    String clickedSeatClass = seats.getSeatClass();
                                    isAvailable = true;
                                } else {
                                    seatButton.setBackground(Color.RED);
                                }
                            }
                        }
                        if (isAvailable) {
                            displayPurchaseOptions(clickedSeat, clickedSeatID, clickedSeatClass); // Assume economy class for now
                        } else {
                            JOptionPane.showMessageDialog(this, "Seat is not available.", "Seat Not Available", JOptionPane.WARNING_MESSAGE);
                        }
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

