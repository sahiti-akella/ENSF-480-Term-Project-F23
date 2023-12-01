package model;

public class Receipt {
    private String selectedSeat;
    private boolean hasInsurance;
    private double seatPrice;
    private static final double INSURANCE_PRICE = 100.00;
    private double totalPrice;

    public Receipt(String selectedSeat, boolean hasInsurance, double seatPrice) {
        this.selectedSeat = selectedSeat;
        this.hasInsurance = hasInsurance;
        this.seatPrice = seatPrice;
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

    public double getTotalPrice() {
        return totalPrice;
    }
}
