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

import controller.ItemManager;
import model.Item;

public class UpdateItemDialog extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField txtName = new JTextField();
	private int txtItemID;
	private JTextField txtQuantity = new JTextField();
	private JTextField txtPrice = new JTextField();
	
	
	private JButton btnUpdate = new JButton("Update");
	private JButton btnReset = new JButton("Reset");


	public UpdateItemDialog(MainFrame frame, Item data)
	{
		super(frame, frame.getTitle(), true);
		
		JLabel title = new JLabel("Update - "+ data.getName(), JLabel.CENTER);
		JPanel center = new JPanel(new GridLayout(4, 2));
		JPanel south = new JPanel();

		add(title, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);

		center.add(new JLabel("Item Name: ", JLabel.RIGHT));
		center.add(txtName);
		center.add(new JLabel("Quantity: ", JLabel.RIGHT));
		center.add(txtQuantity);
		center.add(new JLabel("Price ", JLabel.RIGHT));
		center.add(txtPrice);
		txtItemID=data.getItemID();

		south.add(btnUpdate);
		south.add(btnReset);

		btnUpdate.addActionListener(this);
		btnReset.addActionListener(this);

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

		if (source == btnUpdate)
		{
			Item item = new Item();
			
			item.setName(txtName.getText());
			item.setQuantity(Integer.parseInt(txtQuantity.getText()));
			item.setPrice(Double.parseDouble(txtPrice.getText()));
			item.setItemID(txtItemID);
			

			ItemManager itemManager = new ItemManager();

			try
			{
				int status = itemManager.update(item);


				if (status != 0)
					System.out.println("Success");
				else
					System.out.println("Fail");
			}
			catch (ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
			
			// JOptionPane
		}
		else if (source == btnReset)
		{
			txtName.setText("");
			txtQuantity.setText("");
			txtPrice.setText("");
		}
	}

	

	
}