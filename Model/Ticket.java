package model;

import model.users.Customer;

public class Ticket {

    // Ticket Class Attributes
    private Customer customer;
    private Flight flight;
    private Seat seat;
    private boolean insuranceSelected;
    private double total;

    // Ticket Constructor
    public Ticket(Customer customer, Flight flight, Seat seat, boolean insuranceSelected, double total){
        this.customer = customer;
        this.flight = flight;
        this.seat = seat;
        this.insuranceSelected = insuranceSelected;
        this.total = total;
    }

    // Ticket Getters
    public Customer getCustomer(){
        return this.customer;
    }

    public Flight getFlight(){
        return this.flight;
    }

    public Seat getSeat(){
        return this.seat;
    }

    public boolean getInsuranceSelection(){
        return this.insuranceSelected;
    }

    public double getTotal(){
        return this.total;
    }

    // Ticket Setters
    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public void setFlight(Flight flight){
        this.flight = flight;
    }

    public void setSeat(Seat seat){
        this.seat = seat;
    }

    public void setInsuranceSelection(boolean insuranceSelected){
        this.insuranceSelected = insuranceSelected;
    }

    public void setTotal(double total){
        this.total = total;
    }
}
