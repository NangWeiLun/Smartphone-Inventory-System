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

import controller.ItemManager;
//import model.Customer;
import model.Item;

public class DeleteItemDialog extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField txtName = new JTextField(10);

	private JButton btnSearch = new JButton("Search");
	private JButton btnDelete = new JButton("Delete");
	private JList<Item> lstItems = new JList<>();
	
	
	public DeleteItemDialog(MainFrame frame)
	{
		super(frame, frame.getTitle(), true);
		
		
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		BoxLayout layout = new BoxLayout(top, BoxLayout.X_AXIS);
		
		top.setLayout(layout);
		
		top.add(new JLabel("Item Name: "));
		top.add(txtName);
		top.add(btnSearch);
		
		bottom.add(btnDelete);
		
		lstItems.setVisibleRowCount(5);
		
		btnSearch.addActionListener(this);
		btnDelete.addActionListener(this);
		
		add(top, BorderLayout.NORTH);
		add(new JScrollPane(lstItems));
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
		ItemManager itemManager = new ItemManager();
		
		if (source == btnSearch)
		{
			
			try
			{
				Vector<Item> items = itemManager.searchItems(txtName.getText());
				DefaultListModel<Item> name = new DefaultListModel<>();
				
				for (Item item : items)
					name.addElement(item);
			
				
				lstItems.setModel(name);
			}
			catch (ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
		}
		else if (source==btnDelete)
		{
			ListModel<Item> selected = lstItems.getModel();		

			
			try
			{
				int status=itemManager.delete(selected.getElementAt(lstItems.getSelectedIndex()));
			

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
		}
	}
}