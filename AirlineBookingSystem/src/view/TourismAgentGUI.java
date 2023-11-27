package view;

import javax.swing.*;

import model.users.Customer;

import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TourismAgentGUI implements ActionListener {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/FRWA";
    private static final String USER = "root";
    private static final String PASSWORD = "sahi2004";
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField addressField;

    private ArrayList<String> getAvailableFlights() {
    ArrayList<String> flightList = new ArrayList<>();

    try {
        String query = "SELECT FlightID, Origin, Destination, DepartureDate FROM FLIGHTS";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int flightID = resultSet.getInt("FlightID");
            String origin = resultSet.getString("Origin");
            String destination = resultSet.getString("Destination");
            String departureDate = resultSet.getString("DepartureDate");

            String flightInfo = origin + " -> " + destination + " : " + departureDate;
            flightList.add(flightInfo);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return flightList;
}

    private Connection connection;
    public static void main(String[] args) {
        CustomerGUI gui = new CustomerGUI();
        gui.createUI();
    }


    public void createUI() {
        initializeDatabase();
        JFrame frame = new JFrame();
        frame.setTitle("Customer Welcome Page");
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

        collectCustomerInfo(panel);

        JLabel errorLabel = new JLabel("");
        errorLabel.setBounds(30, 190, 300, 25);
        errorLabel.setForeground(Color.RED);
        panel.add(errorLabel);

        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(80, 220, 180, 40);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate and create customer object
                Customer customer = createCustomer();

                if (customer != null) {
                    openOptionsPanel(customer);
                }
            }
        });
        panel.add(continueButton);

        frame.setVisible(true);
    }

    private void collectCustomerInfo(JPanel panel) {
        
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

     // Validate and create customer object
    private Customer createCustomer() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String address = addressField.getText().trim();

        // Check if any field is empty
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || address.isEmpty()) {
            showError("Please complete the form.");
            return null;
        }

        Customer customer = new Customer(0, "", "", firstName, lastName, email, address, false);

        if (!customer.isValidCustomerInfo()) {
            showError("Please enter a valid email.");
            return null;
        }

        return customer;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void initializeDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source.getText().equals("Continue")) {
            // Validate and create customer object
            Customer customer = createCustomer();

            if (customer != null) {
                openOptionsPanel(customer);
            }
        }
    }

    private void openOptionsPanel(Customer customer) {
        JFrame frame = new JFrame();
        frame.setTitle("Options Panel");
        JPanel panel = new JPanel();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Hello, " + customer.getFirstName() + "!");
        welcomeLabel.setBounds(30, 10, 300, 40);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(welcomeLabel);

        JButton browseFlightsButton = new JButton("Browse Flights");
        browseFlightsButton.setBounds(30, 60, 180, 40);
        browseFlightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                openBrowseFlightListFrame();
            }
        });
        panel.add(browseFlightsButton);

        JButton cancelFlightButton = new JButton("Cancel Flight");
        cancelFlightButton.setBounds(30, 120, 180, 40);
        cancelFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                showCancelConfirmation();
            }
        });
        panel.add(cancelFlightButton);

        frame.setVisible(true);
    }

    private void showCancelConfirmation() {
        int result = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to cancel your flight?",
                "Cancel Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            // Update database or perform cancellation actions
            // ...

            JOptionPane.showMessageDialog(null, "Flight cancelled successfully.");
        }
    }

    private void openBrowseFlightListFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("Browse Flight List");
        JPanel panel = new JPanel();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
    
        panel.setLayout(null);
    
        JLabel welcomeLabel = new JLabel("Browse Flight List");
        welcomeLabel.setBounds(30, 10, 200, 40);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(welcomeLabel);
    
        JLabel promptLabel = new JLabel("Please Select the Desired Flight");
        promptLabel.setBounds(30, 40, 200, 40);
        panel.add(promptLabel);
    
        // Retrieve the list of available flights from the database
        ArrayList<String> flightList = getAvailableFlights();
        flightList.add(0, "Select flight.."); // Add a default option
    
        JComboBox<String> dropdown = new JComboBox<>(flightList.toArray(new String[0]));
        dropdown.setBounds(30, 80, 250, 25);
        panel.add(dropdown);
    
        JButton selectButton = new JButton("Select Flight");
        selectButton.setBounds(30, 120, 150, 40);
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFlight = (String) dropdown.getSelectedItem();
                if (!selectedFlight.equals("Select flight..")) {
                    frame.dispose(); // Close the current frame
                    openBrowseSeatFrame(selectedFlight);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a valid flight.");
                }
            }
        });
        panel.add(selectButton);
    
        frame.setVisible(true);
    }
    

    private void openBrowseSeatFrame(String selectedFlight) {
        BrowseSeatGUI seatGUI = new BrowseSeatGUI(selectedFlight);
        seatGUI.createUI();
    }
}