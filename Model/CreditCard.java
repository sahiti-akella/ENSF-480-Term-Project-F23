package model;
import java.util.Date;

public class CreditCard {
    
    private String cardHolderName;
    private int cardNumber;
    private Date expiryDate;
    private int cvv;

    public String getCardHolderName(){
        return this.cardHolderName;
    }

    public int getCardNumber(){
        return this.cardNumber;
    }

    public Date expiryDate(){
        return this.expiryDate;
    }

    public int cvv(){
        return this.cvv;
    }
}
