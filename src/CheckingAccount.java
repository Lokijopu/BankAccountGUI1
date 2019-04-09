/**
 * 
 * @author Kevin Guo Period 6
 *
 */
public class CheckingAccount extends BankAccount{
	private static double OVER_DRAFT_FEE;
	private static double TRANSACTION_FEE;
	private static double FREE_TRANS;
	private int numTransactions;
	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans) {
		super(n, b);
		try {
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
		numTransactions = 0;
		}
		catch (IllegalArgumentException e) {
			if (b < 0) {
				System.out.println("Problem");
			}
		}
	}
	
	public CheckingAccount(String n, double odf, double tf, int freeTrans) {
		super(n, 0);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
		numTransactions = 0;
	}
	
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
		catch (Exception e) {
			if (numTransactions >= FREE_TRANS) {
				System.out.println("Transaction not authorized; no more transactions can be issued.");
			}
		}
	}
	
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
			if (numTransactions >= FREE_TRANS) {
				System.out.println("Transaction not authorized; no more transactions can be issued.");
			}
		}
	}
	
	public void transfer(BankAccount other, double amt) {
		try {
			if (this.getName().equals(other.getName()) && this.getBalance() >= amt) {
				this.transfer(other, amt);
				this.withdraw(TRANSACTION_FEE);
				numTransactions++;
			}
		}
		catch (IllegalArgumentException e) {
			if (this.getBalance() < amt + TRANSACTION_FEE || (numTransactions >= FREE_TRANS) || (!this.getName().equals(other.getName()))) {
				throw new IllegalArgumentException("Transaction is not allowed to occur IllegalArgumentException");
			}
		}
		catch (NullPointerException e) {
			throw new NullPointerException("NullPointerException has occurred");
		}
	}
	public void endOfMonthUpdate() {
		numTransactions = 0;
	}
}
