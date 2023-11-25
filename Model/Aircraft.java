package model;
public class Aircraft {

    private int aircraftID;
    private String aircraftType;
    private Seat seats[];

    public Aircraft(int aircraftID, String aircraftType) {
        this.aircraftID = aircraftID;
        this.aircraftType = aircraftType;
    }

    public Aircraft(int aircraftID2) {
    }

    public int getAircraftID(){
        return this.aircraftID;
    }

    public String getAircraftType(){
        return this.aircraftType;
    }

    public Seat[] getSeats(){
        return this.seats;
    }


    
}
