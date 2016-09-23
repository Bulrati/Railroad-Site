package ua.nure.nastenko.SummaryTask4.db;

import java.sql.*;

public class ConnectionFactory {
	
	public static String driver = "com.mysql.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost:3306/";
	public static String user = "root";
	public static String pass = "";
	private static Connection con = null;

	private ConnectionFactory(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		if(con == null){
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, user, pass);
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return con;
	}
	
}
