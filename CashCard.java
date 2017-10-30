


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * This class holds the attributes of the cashCard
 * @author Ashraf Saber
 * Date 09/27/2017
 */
public class CashCard implements Comparable<CashCard>{
	
	// Declaring variables
	public String cardNumber;
	public String  expirationDate;

/**
 * CSTR for the CashCard Class
 * @param cardNumber holds the number of the card
 * @param expirationDate holds the value of the expiration date
 */
	public  CashCard(String cardNumber, String expirationDate){
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
	}

/**
 * Sets the value for the card number
 * @param number
 */
	public  void setCardNumber(String number){
		this.cardNumber=number;
	}
	
/**
 * Sets the value for the expiration date 
 * @param expirationDate
 */
	public  void setExpirationDate(String expirationDate){
		this.expirationDate=expirationDate;
	}

/**
 * Gets the card number
 * @return cardNumber: the value of the card number
 */
	public String  getCardNumber(){
		return cardNumber;
	}
	
/**
 * Gets the expiration date
 * @return expirationDate: the value of the expiration date
 */
	public String  getExpirationDate(){
		return expirationDate;
	}
	
/**
 * The compare to function identifies what constitutes the grounds for comparison
 */
	public int compareTo(CashCard other) {
		int cmp=1;
		if(!this.cardNumber.equals(other.cardNumber))
		{
			cmp=this.cardNumber.compareTo(other.cardNumber);
		}
	else if(this.cardNumber.equals(other.cardNumber))
		{cmp=0;}

		return cmp;
	}

}
