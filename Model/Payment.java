package model;

public class Payment {

    // Payment Class Attributes
    private String cardHolderFirstName;
    private String cardHolderLastName;
    private String cardNumber;
    private String expiryDate;
    private int cvv;
    private double total;

    // Payment Constructor
    public Payment(String cardHolderFirstName, String cardHolderLastName, String cardNumber, String expiryDate, int cvv, double total){
        this.cardHolderFirstName = cardHolderFirstName;
        this.cardHolderLastName = cardHolderLastName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.total = total;
    }

    // Payment Getters
    public String getCardHolderFirstName(){
        return this.cardHolderFirstName;
    }

     public String getCardHolderLastName(){
        return this.cardHolderLastName;
    }

    public String getCardNumber(){
        return this.cardNumber;
    }

    public String getExpiryDate(){
        return this.expiryDate;
    }

    public int getCvv(){
        return this.cvv;
    }
    
    public double getTotal(){
        return this.total;
    }

    // Payment Setters 

    public void setCardHolderFirstName(String firstName){
        this.cardHolderFirstName = firstName;
    }

     public void setCardHolderLastName(String lastName){
        this.cardHolderFirstName = lastName;
    }

    public void setCardNumber(String cardNumber){
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(String date){
        this.expiryDate = date;
    }

    public void setCvv(int cvv){
        this.cvv = cvv;
    }
    
    public void setTotal(double total){
        this.total = total;
    }
    
}
