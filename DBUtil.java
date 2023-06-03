package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DBUtil {
//making connection
	
	public static Connection getDbConnection() throws SQLException, ClassNotFoundException
	{
		// Step 1:add jar files
				// Step Load Driver.class

				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");//Reflection
					System.out.println("--driver get loaded---");
					//driver will do connection with Oracle db
					//URL:
					
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					String username = "system";
					String password = "root123";
					
				Connection con=	DriverManager.getConnection(url, username, password);
				System.out.println("----con----");
					return con;
					
				} 
		
				catch ( SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw  e;
				}
	}
	
	
}
