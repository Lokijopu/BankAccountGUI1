import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BankAccountGUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<BankAccount> bankAccounts;
	
	public BankAccountGUI() { 
		bankAccounts = new ArrayList<BankAccount>();
		setBounds(300, 300, 300, 200);
		JMenuBar menubar = new JMenuBar();
		JMenu menu1 = new JMenu("Options 1-3");
		
		JMenuItem option1 = new JMenu("Option1");
		menu1.add(option1);
		
		JMenuItem option2 = new JMenu("Option2");
		menu1.add(option2);
		
		JMenuItem option3 = new JMenu("Option3");
		menu1.add(option3);
		menubar.add(menu1);
		this.setJMenuBar(menubar);
		//setLayout(new GridBagLayout());
		//GridBagConstraints gbc = new GridBagConstraints();
		//String[] arr = {"Option 1", "Option 2", "Option 3"};
		//JList options = new JList(arr);
		//add(options);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new BankAccountGUI();
		
	}

}
