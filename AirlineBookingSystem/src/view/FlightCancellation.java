package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import model.*;
import model.users.*;

public class FlightCancellation {
    private JFrame frame;
    private JPanel panel;
    private int userID; // User ID for whom we want to display tickets

    public FlightCancellation(int userID) {
        this.userID = userID;
    }

    public void createUI() {
        frame = new JFrame();
        frame.setTitle("Flight Cancellation Screen");
        panel = new JPanel();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Tickets for User ID: " + userID);
        titleLabel.setBounds(30, 10, 300, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(titleLabel);

        // Retrieve and display the list of tickets for the user
        displayTickets();

        JButton cancelSelectedButton = new JButton("Cancel Selected Tickets");
        cancelSelectedButton.setBounds(30, 500, 200, 40);
        cancelSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle cancellation logic here
                handleCancellation();
            }
        });
        panel.add(cancelSelectedButton);

        frame.setVisible(true);
    }

    private void displayTickets() {
        ArrayList<Ticket> ticketList = getTicketsForUser(userID);

        int yOffset = 60;
        for (Ticket ticketInfo : ticketList) {
            JLabel ticketLabel = new JLabel(ticketInfo.toString()); //display flight ID
            ticketLabel.setBounds(30, yOffset, 500, 25);
            panel.add(ticketLabel);
            yOffset += 30;
        }
    }

    private ArrayList<Ticket> getTicketsForUser(int userID) {
        ArrayList<Ticket> userTicketList = new ArrayList<>();
        ArrayList<Ticket> ticketList = new FlightSystem().getTicketList();
        
        for (Ticket ticket : ticketList) {
            if (ticket.getCustomer().getUserID() == userID) {
                userTicketList.add(ticket);
            }
        }
    
        return userTicketList;
    }

    private void handleCancellation() {
        // Implement cancellation logic here
        // ...
        JOptionPane.showMessageDialog(null, "Tickets canceled successfully.");
        frame.dispose(); // Close the current frame after cancellation
    }
}
