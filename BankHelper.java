

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Scanner;
/**
 * This class includes functions that help with the operation of the ATM
 * @author Ashraf
 * 09/30/2017
 */
public class BankHelper {
	Scanner scanner = new Scanner(System.in);
	public ArrayList<Bank> banks = new ArrayList<>();
	public void addBank(Bank bank){
		banks.add(bank);
	}
	/**
	 * This function prints the banks and their ids
	 */
	public void getBanks(){
		for(Bank b:banks)
		{
			System.out.print(b.bank_id+" ");
		}
		System.out.print( "\n");
	}
	/**
	 * This function gets the bank details
	 */
	public void getBankDetails(){
		for(Bank b:banks)
		{

			System.out.print("Bank ID: "+b.bank_id+" Number of ATMs: "+b.bank_ATMs.size() );
			System.out.print("\n");
		}
	}
	/**
	 * This function searches for the cardnumber and checks if it is used with the proper atm
	 * @param CardNumber
	 * @param ATM
	 */
	public void search(String CardNumber, String ATM){
		Bank target = new Bank();
		boolean card_found = false;
		target=target(ATM);
		for(CashCard c: target.map.keySet()){
			if(c.cardNumber.equals(CardNumber))
			{
				System.out.print("The card is accepted.");
				card_found=true;
				break;
			}else{
				card_found=false;
			}
		}
		if(!card_found){System.out.print("This card is not supported by this ATM\n");}
	}
	/**
	 * This method takes the name of an ATM and returns its associated bank
	 * @param ATM
	 * @return target
	 */
	public Bank target(String ATM){
		Bank target = new Bank();
		boolean bank_found = false;
		for(Bank b: banks)
		{
			for(int i=0;i<b.bank_ATMs.size();i++){
				if(b.bank_ATMs.get(i).name.equals(ATM))
				{
					target=b;
					bank_found=true;
					break;
				} else{
					bank_found=false;
				}
			}
			if(bank_found){break;}
		}
		return target;
	}

	/**
	 * Checks card expiry (boolean)
	 * @param CardNumber
	 * @param ATM
	 * @return expired
	 */
	public boolean cardExpired(String CardNumber, String ATM){
		boolean expired=false;
		Bank target = new Bank();

		target=target(ATM);
		for(CashCard c: target.map.keySet()){
			if(c.cardNumber.equals(CardNumber))
			{				
				expired=target.cardExpired(c);
				break;
			}else{
				expired=true;
			}

		}return expired;
	}
	/**
	 * Checks card expiry (void)
	 * @param CardNumber
	 * @param ATM
	 */
	public void cardExpiredSOP(String CardNumber, String ATM){
		if(cardExpired(CardNumber,ATM)){
			System.out.print("This card is expired and returned to you.  ");
		}else{
			search(CardNumber,ATM);
		}		
	}
	/**
	 * Validates ATM
	 * @param CardNumber
	 * @param ATM
	 */
	public void ATMvalidation(String CardNumber, String ATM){
		Bank target=target(ATM);	
		ATM targetATM = new ATM();
		for(ATM a: target.bank_ATMs)
		{
			if(a.name.equals(ATM)){
				targetATM=a;
				//System.out.print("ATM: "+targetATM.name);
				break;
			}
		}
		// ACCESS bank then loop through cards then check if they are equal
		boolean notSupported=false;
		for(CashCard c: target.map.keySet())
		{
			if(c.cardNumber.equals(CardNumber))
			{
				notSupported=false;
				break;}
			else{
				notSupported=true;
			}
		}
		if(notSupported){System.out.print("This card is not supported by this ATM\n");}
	}
	/**
	 * Validates ATM (Bool)
	 * @param CardNumber
	 * @param ATM
	 * @return valid
	 */
	public boolean ATMvalidationB(String CardNumber, String ATM){
		boolean valid=false;
		Bank target=target(ATM);	
		ATM targetATM = new ATM();
		for(ATM a: target.bank_ATMs)
		{
			if(a.name.equals(ATM)){
				targetATM=a;
				break;
			}
		}
		for(CashCard c: target.map.keySet())
		{
			if(c.cardNumber.equals(CardNumber))
			{valid=true;break;}
		}
		return valid;
	}	
	/**
	 * Retrieves the CashCard associated with a card name and atm name
	 * @param CardNumber
	 * @param ATM
	 * @return x
	 */
	public CashCard card(String CardNumber, String ATM){
		CashCard x =new CashCard(CardNumber, "30/12/9999");
		Bank target=target(ATM);
		for(CashCard c: target.map.keySet()){
			if(c.cardNumber.equals(CardNumber))
			{	x=c;
			return x;
			}
		}

		return x;
	}
	/**
	 * Authenticates
	 * @param CardNumber
	 * @param ATM
	 */
	public void authentication(String CardNumber, String ATM){
		if(!cardExpired(CardNumber,ATM))
		{
			Boolean repeat=true;
			do{
				System.out.print("Please enter your password.");
				//Scanner scanner = new Scanner(System.in);
				String pass = scanner.nextLine();		
				Bank target=target(ATM);
				CashCard card=card( CardNumber,ATM);
				if(target.authenticateAccount( card,  pass)){
					System.out.print("Authorization is accepted. ");
					repeat=false;
				}else{System.out.print("This is a wrong password.");};
			}while(repeat);
		}
	}
	/**
	 * Checks if exceeding ATM Limit
	 * @param ATM
	 * @param amount
	 * @return
	 */
	public boolean exceedingATML(String ATM,double amount){
		Bank target=target(ATM);
		boolean exceedingATML=false;
		boolean flag=false;
		ATM targetATM = new ATM();
		for(ATM a: target.bank_ATMs){
			if( a.name.equals(ATM)){
				targetATM = a;
				flag=true;
			}
			if(flag){break;}
		}
		exceedingATML= targetATM.checkWithdrawalLimit( amount);
		return exceedingATML;  
	}
	/**
	 * Checks if exceeding Account Limit
	 * @param CardNumber
	 * @param ATM
	 * @param withdrawalAmount
	 * @return exceedingAccountL
	 */
	public boolean exceedingAccountL(String CardNumber, String ATM,double withdrawalAmount){ //1
		boolean exceedingAccountL=false;
		Bank target= target(ATM);
		CashCard card=card(CardNumber,ATM);
		target.map(card,  getAccount(card));
		return exceedingAccountL;
	}
	/**
	 * Gets Account associated with cashcard
	 * @param c
	 * @return acc
	 */
	public Account getAccount(CashCard c){
		Bank t= new Bank();
		Account acc = new Account(c, 1, "", 'A', 40);
		for(Bank b:banks){
			if(b.map.containsKey(c))
			{
				t=b;
			}
			acc = t.map.get(c);		
		}
		return acc;
	}
	/**
	 * performs withdrawal operation
	 * @param CardNumber
	 * @param ATM
	 */
	public void withdrawal(String CardNumber, String ATM){  //2
		System.out.print(" Start your transaction by entering the amount to withdraw.");  
		//Scanner scanner = new Scanner(System.in);
		double withdrawalAmount = scanner.nextDouble();
		String userInput;

		Bank target= target(ATM);
		CashCard card=card(CardNumber,ATM);

		//boolean notExceedingATM=true;
		boolean cont=true;
		do{
			if(exceedingATML(ATM,withdrawalAmount))
			{
				System.out.println("This amount exceeds the maximum amount you can withdraw per transaction. Please enter the amount or quit.");
				userInput=scanner.next(); 
				if(userInput.toLowerCase().equals("quit"))
				{ 
					//System.out.print("quit1 successful"); 
					break;}
				else{ 
					withdrawalAmount= Double.valueOf(userInput);
				} // fails why??????
			}else{
				cont=false;
				//notExceedingATM=false;
			}
		} while(cont);

		boolean  flag=true;

		do{
			if(exceedingAccountL(card.getCardNumber(),ATM,withdrawalAmount)){
				System.out.print("The amount exceeds the current balance. Enter another amount or quit");
				userInput=scanner.next();
				if(userInput.toLowerCase().equals("quit")){
					break;}	
				else{withdrawalAmount= Double.valueOf(userInput);}
			}else{
				flag=false;
			}
		} while(flag);
		target.withdraw(card,withdrawalAmount);
		double remainingBalance= getBalance(CardNumber,ATM);
		boolean flag2=true;

		System.out.println(withdrawalAmount+" is withdrawn from  your account. The remaining balance of this account is "+remainingBalance);
		do{
			System.out.print("If you have more transactions, enter the amount or quit."); 
			userInput=scanner.next();
			if(userInput.toLowerCase().equals("quit")){
				//System.out.print("quit2 successful"); 
				flag2=false;
				break;
			}else
			{
				withdrawalAmount= Double.valueOf(userInput);
				target.withdraw(card,withdrawalAmount);
				remainingBalance= getBalance(CardNumber,ATM);
				System.out.println(withdrawalAmount+" is withdrawn from  your account. The remaining balance of this account is "+remainingBalance);
			}
		}while(flag2);
	}
	/**
	 * gets account balance
	 * @param CardNumber
	 * @param ATM
	 * @return
	 */
	public double getBalance(String CardNumber, String ATM){
		Bank target= target(ATM);
		CashCard card=card(CardNumber,ATM);
		double balance=target.accountBalance(card);
		return balance;
	}
}

