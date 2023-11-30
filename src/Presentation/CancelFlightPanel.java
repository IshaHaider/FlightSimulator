package src.Presentation;

import src.Domain.*;
import src.Controllers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;

public class CancelFlightPanel extends JPanel {
    private JTextField ticketNumField;
    private JTextField flightIDField;
    private JTextField seatIDField;

    SeatController seatController;

    public CancelFlightPanel(Gui mainFrame, SeatController seatController) {
        this.seatController = seatController;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Ticket Number Field
        JLabel ticketNumberLabel = new JLabel("Ticket Number:");
        ticketNumField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(ticketNumberLabel, gbc);
        gbc.gridx = 1;
        add(ticketNumField, gbc);

        // Flight ID Field
        JLabel flightIDLabel = new JLabel("Flight ID:");
        flightIDField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(flightIDLabel, gbc);
        gbc.gridx = 1;
        add(flightIDField, gbc);

        // Seat ID Field
        JLabel seatIDLabel = new JLabel("Seat ID:");
        seatIDField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(seatIDLabel, gbc);
        gbc.gridx = 1;
        add(seatIDField, gbc);

        // Cancel Flight Button
        JButton cancelFlightButton = new JButton("Cancel Flight");
        cancelFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmAndCancelFlight();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(cancelFlightButton, gbc);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> mainFrame.switchViewBasedOnAccessLevel());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(backButton, gbc);
    }

    private void confirmAndCancelFlight() {
        
        int ticketNumber = Integer.parseInt(ticketNumField.getText());
        int flightID = Integer.parseInt(flightIDField.getText());
        int seatID = Integer.parseInt(seatIDField.getText());

        // boolean confirmedCancel = seatController.cancelFlight(ticketNumber, flightID, seatID);
        
        try {
            seatController.cancelFlight(ticketNumber, flightID, seatID);
            ResultSet confirmTicketCancel =  seatController.getDBController().selectTableFromTwoAttributes("TICKET", "flightID", flightID, "seatID", seatID);
            if (!confirmTicketCancel.next()){//TICKET CANCEL CONFIRMED}

            JOptionPane.showMessageDialog(
                this,
                "Flight Cancellation Confirmed\n" +
                "Ticket Number: " + ticketNumField.getText() + "\n" +
                "FlightID: " + flightIDField.getText() + "\n" +
                "SeatID: " + seatIDField.getText() + "\n",
                "Cancellation Confirmation",
                
                JOptionPane.INFORMATION_MESSAGE
            );

        }} catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "ERROR CHECK INPUTS",
                "Error",
                JOptionPane.INFORMATION_MESSAGE
            );
        }

        // if (confirmedCancel){
        //     JOptionPane.showMessageDialog(
        //         this,
        //         "Flight Cancellation Confirmed\n" +
        //         "Ticket Number: " + ticketNumberField.getText() + "\n" +
        //         "FlightID: " + flightIDField.getText() + "\n" +
        //         "SeatID: " + seatIDField.getText() + "\n" +
        //         "Cancellation",
        //         JOptionPane.INFORMATION_MESSAGE
        //     );
        // } else {
        //         JOptionPane.showMessageDialog(
        //         this,
        //         "ERROR CHECK INPUTS",
        //         JOptionPane.INFORMATION_MESSAGE
        //     );
        // }
    }
}
