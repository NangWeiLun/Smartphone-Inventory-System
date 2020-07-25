package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Customer;
import model.TableModel;

public class CustomerManager extends TableManager
{
	public int add(TableModel model) throws ClassNotFoundException,SQLException
	{
		Customer customer = (Customer) model;

		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		Connection connection = DriverManager.getConnection("jdbc:derby:C:/Users/TWK/eclipse-workspace/dbCRMS","admin","123");

		PreparedStatement ps = connection.prepareStatement("INSERT INTO Customer(Name,Gender,PhoneNo)VALUES(?,?,?)");

		ps.setString(1, customer.getName());
		ps.setString(2, customer.getGender());
		ps.setString(3, customer.getPhoneNo());

		int status = ps.executeUpdate();

		connection.close();

		return status;
	}

	public int update(TableModel model) throws ClassNotFoundException,SQLException
	{
		Customer customer = (Customer) model;
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		
		Connection connection = DriverManager.getConnection("jdbc:derby:C:/Users/TWK/eclipse-workspace/dbCRMS","admin","123");
		
		PreparedStatement ps = connection.prepareStatement("UPDATE Customer SET Name=?,Gender=?,PhoneNo=? WHERE CustomerID=?");

		ps.setString(1, customer.getName());
		ps.setString(2, customer.getGender());
		ps.setString(3, customer.getPhoneNo());
		ps.setInt(4, customer.getCustomerID());
		
		int status = ps.executeUpdate();

		connection.close();

		return status;
	}
	
	public Vector<Customer> searchCustomers(String keyword) throws ClassNotFoundException,SQLException
	{
		Vector<Customer> customers = new Vector<>();

		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		Connection connection = DriverManager.getConnection("jdbc:derby:C:/Users/TWK/eclipse-workspace/dbCRMS","admin","123");

		PreparedStatement ps = null;

		ps = connection.prepareStatement(" SELECT * FROM Customer WHERE UPPER(Name) LIKE ? ");
		
		ps.setString(1, "%" + keyword.toUpperCase() + "%");

		ResultSet rs = ps.executeQuery();

		while (rs.next())
		{
			Customer customer = new Customer();
			customer.setCustomerID(rs.getInt("CustomerID"));
			customer.setName(rs.getString("Name"));
			customer.setGender(rs.getString("Gender"));
			customer.setPhoneNo(rs.getString("PhoneNo"));
			customers.add(customer);
		}

		connection.close();

		return customers;		
	}

}