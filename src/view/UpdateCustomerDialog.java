package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CustomerManager;
import model.Customer;

public class UpdateCustomerDialog extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField txtName = new JTextField();
	private int txtCustomerID;
	private JComboBox<String> cmbGender = new JComboBox<>(new String[] {"Male", "Female"});
	private JTextField txtPhoneNo = new JTextField();
	
	
	private JButton btnUpdate = new JButton("Update");
	private JButton btnReset = new JButton("Reset");


	public UpdateCustomerDialog(MainFrame frame, Customer data)
	{
		super(frame, frame.getTitle(), true);
		
		JLabel title = new JLabel("Update - "+ data.getName(), JLabel.CENTER);
		JPanel center = new JPanel(new GridLayout(4, 2));
		JPanel south = new JPanel();

		add(title, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);

		center.add(new JLabel("Customer Name: ", JLabel.RIGHT));
		center.add(txtName);
		center.add(new JLabel("Gender: ", JLabel.RIGHT));
		center.add(cmbGender);
		center.add(new JLabel("Contact No.: ", JLabel.RIGHT));
		center.add(txtPhoneNo);
		txtCustomerID=data.getCustomerID();

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
			Customer customer = new Customer();
			customer.setName(txtName.getText());
			customer.setGender(cmbGender.getSelectedItem().toString());
			customer.setPhoneNo(txtPhoneNo.getText());
			customer.setCustomerID(txtCustomerID);
			
			CustomerManager customerManager = new CustomerManager();

			try
			{
				int status = customerManager.update(customer);

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
			cmbGender.setSelectedIndex(0);
			txtPhoneNo.setText("");
		}
	}

	

	
}