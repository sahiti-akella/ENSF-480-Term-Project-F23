package model;

public class CreditCard {
    
    private String cardHolderName;
    private int cardNumber;
    private String expiryDate;
    private int cvv;

    public CreditCard(String cardHolderName, int cardNumber, String expiryDate, int cvv ){
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public String getCardHolderName(){
        return this.cardHolderName;
    }

    public int getCardNumber(){
        return this.cardNumber;
    }

    public String expiryDate(){
        return this.expiryDate;
    }

    public int cvv(){
        return this.cvv;
    }
}
