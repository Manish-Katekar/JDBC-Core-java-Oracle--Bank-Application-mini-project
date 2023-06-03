package com.pojo;

public class BankAccount {
// ACTID CUSTNAME  USERNAME  PASSWORD  BALANCE
	
	
	private int actid;
	private String custname,username,password;
	private double balance;
	public BankAccount(int actid, String custname, String username, String password, double balance) {
		super();
		this.actid = actid;
		this.custname = custname;
		this.username = username;
		this.password = password;
		this.balance = balance;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getActid() {
		return actid;
	}
	public String getUsername() {
		return username;
	}
	public double getBalance() {
		return balance;
	}
	@Override
	public String toString() {
		return "actid=" + actid + ", custname=" + custname + ", username=" + username + ", password="
				+ password + ", balance=" + balance  ;
	}
	
	
	
	
}
