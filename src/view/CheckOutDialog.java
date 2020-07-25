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
import controller.PurchaseManager;
import model.Cart;

public class CheckOutDialog extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JButton btnRemoveItem = new JButton("Remove Selected Item");
	private JButton btnPurchase = new JButton("Confirm Purchase");
	private JList<Cart> lstItems = new JList<>();
	private String txtCustomer;
	
	public CheckOutDialog(MainFrame frame, String customer) {
		
		super(frame,"Smartphone Inventory System",true);
		
		txtCustomer=customer;
		
		JLabel top = new JLabel("Current Customer: " + txtCustomer ,JLabel.CENTER);
		JPanel bottom = new JPanel();
		BoxLayout layout = new BoxLayout(top, BoxLayout.X_AXIS);
		
		top.setLayout(layout);
		
				
		bottom.add(btnRemoveItem);
		bottom.add(btnPurchase);
		
		lstItems.setVisibleRowCount(20);
		
		btnRemoveItem.addActionListener(this);
		btnPurchase.addActionListener(this);
		
		add(top, BorderLayout.NORTH);
		add(new JScrollPane(lstItems));
		add(bottom, BorderLayout.SOUTH);
		PurchaseManager purchaseManager = new PurchaseManager();
		
		try
		{
			Vector<Cart> items = purchaseManager.searchItems(txtCustomer);
			DefaultListModel<Cart> name = new DefaultListModel<>();
			
			for (Cart item : items)
				name.addElement(item);
		
			lstItems.setModel(name);
		}
		
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		
		pack();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent event)
	{	
		Object source = event.getSource();
	
		PurchaseManager purchaseManager=new PurchaseManager();
		
		if (source == btnPurchase)
		{
			try {
				int status=purchaseManager.add(txtCustomer);
				
				if (status != 0)
					System.out.println("Success");
				else
					System.out.println("Fail");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else if(source==btnRemoveItem) {
			
			Cart selected = lstItems.getSelectedValue();
			
			try
			{
				int status=purchaseManager.delete(selected.getItemID());
			

				if (status != 0)
				{
					System.out.println("Success");
				
				}
				else
				{
					System.out.println("Fail");
					
				}
			}
			catch (ClassNotFoundException|SQLException e)
			{
				e.printStackTrace();
			}
			
			dispose();
			MainFrame frame = null;
			new CheckOutDialog(frame, txtCustomer);
		}
	}
}