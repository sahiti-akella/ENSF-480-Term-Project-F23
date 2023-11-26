package model.users;
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
