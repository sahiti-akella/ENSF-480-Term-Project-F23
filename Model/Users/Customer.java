package model.users;
public class Customer {

    private int userID;
    private String name;
    private String email;
    private String address;
    private boolean isRegistered;

    public Customer(int userID, String name, String email, String address, boolean isRegistered){
        this.userID = userID;
        this.name = email;
        this.address = address;
        this.isRegistered = isRegistered;
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

    public String getAddress(){
        return this.address;
    }

    public boolean getRegistered(){
        return this.isRegistered;
    }

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
