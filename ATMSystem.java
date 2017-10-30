

import java.util.*;
import java.util.Scanner;
/**
 * This class tests the program
 * @author Ashraf
 *
 */
public class ATMSystem {
	/**
	 * This function assists with printing 
	 * @param x
	 */
	public static void sop(Object x){   
		System.out.println(x);
	}
	/**
	 * This function tests the ATM system
	 * @param args
	 */
	public static void main(String [ ] args)
	{
		// Setting the Initial Balance
		double initial_Balance=40.0;

		//Creating the Banks
		Bank bank_A = new Bank();
		bank_A.setID('A');
		Bank bank_B = new Bank();
		bank_B.setID('B');

		BankHelper banks = new BankHelper();
		banks.addBank(bank_A);
		banks.addBank(bank_B);

		//Creating the CashCard
		CashCard card_A11= new CashCard ("A11","12/15/2001");
		CashCard card_A12= new CashCard ("A12","12/15/2020");
		CashCard card_B111= new CashCard ("B111","01/15/2019");
		CashCard card_B122= new CashCard ("B122","01/15/2018");
		CashCard card_B133= new CashCard ("B133","01/15/2020");

		// Creating the Accounts
		Account account_A11 = new Account(card_A11,11,"mypassword",'A',initial_Balance);
		Account account_A12 = new Account(card_A12,12,"mypassword",'A',initial_Balance);
		Account account_B111 = new Account(card_B111,111,"mypassword",'B',initial_Balance);
		Account account_B122 = new Account(card_B122,122,"mypassword",'B',initial_Balance);
		Account account_B133 = new Account(card_B133,133,"mypassword",'B',initial_Balance);
		// Adding Cards and Accounts to the TreeMap in Respective Banks
		bank_A.map(card_A11,account_A11);
		bank_A.map(card_A12,account_A12);
		bank_B.map(card_B111,account_B111);
		bank_B.map(card_B122,account_B122);
		bank_B.map(card_B133,account_B133);
		// Creating ATMs
		ATM A1 = new ATM();
		A1.setName("ATM1_A");
		A1.setID('A');
		A1.setWithdrawalLimit(50);
		bank_A.addATM(A1);

		ATM A2 = new ATM();
		A2.setName("ATM2_A");
		A2.setID('A');
		A2.setWithdrawalLimit(50);
		bank_A.addATM(A2);

		ATM B1 = new ATM();
		B1.setName("ATM1_B");
		B1.setID('B');
		B1.setWithdrawalLimit(50);
		bank_B.addATM(B1);

		ATM B2 = new ATM();
		B2.setName("ATM2_B");
		B2.setID('B');
		B2.setWithdrawalLimit(50);
		bank_B.addATM(B2);

		// PRINT STATES OF BANKS AND WORK ON UI
		System.out.println("States of two Banks \n");
		System.out.println("Assume all accounts have $40 preloaded.");
		System.out.println("BankofA (two customers)");
		// Customer with Cash Card (bankid: A, account number (#11), expires on MM/DD/YY, password 
		System.out.print("Customer with Cash Card (bankid: "+ bank_A.bank_id+ ", account number (# "+ account_A11.getAccountNumber()+"),"+" expires on "+card_A11.getExpirationDate());
		System.out.print( ", password: "+account_A11.getPassword()+")\n");

		System.out.print("Customer with Cash Card (bankid: "+ bank_A.bank_id+ ", account number (# "+ account_A12.getAccountNumber()+"),"+" expires on "+card_A12.getExpirationDate());
		System.out.print( ", password: "+account_A12.getPassword()+")\n");


		System.out.println("\nBankofB (three customers)");
		System.out.print("Customer - Cash Card (bankid: "+ bank_B.bank_id+ ", account number (# "+ account_B111.getAccountNumber()+"),"+" expires on "+card_B111.getExpirationDate());
		System.out.print( ", password: "+account_B111.getPassword()+")\n");

		System.out.print("Customer - Cash Card (bankid: "+ bank_B.bank_id+ ", account number (# "+ account_B122.getAccountNumber()+"),"+" expires on "+card_B122.getExpirationDate());
		System.out.print( ", password: "+account_B122.getPassword()+")\n");

		System.out.print("Customer - Cash Card (bankid: "+ bank_B.bank_id+ ", account number (# "+ account_B133.getAccountNumber()+"),"+" expires on "+card_B133.getExpirationDate());
		System.out.print( ", password: "+account_B133.getPassword()+")\n");

		System.out.print("\nStates of four ATMs (2 for each Bank)\n");
		System.out.print(A1.name+": (ATM1 from BankofA)\n");
		System.out.print("The maximum amount of cash a card can widthraw per day: ");
		System.out.println(A1.getWithdrawalLimit()+"\n");

		System.out.print(A2.name+": (ATM2 from BankofA)\n");
		System.out.print("The maximum amount of cash a card can widthraw per day: ");
		System.out.println(A2.getWithdrawalLimit()+"\n");

		System.out.print(B1.name+": (ATM1 from BankofB)\n");
		System.out.print("The maximum amount of cash a card can widthraw per day: ");
		System.out.println(B1.getWithdrawalLimit()+"\n");

		System.out.print(B2.name+": (ATM2 from BankofB)\n");
		System.out.print("The maximum amount of cash a card can widthraw per day: ");
		System.out.println(B2.getWithdrawalLimit()+"\n");		
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("Now, your program is in an interactive mode.\n");

		Scanner scanner = new Scanner(System.in);
		sop("Enter your choice of ATM ");		
		String userATMInput = scanner.nextLine();	
		String userCardInput;
		do{
			sop("Enter your card ");
			userCardInput = scanner.next();		

			banks.ATMvalidation(userCardInput, userATMInput);
		}
		while(!banks.ATMvalidationB(userCardInput, userATMInput));

		banks.cardExpiredSOP(userCardInput, userATMInput);	
		if(banks.cardExpired(userCardInput, userATMInput))
		{
			System.exit(0);
		}else {
			banks.authentication(userCardInput, userATMInput);
		}
		banks.withdrawal(userCardInput,userATMInput);

	}
}
