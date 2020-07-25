package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Item;
import model.TableModel;

public class ItemManager extends TableManager
{
	public int add(TableModel model) throws ClassNotFoundException,SQLException
	{
		Item item = (Item) model;

		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		Connection connection = DriverManager.getConnection("jdbc:derby:C:/Users/TWK/eclipse-workspace/dbCRMS","admin","123");

		PreparedStatement ps = connection.prepareStatement("INSERT INTO Item(Name,Quantity,Price)VALUES(?,?,?)");

		ps.setString(1, item.getName());
		ps.setInt(2, item.getQuantity());
		ps.setDouble(3, item.getPrice());

		int status = ps.executeUpdate();

		connection.close();

		return status;
	}

	public int update(TableModel model) throws ClassNotFoundException,SQLException
	{
		Item item = (Item) model;

		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		Connection connection = DriverManager.getConnection("jdbc:derby:C:/Users/TWK/eclipse-workspace/dbCRMS","admin","123");

		PreparedStatement ps = connection.prepareStatement("UPDATE Item SET Name=?,Quantity=?,Price=? WHERE ItemID=?");
		ps.setString(1, item.getName());
		ps.setInt(2, item.getQuantity());
		ps.setDouble(3, item.getPrice());
		ps.setInt(4, item.getItemID());
		
		int status = ps.executeUpdate();

		connection.close();

		return status;
	}
	

	public int delete(TableModel model) throws ClassNotFoundException, SQLException
	{
		Item item = (Item) model;
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		Connection connection = DriverManager.getConnection("jdbc:derby:C:/Users/TWK/eclipse-workspace/dbCRMS", "admin", "123");

		PreparedStatement ps = connection.prepareStatement("DELETE FROM Item WHERE ItemID=?");

		//ps.setString(1, keyword);

		ps.setInt(1, item.getItemID());
		int status = ps.executeUpdate();

		connection.close();
		
		return status;
	}
	
	public Vector<Item> searchItems(String keyword) throws ClassNotFoundException,SQLException
	{
		Vector<Item> items = new Vector<>();

		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		Connection connection = DriverManager.getConnection("jdbc:derby:C:/Users/TWK/eclipse-workspace/dbCRMS","admin","123");

		PreparedStatement ps = null;

		ps = connection.prepareStatement(" SELECT * FROM Item WHERE UPPER(Name) LIKE ? ");
		
		ps.setString(1, "%" + keyword.toUpperCase() + "%");

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