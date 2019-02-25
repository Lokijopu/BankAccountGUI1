import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author Kevin Guo Period 6
 *
 */
public class BankAccountTest {

	public static void main(String[] args) {
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
		Scanner in = new Scanner(System.in);
		boolean terminated = false;
		System.out.print("Would you like to \nadd an account \nmake a transaction \nterminate the program\n" );
		String str1 = in.nextLine();
		switch (str1) {
		case "add an account":
			System.out.print("checking or savings?");
			String str2 = in.nextLine().toLowerCase().trim();
			switch(str2) {
			case "checking":
				System.out.print("name:\t");
				String name = in.nextLine();
				System.out.print("balance:\t");
				double bal = in.nextDouble();
				System.out.print("overdraft fee:\t");
				double odf = in.nextDouble();
				System.out.print("transaction fee:\t");
				double tf = in.nextDouble();
				System.out.print("number of free transactions:\t");
				int numTrans = in.nextInt();
				accounts.add(new CheckingAccount(name, bal, odf, tf, numTrans));
				break;
			case "savings":
				System.out.print("name:\t");
				String name2 = in.nextLine();
				System.out.print("balance:\t");
				double bal2 = in.nextDouble();
				System.out.print("rate:\t");
				double rate = in.nextDouble();
				System.out.print("minumum balance:\t");
				double mb = in.nextDouble();
				System.out.print("minumum balance fee:\t");
				double mbf = in.nextDouble();
				accounts.add(new SavingsAccount(name2, bal2, rate, mb, mbf));
				break;
			default:
				System.out.print("checking or savings?");
				str2 = in.nextLine().toLowerCase().trim();
				break;
			}
			System.out.print("Would you like to \nadd an account \nmake a transaction \nterminate the program\n" );
			str1 = in.nextLine().toLowerCase().trim();
		case "make a transaction":
			System.out.print("Would you like to \nwithdraw\ndeposit\ntransfer\nget account numbers");
			String str3 = in.nextLine().toLowerCase().trim();
			switch (str3) {
			case "withdraw":
				try {
				System.out.print("Input your name");
				String name = in.nextLine();
				System.out.print("Input your account number");
				int acctNum = in.nextInt();
				System.out.print("How much do you want to withdraw?");
				double amt = in.nextDouble();
				for (BankAccount i: accounts) {
					if (i.getName().equals(name) && i.getAccountNumber() == acctNum) {
						i.withdraw(amt);
					}
				}
				}
				catch (IllegalArgumentException e) {
					System.out.println("Transaction not authorized.");
				}
				catch (Exception e) {
					if (accounts.size() == 0) {
						System.out.println("There are no accounts");
					}
				}
				break;
			case "deposit":
				try {
					System.out.print("Input your name");
					String name = in.nextLine();
					System.out.print("Input your account number");
					int acctNum = in.nextInt();
					System.out.print("How much do you want to deposit?");
					double amt = in.nextDouble();
					for (BankAccount i: accounts) {
						if (i.getName().equals(name) && i.getAccountNumber() == acctNum) {
							i.deposit(amt);
						}
					}
					}
					catch (IllegalArgumentException e) {
						System.out.println("Transaction not authorized.");
					}
				catch (Exception e) {
					if (accounts.size() == 0) {
						System.out.println("There are no accounts");
					}
				}
				break;
			case "transfer":
				try {
					System.out.print("Account number of bank account transferred from");
					int acctNumFrom = in.nextInt();
					System.out.print("Account number of bank account transferred to");
					int acctNumTo = in.nextInt();
					System.out.print("How much do you want to transfer?");
					double amt = in.nextDouble();
					for (int i = 0; i < accounts.size(); i++) {
						String name1 = "";
						String name2 = " ";
						int index1 = 0;
						int index2 = 0;
						if (accounts.get(i).getAccountNumber() == acctNumFrom) {
							name1 = accounts.get(i).getName();
							index1 = i;
						}
						if (accounts.get(i).getAccountNumber() == acctNumTo) {
							name2 = accounts.get(i).getName();
							index2 = i;
						}
						if (name1.equals(name2) && index1 != index2) {
							accounts.get(index1).transfer(accounts.get(index2), amt);
						}
					}
				}
					catch (IllegalArgumentException e) {
						System.out.println("Transaction not authorized.");
					}
				catch (Exception e) {
					if (accounts.size() == 0) {
						System.out.println("There are no accounts");
					}
				}
				break;
			case "get account numbers":
				System.out.print("Input an account number.");
				int acctNum = in.nextInt();
				boolean hasAcctNum = false;
				for (BankAccount i : accounts) {
					if (i.getAccountNumber() == acctNum) {
						System.out.println(i.getAccountNumber());
						hasAcctNum = true;
					}
				}
				if (!hasAcctNum) {
					System.out.print("Invalid account number. Would you like to \nreenter an account number\nget account numbers");
					String str = in.nextLine();
					switch (str) {
					case "reenter an account number":
						System.out.print("Input an account number.");
						acctNum = in.nextInt();
					case "get account numbers":
						System.out.print("Input your name");
						str = in.nextLine();
						for (BankAccount i : accounts) {
							if (i.getName().equals(str)) {
								if (i instanceof CheckingAccount) {
									i.toString();
									System.out.println("checking");
									hasAcctNum = true;	
								} else if (i instanceof SavingsAccount) {
									i.toString();
									System.out.println("savings");
									hasAcctNum = true;	
								}
					}
				}
						
				break;
			default: 
				System.out.print("Would you like to \nwithdraw\ndeposit\ntransfer\nget account numbers");
				str3 = in.nextLine().toLowerCase().trim();
				break;
			}
				}
		case "terminate the program":
			terminated = true;
			break;
		default:
			System.out.print("Would you like to \nadd an account \nmake a transaction \nterminate the program\n");
			str2 = in.nextLine();
			break;
		}
		if (terminated) {
			System.out.println("Have a nice day.");
		}
	}
	}
}