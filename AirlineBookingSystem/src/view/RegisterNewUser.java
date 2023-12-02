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
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel titleLabel = new JLabel("User Registration");
        titleLabel.setBounds(150, 10, 200, 25);
        panel.add(titleLabel);

        // Create labels and text fields
        createLabelAndTextField("Username:", 30);
        createLabelAndTextField("Password:", 60);
        createLabelAndTextField("First Name:", 90);
        createLabelAndTextField("Last Name:", 120);
        createLabelAndTextField("Address:", 150);
        createLabelAndTextField("Email:", 180);
        //createLabelAndTextField("Credit Card Number:", 210);

        // Create Register button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 240, 100, 30);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle registration logic here
                // Retrieve data from text fields and perform registration
                String username = getFieldText("Username:");
                String password = getFieldText("Password:");
                String firstName = getFieldText("First Name:");
                String lastName = getFieldText("Last Name:");
                String address = getFieldText("Address:");
                String email = getFieldText("Email:");
                //int creditCardNumber = Integer.parseInt(getFieldText("Credit Card Number:"));

                // Validate the input fields (add your validation logic)

                // Create the user in the database (add your database logic)
                insertUser(username, password, firstName, lastName, address, email, userType, false);

                // Close the registration window
                frame.dispose();
            }
        });
        panel.add(registerButton);

        frame.setVisible(true);
    }

    private void createLabelAndTextField(String label, int yPos) {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(30, yPos, 150, 25);
        panel.add(jLabel);

        JTextField textField = new JTextField();
        textField.setBounds(180, yPos, 150, 25);
        panel.add(textField);
    }

    private String getFieldText(String label) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel && ((JLabel) component).getText().equals(label)) {
                int index = panel.getComponentZOrder(component);
                if (index + 1 < components.length && components[index + 1] instanceof JTextField) {
                    return ((JTextField) components[index + 1]).getText();
                }
            }
        }
        return "";
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
                                  String accountType, boolean isRegistered) {

        try {
            // Insert into USERS table
            String userInsertQuery = "INSERT INTO USERS (UserName, UserPassword, FirstName, LastName, Address, Email, AccountType, isRegistered) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement userInsertStmt = dbConnect.prepareStatement(userInsertQuery)) {
                userInsertStmt.setString(1, userName);
                userInsertStmt.setString(2, password);
                userInsertStmt.setString(3, firstName);
                userInsertStmt.setString(4, lastName);
                userInsertStmt.setString(5, address);
                userInsertStmt.setString(6, email);
                userInsertStmt.setString(7, accountType);
                userInsertStmt.setBoolean(8, isRegistered);
                //userInsertStmt.setInt(9, creditCardNumber);
    
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
