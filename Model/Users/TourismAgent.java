package model.users;
public class TourismAgent {

    private int userID;
    private String name;
    private String email;

    public TourismAgent(int userID, String name, String email){
        this.userID = userID;
        this.name = name;
        this.email = email;
    }

    public int getUserID(){
        return this.userID;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }
    
}
