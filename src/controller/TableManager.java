package controller;

import java.sql.SQLException;

import model.TableModel;

public abstract class TableManager
{
	public abstract int add(TableModel model) throws ClassNotFoundException, SQLException;

	public int update(TableModel model) throws ClassNotFoundException, SQLException 
	{
		return 0;
	}
}