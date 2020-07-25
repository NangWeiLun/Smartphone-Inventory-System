package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CartManager;
import model.Cart;
import model.Item;

public class AddQuantity extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField txtQuantity = new JTextField();
	private int txtItemID;
	private String txtName;
	private double txtPrice;
	private String txtCustomer;
		
	private JButton btnPurchase = new JButton("Add To Cart");
	
	public AddQuantity(MainFrame frame, String customer, Item data) {
		
		super(frame,"Smartphone Inventory System",true);
		
		JPanel title = new JPanel(new GridLayout(3, 1));
		JPanel center = new JPanel(new GridLayout(2, 1));
		JPanel south = new JPanel();

		add(title, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);

		title.add(new JLabel("Current Customer - " + customer , JLabel.CENTER));
		title.add(new JLabel("Item - " + data.getName(),JLabel.CENTER));
		center.add(new JLabel("Quantity ", JLabel.CENTER));
		center.add(txtQuantity);
		txtItemID=data.getItemID();
		txtName=data.getName();
		txtPrice=data.getPrice();
		txtCustomer=customer;

		south.add(btnPurchase);

		btnPurchase.addActionListener(this);

		//pack();
		setSize(400, 160);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent event)
	{
		Object source = event.getSource();
		
		if (source == btnPurchase)
		{
			
			Cart item=new Cart();
			item.setItemID(txtItemID);
			item.setQuantity(Integer.parseInt(txtQuantity.getText()));
			item.setName(txtName);
			item.setPrice(txtPrice);
			item.setCustomer(txtCustomer);
			
			CartManager cartManager=new CartManager();
			
			try
			{
				
				int status = cartManager.add(item);

				if (status != 0)
					System.out.println("Success");
				else
					System.out.println("Fail");
				
				dispose();
			}
			catch (ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
			
		}
	}
}