package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelFlightPanel extends JPanel {
    private JTextField flightNumberField;
    private JTextField fromField;
    private JTextField toField;
    private JTextField timeField;

    public CancelFlightPanel(Gui mainFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Flight Number Field
        JLabel flightNumberLabel = new JLabel("Flight Number:");
        flightNumberField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(flightNumberLabel, gbc);
        gbc.gridx = 1;
        add(flightNumberField, gbc);

        // From Field
        JLabel fromLabel = new JLabel("From:");
        fromField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(fromLabel, gbc);
        gbc.gridx = 1;
        add(fromField, gbc);

        // To Field
        JLabel toLabel = new JLabel("To:");
        toField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(toLabel, gbc);
        gbc.gridx = 1;
        add(toField, gbc);

        // Time of Flight Field
        JLabel timeLabel = new JLabel("Time of Flight:");
        timeField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(timeLabel, gbc);
        gbc.gridx = 1;
        add(timeField, gbc);

        // Cancel Flight Button
        JButton cancelFlightButton = new JButton("Cancel Flight");
        cancelFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelFlight();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(cancelFlightButton, gbc);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> mainFrame.switchView("Home"));
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(backButton, gbc);
    }

    private void cancelFlight() {
        String flightNumber = flightNumberField.getText();
        String from = fromField.getText();
        String to = toField.getText();
        String time = timeField.getText();

        // Confirmation dialog
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to cancel the flight?",
            "Confirm Cancellation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        // Check if user confirmed the cancellation
        if (confirm == JOptionPane.YES_OPTION) {
            // For demonstration purposes, printing to the console
            System.out.println("Cancelling Flight: " + flightNumber + ", From: " + from + ", To: " + to + ", Time: " + time);
            // Here you would typically handle the cancellation logic

            // Show flight cancellation confirmation
            JOptionPane.showMessageDialog(
                    this,
                    "Flight Cancellation Confirmed",
                    "Cancellation",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
