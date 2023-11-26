package view;

import java.util.*;

import model.*;
import model.users.User;

import java.sql.*;

public class FlightSystem {

    // MySQL Connection Variables
    private Connection dbConnect;
    private ResultSet results;
    private final String DBURL = "jdbc:mysql://localhost/FRWA";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    // Flight System Database Lists
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Aircraft> aircrafts = new ArrayList<Aircraft>();
    private ArrayList<Flight> flights = new ArrayList<Flight>();
    private ArrayList<Seat> seats = new ArrayList<Seat>();
    private ArrayList<Payment> payments = new ArrayList<Payment>();
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    
    
    /**
     * This method creates a connection to the SQL database.
     */
    public void createConnection() {
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     /**
     * Getter method returns the SQL Connection object.
     * @return the Connection object to the SQL database.
     */
    public Connection getConnection() {
        return dbConnect;
    }

    // Getters
    public ArrayList<User> getUserList() {
        return this.users;
    }

    public ArrayList<Aircraft> getAircraftList() {
        return this.aircrafts;
    }

    public ArrayList<Flight> getFlightList() {
        return this.flights;
    }

    public ArrayList<Seat> getSeatList() {
        return this.seats;
    }

    public ArrayList<Payment> getPaymentList() {
        return this.payments;
    }

    public ArrayList<Ticket> getTicketList() {
        return this.tickets;
    }

    public FlightSystem() {
        createConnection();

        // List of Users

        // List of Aircrafts
        try {
            PreparedStatement myStmt = dbConnect.prepareStatement("SELECT * FROM AIRCRAFTS");
            results = myStmt.executeQuery();

            while (results.next()) {
                Aircraft aircraft = new Aircraft(results.getInt("AircraftID"), results.getString("AircraftType"));
                aircrafts.add(aircraft);
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

         // List of Flights

        // List of Seats

        // List of Payments

        // List of Tickets










        
    }
}