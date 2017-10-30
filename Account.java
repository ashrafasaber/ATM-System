/**
 * This class holds the attributes for the Account class
 * @author Ashraf
 * Date 09/27/2017
 */
public class Account {
	private int accountNumber;
	private String password;
	private char bank_id;
	private double accountBalance;
	private CashCard cashCard;

	/**
	 * This method acts as a CSTR 
	 * @param cashCard
	 * @param accountNumber
	 * @param Password
	 * @param bank_id
	 * @param account_balance
	 */
	public Account(CashCard cashCard, int accountNumber, String password, char bank_id, double accountBalance)
	{
		this.cashCard=cashCard;
		this.accountNumber=accountNumber;
		this.password=password;
		this.bank_id=bank_id;
		this.accountBalance=accountBalance;
	}


	/**
	 * Sets the value of the account number
	 * @param accountNumber
	 */
	public void setAccountNumber(int accountNumber){
		this.accountNumber=accountNumber;
	}
	/**
	 * Sets the password
	 * @param password
	 */
	public void setPassword(String password){
		this.password=password;
	}
	/**
	 * Sets the bank id
	 * @param bank_id
	 */
	public void setBankId(char bank_id){
		this.bank_id=bank_id;
	}
	/**
	 * Sets the account balance
	 * @param accountBalance
	 */
	public void setAccountBalance(double accountBalance){
		this.accountBalance=accountBalance;
	}	
	/**
	 * Sets the cash card value
	 * @param cashCard
	 */
	public void setCashCard(CashCard cashCard){
		this.cashCard=cashCard;
	}

	// getters
	/**
	 * Gets the account number
	 * @return accountNumber
	 */
	public int getAccountNumber(){
		return accountNumber;
	}
	/**
	 * Gets the password
	 * @return password
	 */
	public String getPassword(){
		return password;
	}
	/**
	 * Gets the bank id
	 * @return bank_id
	 */
	public char getBankId(){
		return bank_id;
	}
	/**
	 * Gets the account balance
	 * @return accountBalance
	 */
	public double getAccountBalance(){
		return accountBalance;
	}
}
