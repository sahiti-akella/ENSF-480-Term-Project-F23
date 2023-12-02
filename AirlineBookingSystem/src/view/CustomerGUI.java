package view;

import javax.swing.*;

import model.*;
import model.users.*;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



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
        Customer customer = null;

        ArrayList<Customer> customers = sys.getCustomerList();

        for (Customer c : customers) {
            if (c.getUserID() == userID) {
                customer = c;
            }
        }

        // Prompt user for membership registration
        int choice = JOptionPane.showConfirmDialog(null, "Would you like to register for a membership?", "Membership Registration", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            // User wants to register for a membership
            showCreditCardOption(customer);
        } else {
            // User does not want to register for a membership
            openOptionsPanel(customer);
        }
    }

    private void showCreditCardOption(Customer customer) {
        int creditCardChoice = JOptionPane.showConfirmDialog(null, "Would you like to register for a company credit card?", "Credit Card Registration", JOptionPane.YES_NO_OPTION);

        if (creditCardChoice == JOptionPane.YES_OPTION) {
            String creditCardNumber = JOptionPane.showInputDialog(null, "Please enter a 16-digit credit card number:");

            if (isValidCreditCardNumber(creditCardNumber)) {
                // Credit card number is valid
                registerCreditCard(customer, creditCardNumber);
            } else {
                // Credit card number is invalid
                JOptionPane.showMessageDialog(null, "Invalid credit card number. Please enter a valid 16-digit number.");
                showCreditCardOption(customer); 
            }
        } else {
            // User does not want to register for a credit card
            openOptionsPanel(customer);
        }
    }


    private boolean isValidCreditCardNumber(String creditCardNumber) {
        return creditCardNumber != null && creditCardNumber.matches("\\d{16}");
    }

   private void registerCreditCard(Customer customer, String creditCardNumber) {
        JOptionPane.showMessageDialog(null, "Successfully registered credit card for "
                + customer.getFirstName() + " " + customer.getLastName() + "!", "Credit Card Registration",
                JOptionPane.INFORMATION_MESSAGE);

        openOptionsPanel(customer);
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
        ArrayList<Ticket> ticketList = sys.getTicketList();
        
        for (Ticket ticket : ticketList) {
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
                    openBrowseSeatFrame(selectedFlight);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a valid flight.");
                }
            }
        });
        panel.add(selectButton);
    
        frame.setVisible(true);
    }
    

    private void openBrowseSeatFrame(Flight selectedFlight) {
        BrowseSeatGUI seatGUI = new BrowseSeatGUI(selectedFlight);
        seatGUI.createUI();
    }
}
