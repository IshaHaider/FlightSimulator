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

        // Cancel Flight Button
        JButton cancelFlightButton = new JButton("Cancel Flight");
        cancelFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmAndCancelFlight();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(cancelFlightButton, gbc);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> mainFrame.switchViewBasedOnAccessLevel());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(backButton, gbc);
    }

    private void confirmAndCancelFlight() {
        int ticketNumber = Integer.parseInt(ticketNumField.getText());
        try {
            String returnStatement = seatController.cancelFlight(ticketNumber);
            JOptionPane.showMessageDialog(
                this,
                returnStatement,
                "Cancellation Confirmation",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "ERROR CHECK INPUTS",
                "Error",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
}