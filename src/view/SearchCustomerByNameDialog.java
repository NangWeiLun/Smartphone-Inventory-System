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
import javax.swing.ListModel;

import controller.CustomerManager;
import model.Customer;

public class SearchCustomerByNameDialog extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField txtName = new JTextField(10);
	private JButton btnSearch = new JButton("Search");
	private JButton btnUpdate = new JButton("Update");
	private JList<Customer> lstCustomers = new JList<>();
	private MainFrame mainframe;
	
	public SearchCustomerByNameDialog(MainFrame frame)
	{
		super(frame, frame.getTitle(), true);
		
		mainframe = frame;
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
		
		pack();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		Object source = event.getSource();
		CustomerManager customerManager = new CustomerManager();
		if (source == btnSearch)
		{
			
			try
			{
				Vector<Customer> customers = customerManager.searchCustomers(txtName.getText());
				DefaultListModel<Customer> name = new DefaultListModel<>();
				
				for (Customer customer : customers)
					name.addElement(customer);
				
				
				lstCustomers.setModel(name);
			}
			catch (ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
		}
		else if (source == btnUpdate) 
		{
			ListModel<Customer> selected = lstCustomers.getModel();
		
			new UpdateCustomerDialog(mainframe,selected.getElementAt(lstCustomers.getSelectedIndex()));
		}
	}
}