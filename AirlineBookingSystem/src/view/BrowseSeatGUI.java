package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BrowseSeatGUI {
    private JFrame frame;
    private JPanel seatPanel;
    private String selectedFlight;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/FRWA";
    private static final String USER = "root";
    private static final String PASSWORD = "sahi2004";
    private static final int SEATS_PER_FLIGHT = 12;

    public BrowseSeatGUI(String selectedFlight) {
        this.selectedFlight = selectedFlight;
    }

    public void createUI() {
        frame = new JFrame();
        frame.setTitle("Browse Seats - " + selectedFlight);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        seatPanel = new JPanel();
        seatPanel.setLayout(new GridLayout(3, 4)); // Change the dimensions as needed

        // Calculate the starting seat number based on the flight
        int startingSeatNumber = (getFlightID(selectedFlight) - 1) * SEATS_PER_FLIGHT + 1;

        // Create seats
        for (int row = 1; row <= 3; row++) {
            String seatType;
            if (row == 1) {
                seatType = "Ordinary ($800)";
            } else if (row == 2) {
                seatType = "Comfort ($1150)";
            } else {
                seatType = "Business ($1600)";
            }

            for (int col = 1; col <= 4; col++) {
                int seatNumber = startingSeatNumber + (row - 1) * 4 + col - 1;
                JButton seatButton = createSeatButton(seatType, seatNumber);
                seatPanel.add(seatButton);
            }
        }

        frame.add(seatPanel);
        frame.setVisible(true);
    }

    private JButton createSeatButton(String seatType, int seatNumber) {
        JButton seatButton = new JButton(seatType + " - Seat " + seatNumber);
        seatButton.addActionListener(new SeatClickListener());

        // Check seat availability and enable/disable the button accordingly
        if (!isSeatAvailable(seatNumber)) {
            seatButton.setEnabled(false);
        }

        return seatButton;
    }

    private boolean isSeatAvailable(int seatNumber) {
        try {
            // Query the database to check seat availability for the selected flight
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            String query = "SELECT IsAvailable FROM SEATS WHERE FlightID = ? AND SeatID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, getFlightID(selectedFlight));
            statement.setInt(2, seatNumber);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int isAvailable = resultSet.getInt("IsAvailable");
                return isAvailable == 1; // Assuming 1 represents available and 0 represents not available
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Default to not available in case of an error
    }

    private int getFlightID(String selectedFlight) {
        // Query the database to get the FlightID for the selected flight
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            String query = "SELECT FlightID FROM FLIGHTS WHERE CONCAT(Origin, ' -> ', Destination, ' : ', DepartureDate) = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, selectedFlight);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("FlightID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 in case of an error
    }

    private class SeatClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton seatButton = (JButton) e.getSource();
            String seatName = seatButton.getText();
            frame.dispose(); // Close the seat selection frame
            openInsuranceSelectionFrame(seatName);
        }
    }

    private void openInsuranceSelectionFrame(String selectedSeat) {
        SelectInsuranceGUI insuranceGUI = new SelectInsuranceGUI(selectedSeat);
        insuranceGUI.createUI();
    }
}