package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Properties;

public class SystemAdminGUI {

    private Connection connection;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField addressField;

    public SystemAdminGUI() {
        createUI();
    }

    public void createUI() {
        initializeDatabase();
        JFrame frame = new JFrame();
        frame.setTitle("Admin Welcome Page");
        JPanel panel = new JPanel();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setBounds(30, 10, 200, 40);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(welcomeLabel);

        JLabel promptLabel = new JLabel("Please input your information:");
        promptLabel.setBounds(30, 50, 250, 25);
        panel.add(promptLabel);

        collectAdminInfo(panel);

        JLabel errorLabel = new JLabel("");
        errorLabel.setBounds(30, 190, 300, 25);
        errorLabel.setForeground(Color.RED);
        panel.add(errorLabel);

        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(80, 220, 180, 40);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performAdminAction();
            }
        });
        panel.add(continueButton);

        frame.setVisible(true);
    }

    private void collectAdminInfo(JPanel panel) {
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(30, 80, 80, 25);
        panel.add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(110, 80, 200, 25);
        panel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(30, 110, 80, 25);
        panel.add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(110, 110, 200, 25);
        panel.add(lastNameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 140, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(110, 140, 200, 25);
        panel.add(emailField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(30, 170, 80, 25);
        panel.add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(110, 170, 200, 25);
        panel.add(addressField);
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

    private void performAdminAction() {
    }

    private void addFlight(String origin, String destination, String departureDate, int aircraftID) {
        // Display a dialog to get flight information from the admin
        JTextField originField = new JTextField();
        JTextField destinationField = new JTextField();
        JTextField departureDateField = new JTextField();
        JTextField aircraftIDField = new JTextField();

        Object[] message = {
                "Origin:", originField,
                "Destination:", destinationField,
                "Departure Date:", departureDateField,
                "Aircraft ID:", aircraftIDField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Enter Flight Information", JOptionPane.OK_CANCEL_OPTION);

        // Check if the admin clicked "OK"
        if (option == JOptionPane.OK_OPTION) {
            // Get the entered values
            String newOrigin = originField.getText();
            String newDestination = destinationField.getText();
            String newDepartureDate = departureDateField.getText();
            int newAircraftID = Integer.parseInt(aircraftIDField.getText());

            // Use the entered values to insert a new flight into the database
            try {
                String sql = "INSERT INTO FLIGHTS (Origin, Destination, DepartureDate, AircraftID) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, newOrigin);
                    preparedStatement.setString(2, newDestination);
                    preparedStatement.setString(3, newDepartureDate);
                    preparedStatement.setInt(4, newAircraftID);

                    // Execute the query
                    preparedStatement.executeUpdate();

                    // Display a success message
                    JOptionPane.showMessageDialog(null, "Flight added successfully.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception (e.g., display an error message)
                JOptionPane.showMessageDialog(null, "Error adding flight. Please check the input and try again.");
            }
        }
    }

    private void addCrew(String crewName, String crewRole) {
        // Implement logic to add a crew member to the database
        try {
            String sql = "INSERT INTO CREW_MEMBERS (CrewName, CrewRole) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, crewName);
                preparedStatement.setString(2, crewRole);

                // Execute the update
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Display a success message
                    JOptionPane.showMessageDialog(null, "Crew member added successfully.");
                } else {
                    // Display a message if no rows were affected (insert failed)
                    JOptionPane.showMessageDialog(null, "Failed to add crew member. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions
            JOptionPane.showMessageDialog(null, "Error adding crew member. Please check the input and try again.");
        }
    }

    private void addAircraft(String aircraftType) {
        // Implement logic to add an aircraft to the database
        try {
            String sql = "INSERT INTO AIRCRAFTS (AircraftType) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, aircraftType);

                // Execute the update
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Display a success message
                    JOptionPane.showMessageDialog(null, "Aircraft added successfully.");
                } else {
                    // Display a message if no rows were affected (insert failed)
                    JOptionPane.showMessageDialog(null, "Failed to add aircraft. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions
            JOptionPane.showMessageDialog(null, "Error adding aircraft. Please check the input and try again.");
        }
    }

    private void addFlightDestination(String destination) {
        // Implement logic to add a new flight destination to the database
        try {
            String sql = "INSERT INTO FLIGHT_DESTINATIONS (Destination) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, destination);

                // Execute the update
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Display a success message
                    JOptionPane.showMessageDialog(null, "Flight destination added successfully.");
                } else {
                    // Display a message if no rows were affected (insert failed)
                    JOptionPane.showMessageDialog(null, "Failed to add flight destination. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions
            JOptionPane.showMessageDialog(null, "Error adding flight destination. Please check the input and try again.");
        }
    }

    private void modifyFlightInformation(int flightID, String newOrigin, String newDestination, String newDepartureDate, int newAircraftID) {
        // Implement logic to modify flight information in the database
        try {
            String sql = "UPDATE FLIGHTS SET Origin=?, Destination=?, DepartureDate=?, AircraftID=? WHERE FlightID=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, newOrigin);
                preparedStatement.setString(2, newDestination);
                preparedStatement.setString(3, newDepartureDate);
                preparedStatement.setInt(4, newAircraftID);
                preparedStatement.setInt(5, flightID);

                // Execute the update
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Display a success message
                    JOptionPane.showMessageDialog(null, "Flight information modified successfully.");
                } else {
                    // Display a message if no rows were affected (update failed)
                    JOptionPane.showMessageDialog(null, "Failed to modify flight information. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions
            JOptionPane.showMessageDialog(null, "Error modifying flight information. Please check the input and try again.");
        }
    }

    private void printListOfUsers() {
        try {
            String sql = "SELECT UserName FROM USERS";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            StringBuilder userList = new StringBuilder("List of users:\n");

            while (resultSet.next()) {
                String userName = resultSet.getString("UserName");
                userList.append(userName).append("\n");
            }

            // Display the list in a message dialog
            JOptionPane.showMessageDialog(null, userList.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions
            JOptionPane.showMessageDialog(null, "Error retrieving list of users. Please try again.");
        }
    }

    public static void main(String[] args) {
        new SystemAdminGUI().createUI();
    }
}
