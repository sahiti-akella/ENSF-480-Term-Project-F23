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

public class RegisterNewUser {
    private JFrame frame;
    private JPanel panel;
    private String userType;
    private FlightSystem sys;

    // MySQL Connection Variables
    private Connection dbConnect;

    public RegisterNewUser(String userType) {
        this.userType = userType;
        this.sys = FlightSystem.getInstance();
        createConnection();
    }

    public void createUI() {
        frame = new JFrame();
        frame.setTitle("Register New User");
        panel = new JPanel();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(new GridLayout(7, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel creditCardLabel = new JLabel("Credit Card Number:");
        JTextField creditCardField = new JTextField();

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle registration logic here
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String address = addressField.getText();
                String email = emailField.getText();
                int creditCardNumber = Integer.parseInt(creditCardField.getText());

                // Validate the input fields (add your validation logic)

                // Create the user in the database (add your database logic)
                insertUser(username, password, firstName, lastName, address, email, userType, false, creditCardNumber);

                // Close the registration window
                frame.dispose();
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(creditCardLabel);
        panel.add(creditCardField);
        panel.add(registerButton);

        frame.setVisible(true);
    }

    // SQL Helpers

    /**
     * This method creates a connection to the SQL database.
     */
    public void createConnection() {
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
            dbConnect = DriverManager.getConnection(url, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(String userName, String password, String firstName,
                                  String lastName, String address, String email,
                                  String accountType, boolean isRegistered, int creditCardNumber) {

        try {
            // Insert into USERS table
            String userInsertQuery = "INSERT INTO USERS (UserName, UserPassword, FirstName, LastName, Address, Email, AccountType, isRegistered, CreditCardNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement userInsertStmt = dbConnect.prepareStatement(userInsertQuery)) {
                userInsertStmt.setString(1, userName);
                userInsertStmt.setString(2, password);
                userInsertStmt.setString(3, firstName);
                userInsertStmt.setString(4, lastName);
                userInsertStmt.setString(5, address);
                userInsertStmt.setString(6, email);
                userInsertStmt.setString(7, accountType);
                userInsertStmt.setBoolean(8, isRegistered);
                userInsertStmt.setInt(9, creditCardNumber);
    
                userInsertStmt.executeUpdate();
            }
    
            // Insert into specific user type table (e.g., CUSTOMERS, TOURISM_AGENTS, etc.)
            String userTypeInsertQuery = "INSERT INTO " + getUserTypeTableName(accountType) +
                    " (UserID, UserName, UserPassword, FirstName, LastName" +
                    (accountType.equals("customer") ? ", Address, Email" : "") + ") " +
                    "SELECT UserID, UserName, UserPassword, FirstName, LastName" +
                    (accountType.equals("customer") ? ", Address, Email" : "") +
                    " FROM USERS WHERE AccountType = ?";
            try (PreparedStatement userTypeInsertStmt = dbConnect.prepareStatement(userTypeInsertQuery)) {
                userTypeInsertStmt.setString(1, accountType);
                userTypeInsertStmt.executeUpdate();
            }
    
            // Display success message
            JOptionPane.showMessageDialog(null, "User created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Refresh Flight system after updates
            sys.synchronizeFlightSys();
    
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error creating user. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String getUserTypeTableName(String accountType) {
        switch (accountType) {
            case "customer":
                return "CUSTOMERS";
            case "tourism-agent":
                return "TOURISM_AGENTS";
            case "flight-attendant":
                return "FLIGHT_ATTENDANTS";
            case "airline-agent":
                return "AIRLINE_AGENTS";
            case "admin":
                return "SYSTEM_ADMINS";
            default:
                throw new IllegalArgumentException("Invalid account type: " + accountType);
        }
    }


}
