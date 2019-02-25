/**
 * 
 * @author Kevin Guo Period 6
 *
 */
public class CheckingAccount extends BankAccount{
	private static double OVER_DRAFT_FEE;
	private static double TRANSACTION_FEE;
	private int numTransactions;
	/**
	 * Creates a Checking account
	 * @param n name
	 * @param b balance
	 * @param odf overdraft fee
	 * @param tf transaction fee
	 * @param numTransactions number of free transactions
	 */
	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans) {
		super(n, b);
		try {
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		numTransactions = freeTrans;
		}
		catch (IllegalArgumentException e) {
			if (b < 0) {
				System.out.println("Problem");
			}
		}
	}
	/**
	 * Creates a Checking account with a balance of 0
	 * @param n name
	 * @param odf overdraft fee
	 * @param tf transaction fee
	 * @param freeTrans number of free transactions
	 */
	public CheckingAccount(String n, double odf, double tf, int freeTrans) {
		super(n, 0);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		numTransactions = freeTrans;
	}
	/**
	 * Add a sum of money to the bank account, increase the number of transactions, and adds a transaction fee.
	 * @param amt	amount deposited
	 */
	public void deposit(double amt) {
		try {
			this.deposit(amt);
			numTransactions++;
			this.withdraw(TRANSACTION_FEE);
		}
		catch (NullPointerException e) {
			throw new NullPointerException("NullPointerException");
		}
		catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Transaction not authorized; IllegalArgumentException");
		}
	}
	/**
	 * Deducts a sum of money from the bank account, increase the number of transactions, and adds a transaction fee.
	 * @param amt	amount withdrawn
	 */
	public void withdraw(double amt) {
		try {
			if (this.getBalance() >= amt) {
				this.withdraw(amt);
				numTransactions++;
				this.withdraw(TRANSACTION_FEE);
			} else if (this.getBalance() <= amt && this.getBalance() > 0){
				this.withdraw(amt);
				numTransactions++;
				this.withdraw(TRANSACTION_FEE);
				this.withdraw(OVER_DRAFT_FEE);
			}
		}
		catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Transaction not authorized; IllegalArgumentException");
		}
		catch (NullPointerException e) {
			throw new NullPointerException("NullPointerException");
		}
		catch (Exception e) {
			if (this.getBalance() < 0) {
				System.out.println("Transaction not authorized; balance is negative");
			}
		}
	}
	/**
	 * Transfers money from one bank account to another bank account
	 * @param other the account where the money is being transfered to
	 * @param amt	amount transferred 
	 */
	public void transfer(BankAccount other, double amt) {
		try {
			if (this.getName().equals(other.getName()) && this.getBalance() >= amt) {
				this.transfer(other, amt);
				this.withdraw(TRANSACTION_FEE);
				numTransactions++;
			}
		}
		catch (IllegalArgumentException e) {
			if (this.getBalance() < amt + TRANSACTION_FEE || (!this.getName().equals(other.getName()))) {
				throw new IllegalArgumentException("Transaction is not allowed to occur IllegalArgumentException");
			}
		}
		catch (NullPointerException e) {
			throw new NullPointerException("NullPointerException has occurred");
		}
	}
	/**
	 * Resets the number of transactions
	 */
	public void endOfMonthUpdate() {
		numTransactions = 0;
	}
}