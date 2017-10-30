
/**
 * This class holds the attributes of an ATM
 * @author Ashraf
 * Date 09/27/2017
 */
public class ATM extends Bank{

	String name;
	double withdrawalLimit;
	/**
	 * ATM CSTR
	 */
	ATM(){ }
	/**
	 * Sets bank_id for super class
	 */
	public void setID(char bank_id){
		this.bank_id=bank_id;
	}
	/**
	 *  sets name of the ATM
	 * @param name
	 */
	public void setName(String name){this.name=name;}
	/**
	 * sets the withdrawal limit
	 * @param withdrawalLimit
	 */
	public void setWithdrawalLimit(double withdrawalLimit){
		this.withdrawalLimit=withdrawalLimit;
	}
	/**
	 * gets the bank id
	 * @return bank_id
	 */
	public char getBankId(){return bank_id;}
	/**
	 * gets the withdrawal limit
	 * @return withdrawalLimit
	 */
	public double getWithdrawalLimit(){return withdrawalLimit;}
	/**
	 * 	checks if the withdrawal limit is exceeded
	 * @param amount
	 * @return exceedingLimit
	 */
	public boolean checkWithdrawalLimit(double amount){
		boolean exceedingLimit=false;
		if(amount<=withdrawalLimit){
			exceedingLimit=false;
		}else{
			exceedingLimit=true;
		}
		return exceedingLimit;
	}



}
