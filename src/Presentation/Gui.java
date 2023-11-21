package Presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;

public class Gui extends JFrame implements ActionListener, MouseListener {

    public void setupGUI() {
        
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new setupGUI().setVisible(true);
        });
    }
}