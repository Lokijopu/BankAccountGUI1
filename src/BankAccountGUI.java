import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BankAccountGUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<BankAccount> bankAccounts;
	private static double OVER_DRAFT_FEE;
	private static double TRANSACTION_FEE;
	private int numTransactions;
	private double intRate;
	private static double MIN_BAL;
	private static double MIN_BAL_FEE;
	
	public BankAccountGUI() { 
		setTitle("Bank Account");
		setBounds(100, 100, 350, 250);
		setLayout(null);
		
		JLabel name = new JLabel("Name:");
		name.setBounds(20, 20, 100, 30);
		add(name);
		JTextField nameTxtFld = new JTextField("");
		nameTxtFld.setBounds(80, 20, 100, 30);
		add(nameTxtFld);
		
		JLabel acctType = new JLabel("Account type:");
		acctType.setBounds(20, 60, 100, 30);
		add(acctType);
		JComboBox<String> aTMenu = new JComboBox<String>();
		aTMenu.addItem("Checking");
		aTMenu.addItem("Savings");
		aTMenu.setBounds(120, 60, 100, 30);
		add(aTMenu);
		
		JLabel initBal = new JLabel("Initial balance:");
		initBal.setBounds(20, 100, 100, 30);
		add(initBal);
		JTextField iBTxtFld = new JTextField("");
		iBTxtFld.setBounds(120, 100, 100, 30);
		add(iBTxtFld);
		
		JButton creAcc = new JButton("Create account");
		creAcc.setBounds(20, 140, 125, 30);
		add(creAcc);
		creAcc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (aTMenu.getSelectedItem().equals("Checking")) {
					BankAccount chAcc = new CheckingAccount(nameTxtFld.getText(), Double.parseDouble(iBTxtFld.getText()), OVER_DRAFT_FEE, TRANSACTION_FEE, numTransactions);
					bankAccounts.add(chAcc);
				} else if (aTMenu.getSelectedItem().equals("Savings")) {
					BankAccount svgsAcc = new SavingsAccount(nameTxtFld.getText(), Double.parseDouble(iBTxtFld.getText()), intRate, MIN_BAL, MIN_BAL_FEE);
					bankAccounts.add(svgsAcc);
				} 
			}
		
		});
		
		JButton disAllAcc = new JButton("Display all accounts");
		disAllAcc.setBounds(160, 140, 150, 30);
		add(disAllAcc);
		disAllAcc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (bankAccounts.size() == 0) System.out.println("Whoops");
				for (int i = 0; i < bankAccounts.size(); i++) {
					System.out.println(bankAccounts.get(i).toString());;
				}
			}
		
		});
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new BankAccountGUI();
		
	}

}

