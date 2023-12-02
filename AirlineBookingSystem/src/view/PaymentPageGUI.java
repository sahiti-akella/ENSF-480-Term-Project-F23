package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import model.Payment;
import model.Booking;

public class PaymentPageGUI {
    private JFrame frame;
    private Booking booking;
    private Connection connection;
    private FlightSystem sys;

    public PaymentPageGUI(Booking booking) {
        this.booking = booking;
        this.sys = FlightSystem.getInstance();
    }

    public void createUI() {
        frame = new JFrame();
        frame.setTitle("Payment Page");
        JPanel panel = new JPanel();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel promptLabel = new JLabel("Enter Payment Details:");
        promptLabel.setBounds(30, 10, 300, 40);
        panel.add(promptLabel);

        JLabel firstNameLabel = new JLabel("Card Holder First Name:");
        firstNameLabel.setBounds(30, 50, 200, 25);
        panel.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField .setBounds(180, 50, 150, 25);
        panel.add(firstNameField );

        JLabel lastNameLabel = new JLabel("Card Holder Last Name:");
        lastNameLabel.setBounds(30, 80, 150, 25);
        panel.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField .setBounds(180, 80, 150, 25);
        panel.add(lastNameField );

        JLabel numberLabel = new JLabel("Card Number:");
        numberLabel.setBounds(30, 110, 150, 25);
        panel.add(numberLabel);

        JTextField numberField = new JTextField();
        numberField.setBounds(180, 110,150, 25);
        panel.add(numberField);

        JLabel expiryLabel = new JLabel("Expiry Date:");
        expiryLabel.setBounds(30, 140, 200, 25);
        panel.add(expiryLabel);

        JTextField expiryField = new JTextField();
        expiryField.setBounds(180, 140, 150, 25);
        panel.add(expiryField);

        JLabel cvvLabel = new JLabel("CVV:");
        cvvLabel.setBounds(30, 170, 150, 25);
        panel.add(cvvLabel);

        JTextField cvvField = new JTextField();
        cvvField.setBounds(180, 170, 150, 25);
        panel.add(cvvField);

        JButton proceedButton = new JButton("Confirm Payment");
        proceedButton.setBounds(100, 210, 150, 40);
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardHolderFirstName = firstNameField.getText();
                String cardHolderLastName = lastNameField.getText();
                String cardNumber = numberField.getText();
                String expiryDate = expiryField.getText();
                String cvvString = cvvField.getText();
        
                if (cardHolderFirstName.isEmpty() || cardHolderLastName.isEmpty() ||
                    cardNumber.isEmpty() || expiryDate.isEmpty() || cvvString.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please complete the payment form.");
                    return; 
                }
        
                int cvv;
                try {
                    cvv = Integer.parseInt(cvvString);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid CVV format. Please enter a 3-digit CVV.");
                    return; 
                }
    
                Payment payment = new Payment(cardHolderFirstName, cardHolderLastName, cardNumber, expiryDate, cvv);
        
                if (payment.isValid()) {
                    JOptionPane.showMessageDialog(frame, "Payment Successful!");
                    frame.dispose(); 

                    saveToDatabase(booking);
                    updateSeatAvailability(booking.getSelectedSeat());
                    
                    // synchronize flight system with database
                    sys.synchronizeFlightSys();
                    
                    openSuccessMessageFrame();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid payment information. Please check and try again.\n" +
                            "Please enter a valid expiry date in the format dd/mm/yyyy, a 16-digit Card Number, and a 3-digit CVV.");
                }

            }
        });
        
        panel.add(proceedButton);

        frame.setVisible(true);
    }

    private void displayBooking(Booking booking) {
        BookingGUI bookingFrame = new BookingGUI(booking);
        bookingFrame.setVisible(true);
    }

    private void openSuccessMessageFrame() {
        JFrame successFrame = new JFrame();
        successFrame.setTitle("Success!");
        JPanel successPanel = new JPanel();
        successFrame.setSize(800, 600);
        successFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        successFrame.add(successPanel);

        successPanel.setLayout(null);

        JLabel successLabel = new JLabel("Your trip has successfully been booked!");
        successLabel.setBounds(30, 10, 400, 40);
        successPanel.add(successLabel);

        JButton viewBookingButton = new JButton("View Booking Details");
        viewBookingButton.setBounds(30, 100, 200, 40);
        viewBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBooking(booking);
                successFrame.dispose(); 
            }
        });
        successPanel.add(viewBookingButton);

        successFrame.setVisible(true);
    }

    private void initializeDatabase() {
        // Load database properties
        Properties properties = DBUtils.loadProperties("AirlineBookingSystem/config/database.properties");
        if (properties == null) {
            // Handle the error appropriately
            return;
        }

        String url = properties.getProperty("db.url");
        String dbUsername = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

       private void saveToDatabase(Booking booking) {
        initializeDatabase();
        if (connection == null) {
            // Handle the error appropriately
            return;
        }

        try {
            // Insert into BOOKINGS table
            String insertQuery = "INSERT INTO BOOKINGS (SelectedSeat, InsuranceSelected, SeatPrice, Origin, Destination, DepartureDate) " +
                                "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, booking.getSelectedSeat());
                preparedStatement.setBoolean(2, booking.hasInsurance());
                preparedStatement.setDouble(3, booking.getSeatPrice());
                preparedStatement.setString(4, booking.getOrigin());
                preparedStatement.setString(5, booking.getDestination());
                preparedStatement.setString(6, booking.getDepartureDate()); 
            }     
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    private void updateSeatAvailability(String selectedSeat) {
        initializeDatabase();
        if (connection == null) {
            return;
        }
    
        try {
            String seatNumberString = selectedSeat.replaceAll("[^0-9]", "");
            int seatID = Integer.parseInt(seatNumberString);
    
            String updateQuery = "UPDATE SEATS SET IsAvailable = false WHERE SeatID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setInt(1, seatID);
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        } 
    }
    
}
