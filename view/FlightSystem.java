package view;

import java.util.*;
import java.sql.*;

public class FlightSystem {

    // MySQL Connection Variables
    private Connection dbConnect;
    private ResultSet results;
    private final String DBURL = "jdbc:mysql://localhost/FRWA";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    // Flight System Database Lists
    private ArrayList<Users> users = new ArrayList<Users>();
    private ArrayList<Aircrafts> aircrafts = new ArrayList<Aircrafts>();
    private ArrayList<Flights> flights = new ArrayList<Flights>();
    private ArrayList<Seats> seats = new ArrayList<Seats>();
    private ArrayList<Payments> payments = new ArrayList<Payments>();
    private ArrayList<Tickets> tickets = new ArrayList<Tickets>();
    
    
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

    public FlightSystem(){
        createConnection();
    }


    // Getters
    public ArrayList<Users> getUserList() {
        return this.users;
    }

    public ArrayList<Aircrafts> getAircraftList() {
        return this.aircrafts;
    }

    public ArrayList<Flights> getFlightList() {
        return this.flights;
    }

    public ArrayList<Seats> getSeatList() {
        return this.seats;
    }

    public ArrayList<Payments> getPaymentList() {
        return this.payments;
    }

    public ArrayList<Tickets> getTicketList() {
        return this.tickets;
    }
    
}
