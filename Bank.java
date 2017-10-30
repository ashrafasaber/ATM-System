 

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * This class holds the attributes of the bank
 * @author Ashraf Saber
 * Date 09/27/2017
 */
public class Bank {
	public char bank_id; // should this be public
	Map<CashCard,Account> map = new TreeMap<>();
	public ArrayList<Account> bank_accounts = new ArrayList<>();
	public ArrayList<ATM> bank_ATMs = new ArrayList<>();

	/**
	 * CSTR for Bank
	 */
	Bank(){

	}
	/**
	 * Sets the bank id
	 * @param bank_id
	 */
	public void setID(char bank_id){
		this.bank_id=bank_id;
	}
	/**
	 * Gets the bank id
	 * @return bank_id
	 */
	public char getID(){
		return bank_id;
	}
	/**
	 * gets the card numbers for all the values in the map
	 */
	public void getMap(){
		for(CashCard c: map.keySet())
		{
			System.out.println(c.cardNumber);
		}
	}
	/**
	 * prints the withdrawal limits for all the ATMs
	 */
	public void getATMs(){
		for(ATM a:bank_ATMs)
			System.out.print(a.name+" "+a.bank_id+" "+a.withdrawalLimit+"\n");
	}
	/**
	 * prints the account numbers
	 */
	public void getAccounts(){
		for(Account a: bank_accounts){
			System.out.println(a.getAccountNumber());
		}
	}
	/**
	 * adds accounts to arraylist
	 * @param account
	 */
	public void createAccount(Account account){
		bank_accounts.add(account);
	}
	/**
	 * adds cashcard and account combination to map
	 * @param cashCard
	 * @param account
	 */
	public void map(CashCard cashCard,Account account){	
		map.put(cashCard, account);
		createAccount(account);
	}
	/**
	 * adds ATMs to arraylist
	 * @param atm
	 */
	public void addATM(ATM atm){
		bank_ATMs.add(atm);
	}
	/**
	 * converts a string value to a date value
	 * @param expirationDate
	 * @return date
	 */
	public Date stringToDate(String expirationDate){
		Date date=null;
		SimpleDateFormat formatter=new SimpleDateFormat("MM/DD/YYYY");
		try{
			date=formatter.parse(expirationDate);
		}
		catch(ParseException e)
		{
			System.out.println(e.getMessage());
		}
		return date;
	}

	/**
	 * returns the day of today
	 * @return today
	 */
	public Date dateOfToday(){       
		Date today;
		GregorianCalendar calendar = new GregorianCalendar();
		int day= calendar.get(Calendar.DATE);
		int month=calendar.get(Calendar.MONTH)+1;
		int year=calendar.get(Calendar.YEAR);
		String dayS,monthS,dateToday;
		if(day <10){ dayS= "0"+day;}else{dayS=Integer.toString(day); }
		if(month <10){ monthS= "0"+month;}else{monthS= Integer.toString(month);}
		dateToday= monthS+"/"+dayS+"/"+year;
		today =stringToDate(dateToday);
		return today;
	}

	/**
	 * checks card expiry
	 * @param card
	 * @return expired
	 */
	public boolean cardExpired(CashCard card){   
		boolean expired=false;

		// Check if the expiration date of card 
		Date cardExpiration = stringToDate(card.expirationDate);
		Date today = dateOfToday();
		int cmp=cardExpiration.compareTo(today);
		if(cmp== 0 || cmp==-1){expired=true;}else{expired=false;}
		return expired;
	}
	/**
	 * validates the card
	 * @param card
	 * @return card_valid
	 */
	public boolean validateCard(CashCard card){     
		boolean card_valid  = false;
		if(!cardExpired(card)   && map.containsKey(card)){card_valid=true;}
		if(cardExpired(card)){
			System.out.println("This card is expired and returned to you.");
		}
		return card_valid;
	}	
	/**
	 * validates atm/bank combination
	 * @param a
	 * @return atm_valid
	 */
	public boolean validateATM(ATM a){ 
		boolean atm_valid  = false;
		for( ATM b :bank_ATMs){
			if(b.equals(a)){
				atm_valid=true;
			}
		}
		return atm_valid;	
	}

	/**
	 * authenticates account
	 * @param card
	 * @param pass
	 * @return authenticated
	 */
	public boolean authenticateAccount(CashCard card, String pass){
		boolean authenticated=false;

		for(CashCard c :map.keySet()){
			if(c.getCardNumber().equals(card.getCardNumber()) &&  pass.equals(map.get(c).getPassword())){

				authenticated=true;
				break;
			}
		}
		return authenticated;
	}

	/**
	 * deposits an amount into a card and its corresponding account
	 * @param card
	 * @param amount
	 */
	public void deposit(CashCard card, double amount){
		double currentBalance=map.get(card).getAccountBalance();
		double newBalance=currentBalance+amount;
		map.get(card).setAccountBalance(newBalance);
	}
	/**
	 * withdraws an amount from a specific card
	 * @param card
	 * @param withdrawalAmount
	 */
	public void withdraw(CashCard card, double withdrawalAmount){
		Scanner scanner = new Scanner(System.in);
		Boolean cont=true;
		do{
			if(!checkBalanceLimit(card,withdrawalAmount))
			{
				double newBalance=map.get(card).getAccountBalance()-withdrawalAmount;
				map.get(card).setAccountBalance(newBalance);
				cont=false;
			}
		}while(cont);
	}
	/**
	 * returns the account balance 
	 * @param card
	 * @return balance
	 */
	public double accountBalance(CashCard card)
	{
		double balance=map.get(card).getAccountBalance();
		return balance;
	}
	/**
	 * checks if the balance limit is exceeded
	 * @param card
	 * @param withdrawalAmount
	 * @return
	 */
	public boolean checkBalanceLimit(CashCard card, double withdrawalAmount){
		boolean exceedingBLimit=true;
		double balance=map.get(card).getAccountBalance();
		if(withdrawalAmount<=balance){
			exceedingBLimit=false;
		}else{
			exceedingBLimit=true;
		}
		return exceedingBLimit;
	}

}
