package com.talentsearch.db.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MySQLConnection {

	private static Connection connection = null;
	private static String url = "";
	
	public static Connection getConnection() throws Exception{
		{ 
			if(connection == null)
				connect();
			return connection;
		}
	}
	
	private static Connection connect() throws Exception
	{
		try
		{
			loadconfig("dbdetails.properties");
			Class.forName("com.mysql.jdbc.Driver") ;
	         connection = DriverManager.getConnection(url);
	         return connection;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void close()
	{
		try
		{
			if(connection != null) connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private static void loadconfig(String configfilename)
	{
		 try
		 {
			 System.out.println(configfilename);
			 Properties props = System.getProperties();
			 FileInputStream fs = new FileInputStream(configfilename);
			 props.load(fs);
			 fs.close();
			 String server = props.getProperty("mysql.server");
			 String port = props.getProperty("mysql.port"); 
			 String schema = props.getProperty("mysql.schema");
			 String user = props.getProperty("mysql.user");
			 String password=props.getProperty("mysql.password");

			 
			 url = "jdbc:mysql://" + server + ":" + port + "/" + schema + "?user=" + user + "&password=" + password; 
			 
		}
			 catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
