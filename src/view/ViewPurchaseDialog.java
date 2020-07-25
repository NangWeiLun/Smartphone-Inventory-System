package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import controller.PurchaseManager;
import model.Purchase;

public class ViewPurchaseDialog extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField txtName = new JTextField(10);
	private JButton btnSearch = new JButton("Search");
	private JButton btnUpdate = new JButton("Close");
	private JList<Purchase> lstCustomers = new JList<>();
	public ViewPurchaseDialog(MainFrame frame)
	{
		super(frame, frame.getTitle(), true);
		
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		BoxLayout layout = new BoxLayout(top, BoxLayout.X_AXIS);
		
		top.setLayout(layout);
		
		top.add(new JLabel("Customer Name: "));
		top.add(txtName);
		top.add(btnSearch);
		
		bottom.add(btnUpdate);
		
		lstCustomers.setVisibleRowCount(5);
		
		btnSearch.addActionListener(this);
		btnUpdate.addActionListener(this);
		
		add(top, BorderLayout.NORTH);
		add(new JScrollPane(lstCustomers));
		add(bottom, BorderLayout.SOUTH);
		
		setSize(600,300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		Object source = event.getSource();
		PurchaseManager purchaseManager = new PurchaseManager();
		if (source == btnSearch)
		{
			try
			{
				Vector<Purchase> purchases = purchaseManager.searchItems2(txtName.getText());
				DefaultListModel<Purchase> name = new DefaultListModel<>();
				
				for (Purchase purchase : purchases)
					name.addElement(purchase);
				
				lstCustomers.setModel(name);
			}
			catch (ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
		}
		else if (source == btnUpdate) 
		{
			dispose();
		}
	}
}