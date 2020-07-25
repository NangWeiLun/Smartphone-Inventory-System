package model;

public class Item implements TableModel
{
	private int itemID;
	private String name;
	private int quantity;
	private double price;
	
	public int getItemID()
	{
		return itemID;
	}
	
	public void setItemID(int itemID)
	{
		this.itemID = itemID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	
	@Override
	public String toString()
	{
		return "Item Name: "+name + " Quantity: "+quantity+ " Price: RM " + price;
	}
}