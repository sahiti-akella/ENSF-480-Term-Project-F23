import Users.Customer;

public class Reservation {

    private Customer customer;
    private Flight flight;
    private Seat seat;
    private boolean insuranceSelected;

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
