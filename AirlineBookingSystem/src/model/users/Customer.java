package model.users;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer extends User {

    // Customer Class Attributes
    private String email;
    private String address;
    private boolean isRegistered;

    // Customer Constructor
    public Customer (int userID, String username, String password, String firstName, String lastName, String email, String address, boolean isRegistered){
        super(userID, username, password, firstName, lastName);
        this.email = email;
        this.address = address;
        this.isRegistered = isRegistered;
    }

    // Customer Constructor for guest
    public Customer (String username, String password, String firstName, String lastName, String email, String address, boolean isRegistered){
        super(username, password, firstName, lastName);
        this.email = email;
        this.address = address;
        this.isRegistered = isRegistered;

    }

    // Customer Getters
    public String getEmail(){
        return this.email;
    }

    public String getAddress(){
        return this.address;
    }
     

    public boolean getRegistered(){
        return this.isRegistered;
    }

    // Customer Setters
    public void setEmail(String email){
        this.email = email;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setRegistered(boolean registered){
        this.isRegistered = registered;
    }

}
