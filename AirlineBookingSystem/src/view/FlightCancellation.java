package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.*;

public class FlightCancellation {
    private JFrame frame;
    private JPanel panel;
    private int userID; // User ID for whom we want to display tickets
    private JList<Ticket> ticketList; // JList to display tickets
    private DefaultListModel<Ticket> listModel; // DefaultListModel to manage tickets

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

        // Initialize the DefaultListModel
        listModel = new DefaultListModel<>();

        // Retrieve and display the list of tickets for the user
        displayTickets();

        // Create the JList
        ticketList = new JList<>(listModel);
        ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(ticketList);
        scrollPane.setBounds(30, 60, 500, 400);
        panel.add(scrollPane);

        JButton cancelSelectedButton = new JButton("Cancel Selected Ticket");
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
            JLabel ticketLabel = new JLabel(ticketInfo.toString()); // display flight ID
            ticketLabel.setBounds(30, yOffset, 500, 25);
            panel.add(ticketLabel);
            yOffset += 30;
        }
    }

    private ArrayList<Ticket> getTicketsForUser(int userID) {
        FlightSystem sys = FlightSystem.getInstance();

        ArrayList<Ticket> userTicketList = new ArrayList<>();
        ArrayList<Ticket> ticketList = sys.getTicketList();

        for (Ticket ticket : ticketList) {
            if (ticket.getCustomer().getUserID() == userID) {
                userTicketList.add(ticket);
            }
        }

        return userTicketList;
    }

    private Ticket getSelectedTicket() {
        return ticketList.getSelectedValue();
    }

    private void handleCancellation() {
        Ticket selectedTicket = getSelectedTicket();

        if (selectedTicket == null) {
            JOptionPane.showMessageDialog(null, "No ticket selected for cancellation.");
            return;
        }

        FlightSystem sys = FlightSystem.getInstance();
        String updateQuery = "UPDATE TICKETS SET IsCancelled = TRUE WHERE TicketID = " + selectedTicket.getTicketID();

        // Execute the SQL query to mark the ticket as cancelled
        try {
            PreparedStatement updateStmt = sys.getConnection().prepareStatement(updateQuery);
            updateStmt.executeUpdate();
            updateStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error canceling the ticket. Please try again.");
            return;
        }

        // Update flight system
        sys.synchronizeFlightSys();

        JOptionPane.showMessageDialog(null, "Ticket canceled successfully.");
        frame.dispose(); // Close the current frame after cancellation
    }
}
