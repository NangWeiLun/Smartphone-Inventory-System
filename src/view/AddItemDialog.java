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

public class AddItemDialog extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField txtName = new JTextField();
	private JTextField txtQuantity = new JTextField();
	private JTextField txtPrice = new JTextField();
		private JButton btnSubmit = new JButton("Submit");
	private JButton btnReset = new JButton("Reset");

	public AddItemDialog(MainFrame frame)
	{
		super(frame, frame.getTitle(), true);

		JLabel title = new JLabel("Add Item", JLabel.CENTER);
		JPanel center = new JPanel(new GridLayout(4, 2));
		JPanel south = new JPanel();

		add(title, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		

		center.add(new JLabel("Item Name: ", JLabel.RIGHT));
		center.add(txtName);
		
		center.add(new JLabel("Quantity: ", JLabel.RIGHT));
		center.add(txtQuantity);
		center.add(new JLabel("Price (RM): ", JLabel.RIGHT));
		center.add(txtPrice);

		south.add(btnSubmit);
		south.add(btnReset);

		btnSubmit.addActionListener(this);
		btnReset.addActionListener(this);

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		Object source = event.getSource();

		if (source == btnSubmit)
		{
			Item item = new Item();
			
			item.setName(txtName.getText());
			item.setQuantity(Integer.parseInt(txtQuantity.getText()));
			item.setPrice(Double.parseDouble(txtPrice.getText()));
			

			ItemManager itemManager = new ItemManager();

			try
			{
				int status = itemManager.add(item);

				if (status != 0)
					System.out.println("Success");
				else
					System.out.println("Fail");
			}
			catch (ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
			
			
		}
		else if (source == btnReset)
		{
			
			txtName.setText("");
			txtQuantity.setText("");
			txtPrice.setText("");
			
		}
	}
}