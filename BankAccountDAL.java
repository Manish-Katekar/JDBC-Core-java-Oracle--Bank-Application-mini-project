package com.pojo;

import static com.util.DBUtil.getDbConnection;//static import
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDAL {
	// connection
	private static Connection con;// single copy

	static {
		// static methods gt access by ClassName.MethodName
		// only for static methods
		// we can import static methods so that no need to use class name to invoke
		// method
		// import static DbUtil.getDbConnection;
		try {
			con = getDbConnection();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //
	}

	// write all operations that we can perform on BankAccount
	// get all BankAccount
	public static List<BankAccount> getAllAccounts() {// step 1:connection (static block)
														// step 2:write sql statement
		String str = "select * from BankAccount";
		List<BankAccount> list = new ArrayList<BankAccount>();

		// step 3:create statement
		try {
			Statement stmt = con.createStatement();

			// for select sql use executeQuery
			// for insert ,update,delete sql use executeUpdate
			// execute query
			ResultSet rset = stmt.executeQuery(str);
			if (rset != null) {
				System.out.println("Records are present");
				// process records

				while (rset.next()) {
					BankAccount dto = new BankAccount(rset.getInt("actid"), rset.getString("custname"),
							rset.getString("username"), rset.getString("password"), rset.getDouble("balance"));
					list.add(dto);
					
					
				}

			} else 
			{
				System.out.println("no rows selected");
				
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// Create new Account

	public static int createNewAccount(BankAccount newAct) {
		// create insert
		// prepared stmt:parameter and assign parameter
		// executeUpdate(query)
		// return i
		return 0;
	}
	// UPdate Existing Account details
	// Remove Account

	
	
	
	
	///////////////////////////
	public static BankAccount getAccountById(int actid) {

		String sql = "select * from BankAccount where actid=" + actid;
		System.out.println(sql);
		BankAccount dto = null;
		Statement stmt;
		try {
			stmt = con.createStatement();

			ResultSet rset = stmt.executeQuery(sql);
			if (rset != null) {
				if (rset.next()) {
					dto = new BankAccount(rset.getInt("actid"), rset.getString("custname"), rset.getString("username"),
							rset.getString("password"), rset.getDouble("balance"));
				}
			} else {
				System.out.println("no rows selected");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;// null or not null

	}
	
	
	
	
	///////////////////////
	public static BankAccount validateLogin(String username,String password)
	{
		String sql = "select * from BankAccount where username=? and  password=?";
		System.out.println(sql);
		BankAccount dto = null;
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			ResultSet rset = stmt.executeQuery();
			if (rset != null) {
				if (rset.next()) {
					dto = new BankAccount(rset.getInt("actid"), rset.getString("custname"), rset.getString("username"),
							rset.getString("password"), rset.getDouble("balance"));
				}
			} else {
				System.out.println("no rows selected");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;// null or not null

	}
	
	
	
	///////////////////////////
	public static BankAccount validateid(int rid)
	{
		String sql = "select * from BankAccount where actid=?";
		System.out.println(sql);
		BankAccount dto = null;
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, rid);
			
			
			ResultSet rset = stmt.executeQuery();
			if (rset != null) {
				if (rset.next()) {
					dto = new BankAccount(rset.getInt("actid"), rset.getString("custname"), rset.getString("username"),
							rset.getString("password"), rset.getDouble("balance"));
				}
			} else {
				System.out.println("no rows selected");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;// null or not null

	}

	
	
	//////////////////////////
	public static void withdraw(double amount,int actid) {
		String sql = "update BankAccount set balance=(select (balance - ?) from BankAccount where actid=?) where actid=?";
		try
		{
	
		PreparedStatement stmt;
		stmt = con.prepareStatement(sql);
		stmt.setDouble(1, amount);
		stmt.setInt(2, actid);
		stmt.setInt(3, actid);
		stmt.executeUpdate();
		System.out.println("Withdrawl Succesul !!!");
		
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			}
	}
		
	
	
	///////////////////////////////
		public static void deposit(double amount,int actid) {
			String sql = "update BankAccount set balance=(select (balance + ?) from BankAccount where actid=?) where actid=?";
			try
			{
		
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, amount);
			stmt.setInt(2, actid);
			stmt.setInt(3, actid);
			stmt.executeUpdate();
			System.out.println("Deposited amount Succesull !!!");
			
			}
			catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
		
		
		////////////////////////////
			public static void delete(int actid) {
				String sql = "delete from BankAccount where actid=? ";
				try
				{
			
				PreparedStatement stmt;
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, actid);
				stmt.executeUpdate();
				System.out.println("Account Deleted Successfully !!!");
				
				}
				catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			
			
			
			
			
			
				
			/////////////////////////////	
			public static void moneytransfer(int sid,int rid,double amount) {
					try
					{
					con.setAutoCommit(false);
					String sql = "update BankAccount set balance=(select (balance - ?) from BankAccount where actid=?) where actid=? ";
					String sql1 = "update BankAccount set balance=(select (balance + ?) from BankAccount where actid=?) where actid=?";
					String sql2 = "rollback";
					
					PreparedStatement stmt;
					stmt = con.prepareStatement(sql);
					stmt.setDouble(1, amount);
					stmt.setInt(2, sid);
					stmt.setInt(3, sid);
					stmt.executeUpdate();
					
					BankAccount actObj4=validateid(rid);
					if(actObj4!=null) {
						PreparedStatement stmt1;
						stmt1 = con.prepareStatement(sql1);
						stmt1.setDouble(1, amount);
						stmt1.setInt(2, rid);
						stmt1.setInt(3, rid);
						
						stmt1.executeUpdate();
						System.out.println("money transferred Successfully !!!");
						
					}
					else 
					{
						Statement stmt2;
						stmt2 = con.createStatement();
						stmt2.executeUpdate(sql2);
						System.out.println("Receiver's id invalid. Transaction failed !!!");
						
					}
					con.setAutoCommit(true);
					}
					catch (SQLException e)
					{
						
						e.printStackTrace();
					}
					finally {
						
					}
		
		}


			
			public static double amountCheck(int actid) {
				String sql = "select balance from BankAccount where actid=?";
				double dto1=0;
				try
				{
			
				PreparedStatement stmt;
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, actid);
				
				ResultSet rset=stmt.executeQuery();
				
				if(rset.next()) {
				dto1 = rset.getDouble("balance");
				
				}
				
				}
				catch (SQLException e)
				{
					
					e.printStackTrace();
				}
				return dto1;
			}
						
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
}
	
	
	
	



