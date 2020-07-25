package model;

public class Customer implements TableModel
{
	private int customerID;
	private String name;
	private String gender;
	private String phoneNo;
	
	public int getCustomerID()
	{
		return customerID;
	}
	
	public void setCustomerID(int customerID)
	{
		this.customerID = customerID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getGender()
	{
		return gender;
	}
	
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	public String getPhoneNo()
	{
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo = phoneNo;
	}
	
	
	@Override
	public String toString()
	{
		return "Name: "+ name + "\n Gender: " + gender + " \nContact No.:" + phoneNo;
	}
}