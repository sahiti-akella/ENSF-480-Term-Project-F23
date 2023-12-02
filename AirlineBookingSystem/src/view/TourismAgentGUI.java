package view;

import javax.swing.*;

import model.*;
import model.users.*;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TourismAgentGUI implements ActionListener {
    private FlightSystem sys;
    private TourismAgent agent;
    private DefaultListModel<String> listModel; // DefaultListModel to manage tickets

    public TourismAgentGUI(int agentID) {
        this.sys = FlightSystem.getInstance();

        ArrayList<TourismAgent> agents = sys.getTourismAgentList();

        for (TourismAgent a : agents){
            if (a.getUserID() == agentID){
                this.agent = a;
            }
        }
    }

    private ArrayList<String> getAvailableFlights() {
        ArrayList<Flight> flightList = sys.getFlightList();
        ArrayList<String> strFlightList = new ArrayList<String>();

        for (Flight flight : flightList){
            int flightID = flight.getFlightID();
            String origin = flight.getOrigin();
            String destination = flight.getDestination();
            String departureDate = flight.getDepartureDate();

            String flightInfo = "ID: " + flightID + " | " + origin + " -> " + destination + " : " + departureDate;
            strFlightList.add(flightInfo);
        }

        return strFlightList;
    }

    public void createUI() {

        JFrame frame = new JFrame();
        frame.setTitle("Tourism Agent Menu");
        JPanel panel = new JPanel();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Hello, " + agent.getFirstName() + "!");
        welcomeLabel.setBounds(30, 10, 300, 40);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(welcomeLabel);

        JLabel selectCustomerLabel = new JLabel("Select Existing Customer:");
        selectCustomerLabel.setBounds(30, 60, 250, 40);
        panel.add(selectCustomerLabel);

        // Retrieve the list of existing customers from the database
        ArrayList<Customer> customerList = sys.getCustomerList();
        ArrayList<String> customerNameIDList = new ArrayList<String>();

        for (Customer c : customerList){
            String name = c.getFirstName() + " " + c.getLastName();
            int customerID = c.getUserID();

            String customerNameID = "ID: " + customerID + " | " + name;
            customerNameIDList.add(customerNameID);
        }

        customerNameIDList.add(0, "Select customer.."); // Add a default option

        // Initialize the DefaultListModel
        listModel = new DefaultListModel<>();  // Initialize the listModel here
        
        //add to listmodel
        for (String customerNameID : customerNameIDList ){
            listModel.addElement(customerNameID);
        }

        JList<String> customerJList = new JList<>(listModel);
        customerJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(customerJList);
        scrollPane.setBounds(30, 100, 250, 200);
        panel.add(scrollPane);

        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(30, 320, 150, 40);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCustomer = customerJList.getSelectedValue();

                if (selectedCustomer != null && !selectedCustomer.equals("Select customer..")) {
                    // Handle the selected customer
                    handleSelectedCustomer(selectedCustomer);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a valid customer.");
                }
            }
        });
        panel.add(continueButton);

        JButton createNewCustomerButton = new JButton("Create New Customer");
        // Adjusted the y-coordinate to give more space
        createNewCustomerButton.setBounds(30, 370, 250, 40);
        createNewCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                new RegisterNewUser("customer").createUI();
            }
        });
        panel.add(createNewCustomerButton);

        frame.setVisible(true);
    }


    public void handleSelectedCustomer(String selectedCustomer) {
        int userID = Integer.parseInt(extractCustomerID(selectedCustomer));

        JFrame frame = new JFrame();
        frame.setTitle("Options Panel");
        JPanel panel = new JPanel();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Managing the bookings for UserID: " + userID);
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
                openBrowseFlightListFrame(userID);
            }
        });
        panel.add(browseFlightsButton);

        JButton cancelFlightButton = new JButton("Cancel Flight");
        cancelFlightButton.setBounds(30, 120, 180, 40);
        cancelFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the user's ticket list
                ArrayList<Ticket> userTicketList = getTicketsForUser(userID);

                // Check if the list is null or empty
                if (userTicketList == null || userTicketList.isEmpty()) {
                    // Display a message to the user
                    JOptionPane.showMessageDialog(null, "You have no flights to cancel.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Check if all tickets are cancelled
                    boolean allCancelled = userTicketList.stream().allMatch(Ticket::isCancelled);

                    if (allCancelled) {
                        // Display a message to the user
                        JOptionPane.showMessageDialog(null, "All your flights are already cancelled.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Close the current frame
                        frame.dispose();

                        // Open the FlightCancellation GUI
                        new FlightCancellation(userID).createUI();
                    }
                }
            }
        });

        panel.add(cancelFlightButton);

        frame.setVisible(true);
        
    }

    private ArrayList<Ticket> getTicketsForUser(int userID) {
        FlightSystem sys = FlightSystem.getInstance();

        ArrayList<Ticket> userTicketList = new ArrayList<>();
        ArrayList<Ticket> fullTicketList = sys.getTicketList();

        for (Ticket ticket : fullTicketList) {
            if (ticket.getCustomer().getUserID() == userID) {
                userTicketList.add(ticket);
            }
        }

        return userTicketList;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        //empty 
    }

     // Helper function to extract ticketID from String in format: "ID: XX | Origin -> Destination : Date"
     private static String extractCustomerID(String customerInfo) {
        // Define a pattern for extracting the ID
        Pattern pattern = Pattern.compile("ID: (\\d+)");

        // Create a matcher for the input string
        Matcher matcher = pattern.matcher(customerInfo);

        // Check if the pattern is found
        if (matcher.find()) {
            // Group 1 contains the matched ID
            return matcher.group(1);
        } else {
            // Return an empty string or handle the case when no match is found
            return "";
        }
    }

    private void openBrowseFlightListFrame(int userID) {
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
                String selectedFlightStr = (String) dropdown.getSelectedItem();

                int flightID = -99; //default value most likely not a flight ID

                // Define the pattern for extracting the ID
                Pattern pattern = Pattern.compile("ID: (\\d+)");

                Matcher matcher = pattern.matcher(selectedFlightStr);

                // Check if the pattern matches
                if (matcher.find()) {
                    // Extract the matched ID as a string
                    String idString = matcher.group(1);

                    // Convert the string ID to an integer
                    flightID = Integer.parseInt(idString);

                } 
                //loop through flight list
                ArrayList<Flight> flightList = sys.getFlightList();
                Flight selectedFlight = null;
                for (Flight flight : flightList){
                    if (flight.getFlightID() == flightID){
                        selectedFlight = flight;
                    }
                }

                if (!selectedFlightStr.equals("Select flight..")) {
                    frame.dispose(); // Close the current frame
                    openBrowseSeatFrame(selectedFlight, userID);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a valid flight.");
                }
            }
        });
        panel.add(selectButton);
    
        frame.setVisible(true);
    }
    

    private void openBrowseSeatFrame(Flight selectedFlight, int userID) {
        BrowseSeatGUI seatGUI = new BrowseSeatGUI(selectedFlight, userID);
        seatGUI.createUI();
    }
}
