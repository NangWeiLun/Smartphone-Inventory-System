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

import model.Customer;
import controller.CustomerManager;


public class AddCustomerDialog extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField txtName = new JTextField();
	private JComboBox<String> cmbGender = new JComboBox<>(new String[] {"Male", "Female"});
	private JTextField txtPhoneNo = new JTextField();
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnReset = new JButton("Reset");

	public AddCustomerDialog(MainFrame frame)
	{
		super(frame, frame.getTitle(), true);

		JLabel title = new JLabel("Add Customer", JLabel.CENTER);
		JPanel center = new JPanel(new GridLayout(4, 2));
		JPanel south = new JPanel();

		add(title, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);

		center.add(new JLabel("Customer Name: ", JLabel.RIGHT));
		center.add(txtName);
		center.add(new JLabel("Gender: ", JLabel.RIGHT));
		center.add(cmbGender);
		center.add(new JLabel("Phone Number: ", JLabel.RIGHT));
		center.add(txtPhoneNo);
		

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
			Customer customer = new Customer();

			customer.setName(txtName.getText());
			customer.setGender(cmbGender.getSelectedItem().toString());
			customer.setPhoneNo(txtPhoneNo.getText());
		

			CustomerManager customerManager = new CustomerManager();

			try
			{
				int status = customerManager.add(customer);

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
			cmbGender.setSelectedIndex(0);
			txtPhoneNo.setText("");
			
		}
	}
}