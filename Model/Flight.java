package model;
import java.util.Date;

public class Flight {

    private int flightID;
    private String origin;
    private String destination;
    private Date departureDate;
    private Crew crew[];


    public int getflightID(){
        return this.flightID;
    }

    public String getOrigin(){
        return this.origin;
    }

    public String getDestination(){
        return this.destination;
    }

    public Date getDepartureDate(){
        return this.departureDate;
    }

    public Crew[] getCrew(){
        return this.crew;
    }

}
