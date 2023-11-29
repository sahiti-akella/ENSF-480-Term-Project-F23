package view;

import javax.swing.*;

import model.*;
import model.users.*;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;


public class CustomerGUI implements ActionListener {
    
    private int userID;
    private FlightSystem sys;

    public CustomerGUI(int userID) {
        this.userID = userID;
        this.sys = FlightSystem.getInstance();
    }

    private ArrayList<String> getAvailableFlights() {
        ArrayList<Flight> flightList = sys.getFlightList();
        ArrayList<String> strFlightList = new ArrayList<String>();

        for (Flight flight : flightList){
            int flightID = flight.getflightID();
            String origin = flight.getOrigin();
            String destination = flight.getDestination();
            String departureDate = flight.getDepartureDate();

            String flightInfo = "ID: " + flightID + " | " + origin + " -> " + destination + " : " + departureDate;
            strFlightList.add(flightInfo);
        }

        return strFlightList;
    }
    // public static void main(String[] args) {
    //     CustomerGUI gui = new CustomerGUI();
    //     gui.createUI();
    // }

    public void createUI() {
        Customer customer = null;

        ArrayList<Customer> customers = sys.getCustomerList();

        for (Customer c : customers){
            if (c.getUserID() == userID){
                customer = c;
            }
        }

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
                //open browse flights
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
                //Open FLight cancellation gui
                new FlightCancellation(userID).createUI();
            }
        });
        panel.add(cancelFlightButton);

        frame.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //empty 
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
