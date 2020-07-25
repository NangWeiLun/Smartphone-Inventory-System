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

import controller.PurchaseManager;


public class ViewReportByMonth extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JComboBox<String> month = new JComboBox<>(new String[] {"1", "2","3","4","5","6","7","8","9","10","11","12"});
	private JTextField purchaseNumber = new JTextField();
	private JTextField totalEarn = new JTextField();
	private JButton btnSubmit = new JButton("Submit");
	
	public ViewReportByMonth(MainFrame frame) {

		super(frame, frame.getTitle(), true);

		JLabel title = new JLabel("View Report By Month", JLabel.CENTER);
		JPanel center = new JPanel(new GridLayout(4, 2));
		JPanel south = new JPanel();

		add(title, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		center.add(new JLabel("Month: ", JLabel.RIGHT));
		center.add(month);
		center.add(new JLabel("Purchase Number: ", JLabel.RIGHT));
		center.add(purchaseNumber);
		center.add(new JLabel("Total Earn: RM ", JLabel.RIGHT));
		center.add(totalEarn);
		
		south.add(btnSubmit);

		btnSubmit.addActionListener(this);
		
		purchaseNumber.setEditable(false);
		totalEarn.setEditable(false);
		
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
			try {
				
			PurchaseManager purchaseManager = new PurchaseManager();
			purchaseNumber.setText(String.valueOf(purchaseManager.totalPurchaseMonth(month.getSelectedIndex()+1)));
			totalEarn.setText(String.valueOf(purchaseManager.totalEarnMonth(month.getSelectedIndex()+1)));
			}
			catch (ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}