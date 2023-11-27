package view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomerGUI implements ActionListener {
    private static final String DB_URL = "jdbc:mysql://your_database_url:3306/FRWA";
    private static final String USER = "username";
    private static final String PASSWORD = "password";

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

        JButton browseFlightsButton = new JButton("Browse Flights");
        browseFlightsButton.setBounds(30, 50, 180, 40);
        browseFlightsButton.addActionListener(this);
        panel.add(browseFlightsButton);

        frame.setVisible(true);
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

        if (source.getText().equals("Browse Flights")) {
            openBrowseFlightListFrame();
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

        String[] options = {"Select flight..", "1", "2", "3"};
        JComboBox<String> dropdown = new JComboBox<>(options);
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
