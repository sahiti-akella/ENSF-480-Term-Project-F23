package model;

public class Booking {
    private String selectedSeat;
    private boolean hasInsurance;
    private double seatPrice;
    private static final double INSURANCE_PRICE = 100.00;
    private double totalPrice;
    private String origin;  
    private String destination;
    private String departureDate;

    public Booking(String selectedSeat, boolean hasInsurance, double seatPrice, String origin, String destination, String departureDate) {
        this.selectedSeat = selectedSeat;
        this.hasInsurance = hasInsurance;
        this.seatPrice = seatPrice;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        totalPrice = seatPrice + (hasInsurance ? INSURANCE_PRICE : 0);
    }

    public String getSelectedSeat() {
        return selectedSeat;
    }

    public boolean hasInsurance() {
        return hasInsurance;
    }

    public double getSeatPrice() {
        return seatPrice;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
