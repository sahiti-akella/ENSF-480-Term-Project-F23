package view;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Properties;

public class SystemAdminGUI {

    private Connection connection;
    private FlightSystem sys = FlightSystem.getInstance();



    public void createUI() {
        initializeDatabase();

        JFrame frame = new JFrame();

        frame.setTitle("Admin Welcome Page");
        JPanel panel = new JPanel();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(Box.createVerticalStrut(50));
        JLabel welcomeLabel = new JLabel("Welcome to Admin Page!");
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 250));
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(welcomeLabel);
        panel.add(Box.createVerticalStrut(50));
        String[] adminActions = {
                "Add Flight",
                "Add Crew",
                "Add Aircraft",
                "Add Flight Destination",
                "Modify Flight Information",
                "Print List of Users"
        };

        JComboBox<String> adminActionsDropdown = new JComboBox<>(adminActions);
        adminActionsDropdown.setBorder(BorderFactory.createEmptyBorder(100, 200, 100, 200));
        panel.add(adminActionsDropdown);


        panel.add(Box.createVerticalStrut(50));
        JButton continueButton = new JButton("Continue");
        panel.add(continueButton);
        continueButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        panel.add(Box.createVerticalStrut(200));

        continueButton.addActionListener(e -> {
            String selectedAction = (String) adminActionsDropdown.getSelectedItem();
            performAdminAction(selectedAction);
        });

        frame.setVisible(true);
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

    private void performAdminAction(String selectedAction) {
        switch (selectedAction) {
            case "Add Flight":
                addFlight();
                break;
            case "Add Crew":
                addCrew();
                break;
            case "Add Aircraft":
                addAircraft();
                break;
            case "Add Flight Destination":
                addFlightDestination();
                break;
            case "Modify Flight Information":
                modifyFlightInformation();
                break;
            case "Print List of Users":
                printListOfUsers();
                break;
            default:
                // Handle the case where an unknown action is selected
                JOptionPane.showMessageDialog(null, "Invalid action selected.");
        }
    }
    private boolean isEmpty(String value) {
        return value.trim().isEmpty();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void addFlight() {
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
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, newOrigin);
                    preparedStatement.setString(2, newDestination);
                    preparedStatement.setString(3, newDepartureDate);
                    preparedStatement.setInt(4, newAircraftID);

                    // Execute the query
                    preparedStatement.executeUpdate();

                    // Get the generated flight ID
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    int flightID = -1;
                    if (generatedKeys.next()) {
                        flightID = generatedKeys.getInt(1);
                    }

                    // Insert seat information for the new flight
                    insertSeatsForFlight(flightID);

                    // Display a success message
                    JOptionPane.showMessageDialog(null, "Flight added successfully.");

                    // Reset FlightSys
                    sys.synchronizeFlightSys();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception (e.g., display an error message)
                JOptionPane.showMessageDialog(null, "Error adding flight. Please check the input and try again.");
            }
        }
    }

    private void insertSeatsForFlight(int flightID) {
        try {
            String sql = "INSERT INTO SEATS (FlightID, LayoutID, IsAvailable) VALUES (?, ?, 1)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Assuming LayoutID 1 for Ordinary seats, 2 for Comfort, 3 for Business-Class
                for (int layoutID = 1; layoutID <= 3; layoutID++) {
                    for (int i=0; i<4; i++){
                        preparedStatement.setInt(1, flightID);
                        preparedStatement.setInt(2, layoutID);
                        preparedStatement.addBatch();
                    }   
                }
                // Execute the batch update
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log error, display an error message)
        }
    }

    private void addCrew() {
        // Display a dialog to get crew information from the admin
        JTextField crewNameField = new JTextField();
        JTextField crewRoleField = new JTextField();

        Object[] message = {
                "Crew Name:", crewNameField,
                "Crew Role:", crewRoleField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Enter Crew Information", JOptionPane.OK_CANCEL_OPTION);

        // Check if the admin clicked "OK"
        if (option == JOptionPane.OK_OPTION) {
            // Get the entered values
            String newCrewName = crewNameField.getText();
            String newCrewRole = crewRoleField.getText();

            // Use the entered values to insert a new crew member into the database
            try {
                String sql = "INSERT INTO CREW_MEMBERS (CrewName, CrewRole) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, newCrewName);
                    preparedStatement.setString(2, newCrewRole);

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
    }

    private void addAircraft() {
        // Display a dialog to get aircraft information from the admin
        JTextField aircraftTypeField = new JTextField();

        Object[] message = {
                "Aircraft Type:", aircraftTypeField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Enter Aircraft Information", JOptionPane.OK_CANCEL_OPTION);

        // Check if the admin clicked "OK"
        if (option == JOptionPane.OK_OPTION) {
            // Get the entered values
            String newAircraftType = aircraftTypeField.getText();

            // Use the entered values to insert a new aircraft into the database
            try {
                String sql = "INSERT INTO AIRCRAFTS (AircraftType) VALUES (?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, newAircraftType);

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
    }

    private void addFlightDestination() {
        // Display a dialog to get flight destination information from the admin
        JTextField destinationField = new JTextField();

        Object[] message = {
                "Flight Destination:", destinationField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Enter Flight Destination Information", JOptionPane.OK_CANCEL_OPTION);

        // Check if the admin clicked "OK"
        if (option == JOptionPane.OK_OPTION) {
            // Get the entered values
            String newDestination = destinationField.getText();

            // Use the entered values to insert a new flight destination into the database
            try {
                String sql = "INSERT INTO FLIGHT_DESTINATIONS (Destination) VALUES (?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, newDestination);

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
    }

    private void modifyFlightInformation() {
        // Display a dialog to get flight modification information from the admin
        JTextField flightIDField = new JTextField();
        JTextField newOriginField = new JTextField();
        JTextField newDestinationField = new JTextField();
        JTextField newDepartureDateField = new JTextField();
        JTextField newAircraftIDField = new JTextField();

        Object[] message = {
                "Flight ID:", flightIDField,
                "New Origin:", newOriginField,
                "New Destination:", newDestinationField,
                "New Departure Date:", newDepartureDateField,
                "New Aircraft ID:", newAircraftIDField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Enter Flight Modification Information", JOptionPane.OK_CANCEL_OPTION);

        // Check if the admin clicked "OK"
        if (option == JOptionPane.OK_OPTION) {
            // Get the entered values
            int flightID = Integer.parseInt(flightIDField.getText());
            String newOrigin = newOriginField.getText();
            String newDestination = newDestinationField.getText();
            String newDepartureDate = newDepartureDateField.getText();
            int newAircraftID = Integer.parseInt(newAircraftIDField.getText());

            // Use the entered values to modify flight information in the database
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
        SwingUtilities.invokeLater(() -> {
            SystemAdminGUI systemAdminGUI = new SystemAdminGUI();
            systemAdminGUI.createUI();
        });
    }
}
