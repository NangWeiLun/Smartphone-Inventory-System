package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Vector;

import model.Cart;
import model.Purchase;
import model.TableModel;

public class PurchaseManager extends TableManager
{
	
	public Vector<Cart> searchItems(String customer) throws ClassNotFoundException,SQLException
	{
		Vector<Cart> items = new Vector<>();

		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		Connection connection = DriverManager.getConnection("jdbc:derby:C:/Users/TWK/eclipse-workspace/dbCRMS","admin","123");

		PreparedStatement ps = null;

		ps = connection.prepareStatement(" SELECT * FROM Cart WHERE Customer LIKE ? ");
		
		ps.setString(1, customer);

		ResultSet rs = ps.executeQuery();

		while (rs.next())
		{
			Cart item = new Cart();
			item.setItemID(rs.getInt("CartID"));
			item.setName(rs.getString("Name"));
			item.setQuantity(rs.getInt("Quantity"));
			item.setPrice(rs.getDouble("Price"));
			items.add(item);
		}

		connection.close();

		return items;		
	}
	
	public Vector<Purchase> searchItems2(String customer) throws ClassNotFoundException,SQLException
	{
		Vector<Purchase> items = new Vector<>();

		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		Connection connection = DriverManager.getConnection("jdbc:derby:C:/Users/TWK/eclipse-workspace/dbCRMS","admin","123");

		PreparedStatement ps = null;

		ps = connection.prepareStatement(" SELECT * FROM Purchase WHERE Customer LIKE ? ");
		
		ps.setString(1, customer);

		ResultSet rs = ps.executeQuery();

		while (rs.next())
		{
			Purchase item = new Purchase();
			item.setItemID(rs.getInt("PurchaseID"));
			item.setName(rs.getString("Name"));
			item.setQuantity(rs.getInt("Quantity"));
			item.setPrice(rs.getDouble("Price"));
			item.setCustomer(rs.getString("Customer"));
			item.setDate(rs.getString("Date"));
			items.add(item);
		}

		connection.close();

		return items;		
	}
	
	public int delete(int i) throws ClassNotFoundException, SQLException
	{
		int item = i;
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		Connection connection = DriverManager.getConnection("jdbc:derby:C:/Users/TWK/eclipse-workspace/dbCRMS","admin","123");

		PreparedStatement ps = connection.prepareStatement("DELETE FROM Cart WHERE CartID=?");

		ps.setInt(1, item);
		int status = ps.executeUpdate();

		connection.close();
		
		return status;
	}
	
	public int totalPurchaseMonth(int month) throws ClassNotFoundException,SQLException
	{
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		Connection connection = DriverManager.getConnection("jdbc:derby:C:/Users/TWK/eclipse-workspace/dbCRMS","admin","123");

		PreparedStatement ps = null;

		ps = connection.prepareStatement("SELECT COUNT(PurchaseId) FROM Purchase WHERE Date LIKE ? ");
		
		ps.setString(1, "%-" + String.valueOf(month) + "-%");

		ResultSet rs = ps.executeQuery();
		int count = 0;
		if(rs.next())
			count = rs.getInt("1");
		connection.close();

		return count;		
	}
	
	public double totalEarnMonth(int month) throws ClassNotFoundException,SQLException
	{
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		Connection connection = DriverManager.getConnection("jdbc:derby:C:/Users/TWK/eclipse-workspace/dbCRMS","admin","123");

		PreparedStatement ps = null;

		ps = connection.prepareStatement("SELECT SUM(Quantity*Price) FROM Purchase WHERE Date LIKE ? ");
		
		ps.setString(1, "%-" + String.valueOf(month) + "-%");

		ResultSet rs = ps.executeQuery();

		double sum = 0.00;
		if(rs.next())
			sum = rs.getDouble("1");
		connection.close();

		return sum;		
	}

	public Vector<String> totalItemPurchase() throws ClassNotFoundException,SQLException
	{
		Vector<String> items = new Vector<>();

		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		Connection connection = DriverManager.getConnection("jdbc:derby:C:/Users/TWK/eclipse-workspace/dbCRMS","admin","123");

		PreparedStatement ps = connection.prepareStatement("SELECT Name, SUM(Price), SUM(Quantity) FROM Purchase GROUP BY Name");
		
		ResultSet rs = ps.executeQuery();

		while (rs.next())
		{
			String string = "Model: " + rs.getString("Name")+ "     "+"Total Earn: RM"+ rs.getDouble("2") + "    "+"Quantity Sold: "+rs.getInt("3");
			items.add(string);
		}
		
		connection.close();

		return items;	
	}


	@Override
	public int add(TableModel model) throws ClassNotFoundException, SQLException {
	
		return 0;
	}

	public int add(String txtCustomer) throws ClassNotFoundException, SQLException {

		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		Connection connection = DriverManager.getConnection("jdbc:derby:C:/Users/TWK/eclipse-workspace/dbCRMS","admin","123");

		PreparedStatement ps = null;

		ps = connection.prepareStatement(" SELECT * FROM Cart WHERE Customer LIKE ? ");
		
		ps.setString(1, txtCustomer);

		ResultSet rs = ps.executeQuery();

		String date= LocalDate.now().toString();
		int status = 0;
		
		while (rs.next())
		{
			PreparedStatement ps2 = null;
			ps2=connection.prepareStatement(" INSERT INTO Purchase(Name,Quantity,Price,Customer,Date)VALUES(?,?,?,?,?) ");
			
			String itemName=rs.getString("Name");
			int quantityBuy=rs.getInt("Quantity");
			ps2.setString(1,itemName );
			ps2.setInt(2,quantityBuy);
			ps2.setDouble(3, rs.getDouble("Price"));
			ps2.setString(4, rs.getString("Customer"));
			ps2.setString(5, date);
			
			status = ps2.executeUpdate();
			
			PreparedStatement ps5 = null;
			ps5 = connection.prepareStatement(" SELECT * FROM Item WHERE Name=? ");
			
			ps5.setString(1, itemName);
			
			int stockLeft=0;
			ResultSet rs5 = ps5.executeQuery();
			while(rs5.next()) {
				stockLeft=rs5.getInt("Quantity");
			}
			
			int balance=stockLeft-quantityBuy;
			
			PreparedStatement ps4 = null;
			ps4=connection.prepareStatement(" UPDATE Item SET Quantity=? WHERE Name=? ");
			
			ps4.setInt(1, balance);
			ps4.setString(2,itemName);
			
			ps4.executeUpdate();
		}
		
		PreparedStatement ps3 = null;
		ps3=connection.prepareStatement(" DELETE FROM Cart WHERE Customer=? ");
		
		ps3.setString(1, txtCustomer);
		ps3.executeUpdate();

		connection.close();
		
		return status;
	}
}