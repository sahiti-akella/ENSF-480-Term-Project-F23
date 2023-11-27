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


    // Validation functions for customer information
    private boolean isValidEmail(String email) {
        // Simple email validation using regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidName(String name) {
        // Simple name validation: should not be empty
        return !name.trim().isEmpty();
    }

    private boolean isValidAddress(String address) {
        // Simple address validation: should not be empty
        return !address.trim().isEmpty();
    }

    // Validate all customer information
    public boolean isValidCustomerInfo() {
        return isValidName(getFirstName()) &&
                isValidName(getLastName()) &&
                isValidEmail(email) &&
                isValidAddress(address);
    }

    // Customer Operations (Do these go here??)
    public void register(){

    }

    public void applyCreditCard(){
        
    }

    public void recievePromotionNews(){

    }

    public void useAirportLounge(){

    }

    public void receiveCompanionTicket(){

    }
}
