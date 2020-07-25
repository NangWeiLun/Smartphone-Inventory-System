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
import javax.swing.ListModel;
import controller.CartManager;
import model.Customer;
import model.Item;

public class ItemToCartDialog extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JButton btnToCart = new JButton("Checkout");
	private JButton btnPurchase = new JButton("Add Quantity");
	private JList<Item> lstItems = new JList<>();
	private MainFrame frame;
	private String txtCustomer;
	
	public ItemToCartDialog(MainFrame frame, Customer data) {
		
		super(frame,"Smartphone Inventory System",true);
		
		txtCustomer=data.getName();
		
		JLabel top = new JLabel("Current Customer: " + txtCustomer ,JLabel.CENTER);
		JPanel bottom = new JPanel();
		BoxLayout layout = new BoxLayout(top, BoxLayout.X_AXIS);
		
		top.setLayout(layout);
		
		
		
		bottom.add(btnPurchase);
		bottom.add(btnToCart);
		
		lstItems.setVisibleRowCount(5);
		
		btnToCart.addActionListener(this);
		btnPurchase.addActionListener(this);
		
		add(top, BorderLayout.NORTH);
		add(new JScrollPane(lstItems));
		add(bottom, BorderLayout.SOUTH);
		CartManager cartManager = new CartManager();
		
		try
		{
			Vector<Item> items = cartManager.searchItems();
			DefaultListModel<Item> name = new DefaultListModel<>();
			
			for (Item item : items)
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
		
		if (source == btnPurchase)
		{
			
			ListModel<Item> selected = lstItems.getModel();
			
			new AddQuantity(frame,txtCustomer,selected.getElementAt(lstItems.getSelectedIndex()));
		}else if(source==btnToCart) {
			new CheckOutDialog(frame,txtCustomer);
		}
	}
}