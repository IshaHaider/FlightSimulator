package src.Presentation;

import src.Domain.*;
import src.Controllers.*;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class CreditCardPanel extends JPanel {
    private Gui mainFrame;
    private SeatController seatController;
    private PromotionController promotionController;

    private JTextField cardNumberField;
    private JTextField expiryDateField;
    private JTextField cvvField;

    private int seatID;
    private int flightID;
    private String seatName;
    private int aircraftID;
    private AirplaneClass classType;
    private LocalDate departDate;
    private LocalTime departTime;
    private String departLocation;
    private LocalDate arriveDate;
    private LocalTime arriveTime;
    private String arriveLocation;
    private float cost;
    // private float newCost;
    // private float percentageDiscount;

    // private GuestUser tmpGuesUser;

    public CreditCardPanel(Gui mainFrame, SeatController seatController, PromotionController promotionController) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Credit Card Number Field
        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberField = new JTextField(24);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(cardNumberLabel, gbc);
        gbc.gridx = 1;
        add(cardNumberField, gbc);

        // Expiry Date Field
        JLabel expiryDateLabel = new JLabel("Expiry Date (MM/YY):");
        expiryDateField = new JTextField(4);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(expiryDateLabel, gbc);
        gbc.gridx = 1;
        add(expiryDateField, gbc);

        // CVV Field
        JLabel cvvLabel = new JLabel("CVV:");
        cvvField = new JTextField(3);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(cvvLabel, gbc);
        gbc.gridx = 1;
        add(cvvField, gbc);

        // Purchase Button
        JButton purchaseButton = new JButton("Purchase");
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // saveCreditCardInfo();
                String cardNumber = cardNumberField.getText();
                String expiryDate = expiryDateField.getText();
                String cvv = cvvField.getText();

                if (!cardNumber.matches("\\d{16}")) {
                    JOptionPane.showMessageDialog(CreditCardPanel.this,"Invalid Card Number. It must be 16 digits long.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                if (!expiryDate.matches("\\d{4}")) { // Matches MMYY format where MM is 01/12
                    JOptionPane.showMessageDialog(CreditCardPanel.this,"Invalid Expiry Date. Format must be MMYY.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (!cvv.matches("\\d{3}")) {
                    JOptionPane.showMessageDialog(CreditCardPanel.this,"Invalid CVV. It must be 3 digits long.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                // If all validations pass
                UserSession userSession = UserSession.getInstance();
                if (userSession.isLoggedIn()) {
                    seatController.purchaseSeat(flightID, aircraftID, userSession.getUserID() ,seatID);
                    JOptionPane.showMessageDialog(CreditCardPanel.this,"Purchase Confirmed!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    displayPurchaseSummary();
                }
                else {
                    isGuestUser();
                }

            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(purchaseButton, gbc);

        // Cancel Button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> mainFrame.switchViewBasedOnAccessLevel());
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(cancelButton, gbc);
    }

    private void displayPurchaseSummary() {
        JFrame summaryFrame = new JFrame("Purchase Summary");
        summaryFrame.setLayout(new GridLayout(0, 1));
        summaryFrame.setSize(800, 800);
        // Promotions
        // LocalDate today = LocalDate.now();
        // ArrayList<Promotions> currentPromotions = promotionController.getCurrentPromotions();
        // for (Promotions promotion : currentPromotions) {
        //     if (promotion.getStartDate().isBefore(today) && promotion.getEndDate().isAfter(today)) {
        //         percentageDiscount = Float.parseFloat(promotion.getDiscount().replace("%", "")) / 100;
        //         newCost = cost * (1 - percentageDiscount);
        //     }
        // }

        // Displaying all the required information
        summaryFrame.add(new JLabel("Flight Number: " + flightID));
        summaryFrame.add(new JLabel("Seat ID: " + seatID));
        summaryFrame.add(new JLabel("Seat Class: " + classType));
        summaryFrame.add(new JLabel("Seat Name: " + seatName));
        summaryFrame.add(new JLabel("Aircraft ID: " + aircraftID));
        summaryFrame.add(new JLabel("Departure Date: " + departDate));
        summaryFrame.add(new JLabel("Departure Time: " + departTime));
        summaryFrame.add(new JLabel("Departure Location: " + departLocation));
        summaryFrame.add(new JLabel("Arrival Date: " + arriveDate));
        summaryFrame.add(new JLabel("Arrival Time: " + arriveTime));
        summaryFrame.add(new JLabel("Arrival Location: " + arriveLocation));
        summaryFrame.add(new JLabel("Cost: $" + cost));

        summaryFrame.pack();
        summaryFrame.setVisible(true);
        summaryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // maybe dont need this line
        mainFrame.switchViewBasedOnAccessLevel();;
    }

    private void isGuestUser() {
        // Create a new frame for guest user information
        JFrame guestFrame = new JFrame("Guest User Information");
        guestFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        guestFrame.setSize(800,600);
        
        // First Name Field
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField(15);
        gbc.gridx = 0; gbc.gridy = 0;
        guestFrame.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        guestFrame.add(firstNameField, gbc);

        // Last Name Field
        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField(15);
        gbc.gridx = 0; gbc.gridy = 1;
        guestFrame.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        guestFrame.add(lastNameField, gbc);

        // Address Field
        JLabel addressLabel = new JLabel("Address (Ex: 123 John Laruie):");
        JTextField addressField = new JTextField(15);
        gbc.gridx = 0; gbc.gridy = 2;
        guestFrame.add(addressLabel, gbc);
        gbc.gridx = 1;
        guestFrame.add(addressField, gbc);

        // Email Field
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(15);
        gbc.gridx = 0; gbc.gridy = 3;
        guestFrame.add(emailLabel, gbc);
        gbc.gridx = 1;
        guestFrame.add(emailField, gbc);

        // Birthdate Field
        JLabel birthdateLabel = new JLabel("Birthdate (yyyy-MM-dd):");
        JTextField birthdateField = new JTextField(15);
        gbc.gridx = 0; gbc.gridy = 4;
        guestFrame.add(birthdateLabel, gbc);
        gbc.gridx = 1;
        guestFrame.add(birthdateField, gbc);

        // Phone Number Field
        JLabel phoneNumberLabel = new JLabel("Phone Number (###-###-####):");
        JTextField phoneNumberField = new JTextField(15);
        gbc.gridx = 0; gbc.gridy = 5;
        guestFrame.add(phoneNumberLabel, gbc);
        gbc.gridx = 1;
        guestFrame.add(phoneNumberField, gbc);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {

            String fName = firstNameField.getText();
            String lName = lastNameField.getText();
            String addr = addressField.getText();
            String email = emailField.getText();
            LocalDate bday = LocalDate.parse(birthdateField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String phoneNum = phoneNumberField.getText();

            Name guestUserName = new Name(fName, lName);
            Address guestUserAddress = new Address(addr);

            GuestUser tmpGuesUser = new GuestUser(guestUserName, guestUserAddress, email, bday ,phoneNum );
            seatController.purchaseSeat(flightID, aircraftID, tmpGuesUser ,seatID); 
            JOptionPane.showMessageDialog(CreditCardPanel.this,"Purchase Confirmed!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            displayPurchaseSummary();
            guestFrame.dispose();
        });
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        guestFrame.add(submitButton, gbc);

        // Finalize and display the frame
        guestFrame.pack();
        guestFrame.setVisible(true);
        guestFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public void setAircraftID(int aircraftID) {
        this.aircraftID = aircraftID;
    }

    public void setClassType(AirplaneClass classType) {
        this.classType = classType;
    }

    public void setDepartDate(LocalDate departDate) {
        this.departDate = departDate;
    }

    public void setDepartTime(LocalTime departTime) {
        this.departTime = departTime;
    }

    public void setDepartLocation(String departLocation) {
        this.departLocation = departLocation;
    }

    public void setArriveDate(LocalDate arriveDate) {
        this.arriveDate = arriveDate;
    }

    public void setArriveTime(LocalTime arriveTime) {
        this.arriveTime = arriveTime;
    }

    public void setArriveLocation(String arriveLocation) {
        this.arriveLocation = arriveLocation;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

}
