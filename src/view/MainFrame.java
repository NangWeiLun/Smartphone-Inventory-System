package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JMenuItem addItemMenuItem = new JMenuItem("Add Item");
	private JMenuItem searchItemByName=new JMenuItem("Search Item");
	private JMenuItem deleteItemMenuItem=new JMenuItem("Delete Item");
	
	private JMenuItem addCustomerMenuItem=new JMenuItem("Add Customer");
	private JMenuItem searchCustomerByName=new JMenuItem("Search Customer");
	
	
	private JMenuItem addToCart=new JMenuItem("Add New Purchase");
	private JMenuItem view=new JMenuItem("View Purchase History");
	
	private JMenuItem viewReportByMonth=new JMenuItem("View Report By Month");
	private JMenuItem viewReportByItem=new JMenuItem("View Report By Item");

	
	public MainFrame()
	{
		super("Smartphone Inventory System");
		
		JMenuBar menuBar = new JMenuBar();
		JMenu itemMenu = new JMenu("Item");
		JMenu customerMenu = new JMenu("Customer");
		JMenu purchaseMenu=new JMenu("Purchase");
		JMenu report = new JMenu("Report");
		
		JLabel title = new JLabel("Welcome to " + getTitle(), JLabel.CENTER);
		
		menuBar.add(itemMenu);
		menuBar.add(customerMenu);
		menuBar.add(purchaseMenu);
		menuBar.add(report);
		
		itemMenu.add(addItemMenuItem);
		itemMenu.add(searchItemByName);
		itemMenu.add(deleteItemMenuItem);
		
		customerMenu.add(addCustomerMenuItem);
		customerMenu.add(searchCustomerByName);
		
		
		purchaseMenu.add(addToCart);
		purchaseMenu.add(view);
		
		report.add(viewReportByMonth);
		report.add(viewReportByItem);
		
		add(title);
		
		addItemMenuItem.addActionListener(this);
		searchItemByName.addActionListener(this);
		deleteItemMenuItem.addActionListener(this);
		
		addCustomerMenuItem.addActionListener(this);
		searchCustomerByName.addActionListener(this);
		

		addToCart.addActionListener(this);
		view.addActionListener(this);
		
		viewReportByMonth.addActionListener(this);
		viewReportByItem.addActionListener(this);
		//pack();
		setSize(400, 150);
		setJMenuBar(menuBar);
		//setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		Object source = event.getSource();
		
		if (source == addItemMenuItem)
			new AddItemDialog(this);
		
		else if(source==searchItemByName)
			new SearchItemByNameDialog(this);
		
		else if (source==addCustomerMenuItem)
			new AddCustomerDialog(this);
		
		else if (source==searchCustomerByName)
			new SearchCustomerByNameDialog(this);
		
		else if(source==deleteItemMenuItem)
		{
			new DeleteItemDialog(this);
		}
		
		else if (source==addToCart)
			new CustomerCartDialog(this);
		else if(source==view)
			new ViewPurchaseDialog(this);
		
		else if (source==viewReportByMonth)
		{
			new ViewReportByMonth(this);
		}
		
		else if (source==viewReportByItem)
		{
			new ViewReportByItem(this);
		}
		
		
	}
	
	

	public static void main(String[] args)
	{
		new MainFrame();
	}
}