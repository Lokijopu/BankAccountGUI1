/**
 * 
 * @author Kevin Guo Period 6
 *
 */
public class SavingsAccount extends BankAccount{

	private double intRate;
	private static double MIN_BAL;
	private static double MIN_BAL_FEE;
	/**
	 * Creates a savings account
	 * @param n name
	 * @param b balance
	 * @param r interest rate
	 * @param mb minimum balance
	 * @param mbf minimum balance fee
	 */
	SavingsAccount(String n, double b, double r, double mb, double mbf) {
		super(n, b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	/**
	 * Creates a savings account with a balance of 0
	 * @param n name
	 * @param b balance
	 * @param r interest rate
	 * @param mb minimum balance
	 * @param mbf minimum balance fee
	 */
	SavingsAccount(String n, double r, double mb, double mbf) {
		super(n, 0);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	/**
	 * Deducts a sum of money from the savings account without the balance going negative. If the balance goes below minimum balance, fee is charged.
	 * @param amt amount withdrawn
	 */
	public void withdraw(double amt) {
		try {
			if (this.getBalance() - amt >= MIN_BAL) {
				this.withdraw(amt);
			} else if (this.getBalance() - amt < MIN_BAL && this.getBalance() - amt >= MIN_BAL_FEE) {
				this.withdraw(amt);
				this.withdraw(MIN_BAL_FEE);
			}
		}
		catch (IllegalArgumentException e) {
			if (this.getBalance() <= amt + MIN_BAL_FEE) {
			throw new IllegalArgumentException("Transaction not authorized; IllegalArgumentException");
			}
		}
		catch (NullPointerException e) {
			throw new NullPointerException("NullPointerException");
		}
	}
	/**
	 * Transfers money from one account to another without any balance going negative
	 * @param other the account where money is being transferred to
	 * @param amt amount withdrawn
	 */
	public void transfer(BankAccount other, double amt) {
		try {
			if (this.getName().equals(other.getName()) && this.getBalance() >= amt) {
				this.transfer(other, amt);
			}
		}
		catch (IllegalArgumentException e) {
			if (!this.getName().equals(other.getName()) || (this.getBalance() < amt)) {
				throw new IllegalArgumentException("Transaction is not allowed to occur IllegalArgumentException");
			}
		}
		catch (NullPointerException e) {
			throw new NullPointerException("NullPointerException");
		}
	}
	/**
	 * adds interest to the account
	 */
	public void addInterest() {
		this.deposit(this.getBalance() * intRate); 
	}
	/**
	 * adds interest to the account at the end of each month
	 */
	public void endOfMonthUpdate() {
		this.addInterest();
	}
	
}