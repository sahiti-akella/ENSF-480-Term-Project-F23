package model;
public class Seat{

    private int flightID;
    private int seatID;
    private String seatType;
    private double price;
    private boolean isAvailable;

    public Seat(int flightID, int seatID, String seatType, double price, boolean isAvailable){
        this.flightID = flightID;
        this.seatID = seatID;
        this.seatType = seatType;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public int getflightID(){
        return this.flightID;
    }

    public int getSeatID(){
        return this.seatID;
    }

    public String getSeatType(){
        return this.seatType;
    }

    public double getPrice(){
        return this.price;
    }

    public boolean isAvailable(){
        return this.isAvailable;
    }
    
}
