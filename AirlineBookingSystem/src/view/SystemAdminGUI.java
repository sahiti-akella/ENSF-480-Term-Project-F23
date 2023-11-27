package view;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemAdminGUI {

    private JFrame frame;
    private JPanel mainPanel;
    private JTextArea displayArea;

    public SystemAdminGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("System Admin GUI");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new FlowLayout());

        displayArea = new JTextArea(20, 60);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        mainPanel.add(scrollPane);

        JButton browsePassengerListButton = new JButton("Browse Passenger List");
        mainPanel.add(browsePassengerListButton);

        JButton performSomeAdminAction = new JButton("Perform Admin Action");
        mainPanel.add(performSomeAdminAction);

        browsePassengerListButton.addActionListener(e -> openBrowsePassengerListFrame());

        performSomeAdminAction.addActionListener(e -> performAdminAction());

        frame.setVisible(true);
    }

    private void openBrowsePassengerListFrame() {
        JFrame browseFrame = new JFrame("Browse Passenger List");
        browseFrame.setBounds(200, 200, 600, 400);

        JPanel browsePanel = new JPanel();
        browseFrame.getContentPane().add(browsePanel, BorderLayout.CENTER);

        JTextArea passengerListArea = new JTextArea(15, 40);
        JScrollPane scrollPane = new JScrollPane(passengerListArea);
        browsePanel.add(scrollPane);

        // Call the method to fetch and display passenger information
        fetchAndDisplayPassengerInfo(passengerListArea);

        browseFrame.setVisible(true);
    }

    private void fetchAndDisplayPassengerInfo(JTextArea passengerListArea) {
        // Implement the logic to fetch passenger information from the database
        // and display it in the text area
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/FRWA", "", "");
            String query = "SELECT * FROM CUSTOMERS";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String userInfo = String.format("UserID: %d, UserName: %s, FirstName: %s, LastName: %s, Address: %s, Email: %s, Registered: %b%n",
                        resultSet.getInt("UserID"),
                        resultSet.getString("UserName"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Address"),
                        resultSet.getString("Email"),
                        resultSet.getBoolean("isRegistered"));
                passengerListArea.append(userInfo);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void performAdminAction() {
        // Implement the logic for system admin actions

        displayArea.setText("Performing System Admin Action...\n");

        // admin action logic

        displayArea.append("System Admin Action Completed!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Load the JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                new SystemAdminGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

