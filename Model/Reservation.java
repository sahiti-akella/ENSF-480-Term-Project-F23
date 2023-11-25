package model;
import model.users.Customer;

public class Reservation {

    private Customer customer;
    private Flight flight;
    private Seat seat;
    private boolean insuranceSelected;

    public Reservation(Customer customer, Flight flight, Seat seat, boolean insuranceSelected){
        this.customer = customer;
        this.flight = flight;
        this.seat = seat;
        this.insuranceSelected = insuranceSelected;
    }

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

    public void makeReservation(){

    }

}
