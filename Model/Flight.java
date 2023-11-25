package model;

public class Flight extends Aircraft {

    private int flightID;
    private String origin;
    private String destination;
    private String departureDate;
    private Crew crew[];


    public Flight(int flightID, String origin, String destination, String departureDate, int aircraftID) {
        super(aircraftID);
        this.flightID = flightID;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
    }

    public int getflightID(){
        return this.flightID;
    }

    public String getOrigin(){
        return this.origin;
    }

    public String getDestination(){
        return this.destination;
    }

    public String getDepartureDate(){
        return this.departureDate;
    }

    public Crew[] getCrew(){
        return this.crew;
    }

}
