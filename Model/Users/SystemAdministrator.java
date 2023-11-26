package model.users;
import java.util.Date;

import model.*;

public class SystemAdministrator extends User{

   // SystemAdministrator Constructor
   public SystemAdministrator (int userID, String username, String password, String firstName, String lastName){
        super(userID, username, password, firstName, lastName);
    }

    public void browseFlights(Date date){

    }

    public void browseCrews(String flightID){

    }

    public void browseAircrafts(){

    }

    public void addCrew(Crew crew){

    }

    public void removeCrew(Crew crew){
        
    }

    public void addAircraft(Aircraft aircraft){

    }

    public void removeAircraft(Aircraft aircraft){

    }

    public void addDestination(String destination){

    }

    public void removeDestination(String destination){

    }

    public void addFlightInfo(Flight flight){

    }

    public void removeFlightInfo(Flight flight){

    }

    public void modifyFlightInfo(Flight flight){

    }

    public void printUserList(){

    }


}
