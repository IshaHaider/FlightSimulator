package src.Presentation;

import javax.swing.*;
import javax.swing.text.*;

import src.Controllers.SeatController;
import src.Domain.Seat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditCardPanel extends JPanel {
    Gui mainFrame;
    SeatController seatController;

    private JTextField cardNumberField;
    private JTextField expiryDateField;
    private JTextField cvvField;

    private int flightNumber;
    private String seatID;
    private String seatClass;

    public CreditCardPanel(Gui mainFrame, SeatController seatController) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Credit Card Number Field
        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberField = new JTextField(24);
        // Set the document with a custom document filter
        // ((AbstractDocument) cardNumberField.getDocument()).setDocumentFilter(new DocumentFilter() {
        //     public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        //         String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
        //         if (newText.length() <= 24 && newText.matches("^[0-9]*$")) {
        //             super.replace(fb, offset, length, text, attrs);
        //         }
        //     }

        //     public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        //         String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
        //         if (newText.length() <= 24 && newText.matches("^[0-9]*$")) {
        //             super.insertString(fb, offset, string, attr);
        //         }
        //     }
        // });
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(cardNumberLabel, gbc);
        gbc.gridx = 1;
        add(cardNumberField, gbc);

        // Expiry Date Field
        JLabel expiryDateLabel = new JLabel("Expiry Date (MM/YY):");
        expiryDateField = new JTextField(4);
        // ((AbstractDocument) expiryDateField.getDocument()).setDocumentFilter(new DocumentFilter() {
        //     public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        //         String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
        //         if (newText.length() <= 4 && newText.matches("^[0-9]*$")) {
        //             super.replace(fb, offset, length, text, attrs);
        //         }
        //     }

        //     public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        //         String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
        //         if (newText.length() <= 4 && newText.matches("^[0-9]*$")) {
        //             super.insertString(fb, offset, string, attr);
        //         }
        //     }
        // });
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(expiryDateLabel, gbc);
        gbc.gridx = 1;
        add(expiryDateField, gbc);

        // CVV Field
        JLabel cvvLabel = new JLabel("CVV:");
        cvvField = new JTextField(3);
//         ((AbstractDocument) cvvField.getDocument()).setDocumentFilter(new DocumentFilter() {
        //     public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        //         String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
        //         if (newText.length() <= 3 && newText.matches("^[0-9]*$")) {
        //             super.replace(fb, offset, length, text, attrs);
        //         }
        //     }

        //     public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        //         String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
        //         if (newText.length() <= 3 && newText.matches("^[0-9]*$")) {
        //             super.insertString(fb, offset, string, attr);
        //         }
        //     }
        // });
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
                    JOptionPane.showMessageDialog(CreditCardPanel.this,
                            "Invalid Card Number. It must be 16 digits long.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                if (!expiryDate.matches("\\d{4}")) { // Matches MMYY format where MM is 01/12
                    JOptionPane.showMessageDialog(CreditCardPanel.this,
                            "Invalid Expiry Date. Format must be MMYY.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (!cvv.matches("\\d{3}")) {
                    JOptionPane.showMessageDialog(CreditCardPanel.this,
                            "Invalid CVV. It must be 3 digits long.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                // If all validations pass
                JOptionPane.showMessageDialog(CreditCardPanel.this,
                        "Purchase Confirmed!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                displayPurchaseSummary();
                
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(purchaseButton, gbc);

        // Cancel Button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> mainFrame.switchView("Search Flight"));
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(cancelButton, gbc);
    }

    // private void saveCreditCardInfo() {
    //     String cardNumber = cardNumberField.getText();
    //     String expiryDate = expiryDateField.getText();
    //     String cvv = cvvField.getText();

    //     System.out.println("Card Number: " + cardNumber + ", Expiry: " + expiryDate + ", CVV: " + cvv); //SAVE INTO DATABASE???
    // }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    private void displayPurchaseSummary() {
        JFrame summaryFrame = new JFrame("Purchase Summary");
        summaryFrame.setLayout(new GridLayout(0, 1));
        summaryFrame.add(new JLabel("Flight Number: " + flightNumber));
        summaryFrame.add(new JLabel("Seat ID: " + seatID));
        summaryFrame.add(new JLabel("Seat Class: " + seatClass));

        summaryFrame.pack();
        summaryFrame.setVisible(true);
        mainFrame.switchView("Home");
    }
}
