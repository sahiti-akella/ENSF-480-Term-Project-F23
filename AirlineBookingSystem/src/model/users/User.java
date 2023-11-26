package model.users;
public class User {
    
    // User Class Attributes
    private int userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    // User Constructor
    public User (int userID, String username, String password, String firstName, String lastName){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //User Getters
    public int getUserID(){
        return this.userID;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

     //User Setters
    public void setUserID(int ID){
        this.userID = ID;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

}
