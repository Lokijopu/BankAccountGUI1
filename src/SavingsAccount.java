/**
 * 
 * @author Kevin Guo Period 6
 *
 */
public class SavingsAccount extends BankAccount{

	private double intRate;
	private static double MIN_BAL;
	private static double MIN_BAL_FEE;
	SavingsAccount(String n, double b, double r, double mb, double mbf) {
		super(n, b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	SavingsAccount(String n, double r, double mb, double mbf) {
		super(n, 0);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
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
	
	public void addInterest() {
		this.deposit(this.getBalance() * intRate); 
	}
	public void endOfMonthUpdate() {
		this.addInterest();
	}
	
}
