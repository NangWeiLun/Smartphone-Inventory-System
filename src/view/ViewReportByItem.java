package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.PurchaseManager;

public class ViewReportByItem extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JList<String> lstItems = new JList<>();
	
	public ViewReportByItem(MainFrame frame)
	{
		super(frame, frame.getTitle(), true);
		
		JPanel top = new JPanel();
		BoxLayout layout = new BoxLayout(top, BoxLayout.X_AXIS);
		
		top.setLayout(layout);
		
		top.add(new JLabel("Item Sold: "));
				
		lstItems.setVisibleRowCount(10);
				
		add(top, BorderLayout.NORTH);
		add(new JScrollPane(lstItems));
		PurchaseManager purchaseManager = new PurchaseManager();
		
		try
		{
			Vector<String> items = purchaseManager.totalItemPurchase();
			DefaultListModel<String> name = new DefaultListModel<>();
			
			for (String item : items)
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
	}
}