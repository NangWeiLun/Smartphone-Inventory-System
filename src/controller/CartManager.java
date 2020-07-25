package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Cart;
import model.Item;
import model.TableModel;

public class CartManager extends TableManager
{
	public int add(TableModel model) throws ClassNotFoundException,SQLException
	{
		Cart item = (Cart) model;

		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		Connection connection = DriverManager.getConnection("jdbc:derby:C:/Users/TWK/eclipse-workspace/dbCRMS","admin","123");

		PreparedStatement ps = connection.prepareStatement("INSERT INTO Cart(Name,Quantity,Price,Customer)VALUES(?,?,?,?)");

		ps.setString(1, item.getName());
		ps.setInt(2, item.getQuantity());
		ps.setDouble(3, item.getPrice()*item.getQuantity());
		ps.setString(4, item.getCustomer());
		
		int status = ps.executeUpdate();

		connection.close();

		return status;
	}

	public int update(TableModel model)
	{
		

		System.out.println("Updating customer in the database");
		return 0;
	}
	
	public Vector<Item> searchItems() throws ClassNotFoundException,SQLException
	{
		Vector<Item> items = new Vector<>();

		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		Connection connection = DriverManager.getConnection("jdbc:derby:C:/Users/TWK/eclipse-workspace/dbCRMS","admin","123");

		PreparedStatement ps = null;

		ps = connection.prepareStatement(" SELECT * FROM Item");

		ResultSet rs = ps.executeQuery();

		while (rs.next())
		{
			Item item = new Item();
			item.setItemID(rs.getInt("ItemID"));
			item.setName(rs.getString("Name"));
			item.setQuantity(rs.getInt("Quantity"));
			item.setPrice(rs.getDouble("Price"));
			items.add(item);
		}

		connection.close();

		return items;		
	}

	
}