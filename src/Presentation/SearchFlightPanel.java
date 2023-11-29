package src.Presentation;
import src.Domain.*;
import src.Controllers.*;

// Search for flights to certain destinations and I want you to be able to search for flights in certain times in my database 
// which is MYSQL. then after that it should print the flights and you click on a flight then it should show a seat map. 
// There are 3 different sections of the seat map economy, business, first class. 
// When you click on a seat it should bring you to a new window that displays all the info about the seat price, 
// where it is, etc. I want you to remember all the info abut the flight and stuff and then display a new window on the c
// reditcardpanel that has all that. 

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;

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
                if (selectedRow >= 0) {
                    int flightId = Integer.parseInt(flightTable.getValueAt(selectedRow, 0).toString());
                    int aircraftId = Integer.parseInt(flightTable.getValueAt(selectedRow, 1).toString());
                    String arrivalLoc = flightTable.getValueAt(selectedRow, 2).toString();
                    String departLoc = flightTable.getValueAt(selectedRow, 3).toString();
                    LocalDate departDate = LocalDate.parse(flightTable.getValueAt(selectedRow, 4).toString());
                    LocalTime departTime = LocalTime.parse(flightTable.getValueAt(selectedRow, 5).toString());
                    LocalDate arrivalDate = LocalDate.parse(flightTable.getValueAt(selectedRow, 6).toString());
                    LocalTime arrivalTime = LocalTime.parse(flightTable.getValueAt(selectedRow, 7).toString());
                    seatMap(flightId, aircraftId, arrivalLoc, departLoc, departDate, departTime, arrivalDate, arrivalTime);
                }
            }
        });
    }

    private void executeSearch(String destination) {
        ArrayList<Flight> currFlights = seatController.getCertainFlights(destination);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Flight ID");
        tableModel.addColumn("aircraft ID");
        tableModel.addColumn("Arrival Location");
        tableModel.addColumn("Departure Location");
        tableModel.addColumn("Departure Date");
        tableModel.addColumn("Departure Time");
        tableModel.addColumn("Arrival Date");
        tableModel.addColumn("Arrival Time");
        
        boolean hasResults = false; // Flag to check if there are results

        // Iterate through the ResultSet and add rows to the tableModel
        for (Flight flight : currFlights) {
            hasResults = true;

            int flightId = flight.getFlightID();
            int aircraftId = flight.getAircraftID();
            String arrivalLocation = flight.getArriveLocation();
            String departLocation = flight.getDepartLocation();
            LocalDate departDate = flight.getDepartDate();
            LocalTime departTime = flight.getDepartTime();
            LocalDate arrivalDate = flight.getArriveDate();
            LocalTime arrivalTime = flight.getArriveTime();

            tableModel.addRow(new Object[]{flightId, aircraftId, arrivalLocation, departLocation, departDate, departTime, arrivalDate, arrivalTime});
        }

        if (!hasResults) {
            JOptionPane.showMessageDialog(this, "No flights available for the specified destination.", "No Flights Found", JOptionPane.INFORMATION_MESSAGE);
        }

        flightTable.setModel(tableModel);
    }

    private void seatMap(int flightID, int aircraftID, String arrivalLoc, String departLoc, LocalDate departDate, LocalTime departTime, LocalDate arrivalDate, LocalTime arrivalTime) {

        ArrayList<Seat> allSeats = seatController.getCertainSeats(flightID);

        seatMapFrame = new JFrame("Seat Map for Flight ID: " + flightID);
        seatMapFrame.setLayout(new GridLayout(9, 7)); // 7 columns for seats, 7 rows

        JButton[][] seatButtons = new JButton[9][7]; // 2-D array for seat buttons

        int seatIndex = 0;
        for (int row=0; row < 9; row++) {
            for (int col = 0; col < 7; col++) {
                if (col == 3) {
                    JLabel aisleLabel = new JLabel("Aisle");
                    aisleLabel.setHorizontalAlignment(JLabel.CENTER);
                    seatMapFrame.add(aisleLabel);
                } else {
                    if (seatIndex < allSeats.size()) {
                        Seat currentSeat = allSeats.get(seatIndex);
                        seatIndex++;
                        String seatName = currentSeat.getSeatName();
                        AirplaneClass classType = currentSeat.getSeatClass();
                        JButton seatButton = new JButton(seatName);
                        boolean isAvailable = currentSeat.getAvailable();
                        if (isAvailable) {
                            if (classType  == AirplaneClass.Economy) {
                                seatButton.setBackground(Color.BLUE);
                            } else if (classType  == AirplaneClass.Business) {
                                seatButton.setBackground(Color.GREEN);
                            }
                        } else { 
                            seatButton.setBackground(Color.RED);
                            seatButton.setEnabled(false);
                        }

                        seatButton.addActionListener(e -> {
                            displayPurchaseOptions(currentSeat.getSeatID(), currentSeat.getAircraftID(), seatName, currentSeat.getSeatClass(), currentSeat.getCost(), currentSeat.getBaggage(), flightID, departDate, departTime, arrivalDate, arrivalTime, arrivalLoc, departLoc);
                        });

                        seatMapFrame.add(seatButton);
                        seatButtons[row][col] = seatButton;
                    }
                }
            }
        }

        // int seatNumber = 0;
        // char seatLetter = (char) ('A');
        // int seatLetterCounter = 0;
        // for (int row = 0; row < 9; row++) {
        //     seatNumber += 1;
        //     seatLetterCounter = 0;
        //     for (int col = 0; col < 7; col++) {

        //         if (col == 3) { // Middle column for aisle
        //             JLabel aisleLabel = new JLabel("Aisle");
        //             aisleLabel.setHorizontalAlignment(JLabel.CENTER);
        //             seatMapFrame.add(aisleLabel);
        //             seatNumber += 1;
        //             seatLetterCounter = 0;
        //         } 
        //         else {
        //             seatLetter = (char) ('A' + seatLetterCounter);
        //             seatLetterCounter += 1;
        //             String seatName = seatNumber + "" + seatLetter;
        //             JButton seatButton = new JButton(seatName);
        //             boolean isAvailable = false;
        //             seatButton.addActionListener(e -> {
        //                 // Save seat name and perform action
        //                 for (Seat seats : allSeats) {
        //                     if (seats.getSeatName().equals(seatName)) {
        //                         if (seats.isAvailable()) {
        //                             seatButton.setBackground(Color.GREEN);
        //                             String clickedSeat = seats.getSeatName();
        //                             int clickedSeatID = seats.getSeatID();
        //                             String clickedSeatClass = seats.getSeatClass();
        //                             isAvailable = true;
        //                         } else {
        //                             seatButton.setBackground(Color.RED);
        //                         }
        //                     }
        //                 }
        //                 if (isAvailable) {
        //                     displayPurchaseOptions(clickedSeat, clickedSeatID, clickedSeatClass);
        //                 } else {
        //                     JOptionPane.showMessageDialog(this, "Seat is not available.", "Seat Not Available", JOptionPane.WARNING_MESSAGE);
        //                 }
        //             });
        //             seatMapFrame.add(seatButton);
        //             seatButtons[row][col] = seatButton;
        //         }
        //     }
        // }
        
        seatMapFrame.pack();
        seatMapFrame.setVisible(true);
    }

    private void displayPurchaseOptions(int seatID, int aircraftID, String seatName, AirplaneClass classType, float ticketCost, boolean baggage, int flightID, LocalDate departDate, LocalTime departTime, LocalDate arrivalDate, LocalTime arrivalTime, String arrivalLoc, String departLoc) {
        JFrame optionsFrame = new JFrame("Purchase Options for Seat ID: " + seatID);
        optionsFrame.setLayout(new FlowLayout());
        optionsFrame.setSize(300, 100);
    
        JLabel seatLabel = new JLabel("Do you want to buy flight to: " + arrivalLoc  + " From: " + departLoc + " At: " + departDate.toString() + " " + departTime.toString() + " on Flight ID: " + flightID + "SeatType: " + classType + "Ticket Cost: $" + ticketCost);
        JButton buyButton = new JButton("Buy");
        JButton noBuyButton = new JButton("No Buy");
    
        buyButton.addActionListener(e -> {
            optionsFrame.dispose();
            CreditCardPanel creditCardPanel = mainFrame.getCreditCardPanel();
            creditCardPanel.setSeatID(seatID);
            creditCardPanel.setAircraftID(aircraftID);
            creditCardPanel.setSeatName(seatName);
            creditCardPanel.setFlightID(flightID);
            creditCardPanel.setClassType(classType);
            creditCardPanel.setDepartDate(departDate);
            creditCardPanel.setDepartTime(departTime);
            creditCardPanel.setDepartLocation(departLoc);
            creditCardPanel.setArriveDate(arrivalDate);
            creditCardPanel.setArriveTime(arrivalTime);
            creditCardPanel.setArriveLocation(arrivalLoc);
            creditCardPanel.setCost(ticketCost);
            seatMapFrame.dispose();
            mainFrame.switchView("Payment");
        });
    
        noBuyButton.addActionListener(e -> {
            optionsFrame.dispose();
            mainFrame.switchViewBasedOnAccessLevel();
        });
    
        optionsFrame.add(seatLabel);
        optionsFrame.add(buyButton);
        optionsFrame.add(noBuyButton);
    
        optionsFrame.setVisible(true);
    }
}

