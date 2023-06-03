package com.taster;

import java.util.List;
import java.util.Scanner;

import com.pojo.BankAccount;
import com.pojo.BankAccountDAL;

public class TesterBankCRUD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("1:Show All Accounts \n2:Search By Account Id \n3:Check Valid User \n4:Withdraw amount \n5:Deposit Amount \n6:Show Balance Details \n7:Delete Account \n8.MoneyTransfer \n9.Exit ");

		int choice;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Enter Choice:");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				List<BankAccount> bank = BankAccountDAL.getAllAccounts();
				
				for (BankAccount b : bank) {
					System.out.println(b);
				}

				break;
			case 2:// get Act By Id
				System.out.println("Enter ActId");
				int actid = sc.nextInt();

				BankAccount actDetails = BankAccountDAL.getAccountById(actid);
				if (actDetails != null)
				{	System.out.println(actDetails);
				
				}
				else
					System.out.println("Actid Invalid");

				break;
			case 3:
			System.out.println("Enter User Name Pwd for Login");
			String uname=sc.next();
			String pwd=sc.next();
		  BankAccount actObj=	BankAccountDAL.validateLogin(uname, pwd);
			if(actObj!=null)
			{
				System.out.println("Valid User");
				System.out.println(actObj);
			}
			else
			{
				System.out.println("InValid User");
			}
			break;
			case 4:
				System.out.println("Enter Username and Password: ");
				String username=sc.next();
				String passwd=sc.next();
				BankAccount actObj1=BankAccountDAL.validateLogin(username, passwd);
				if(actObj1!=null)
				{
					System.out.println("Valid User");
					System.out.println("Enter account id and amount to withdraw:");
					int id=sc.nextInt();
					double amt=sc.nextDouble();
					if(amt<=(BankAccountDAL.amountCheck(id))) {
						BankAccountDAL.withdraw(amt,id);
					}
					else
					{
						System.out.println("Insufficient Balance");
					}
				}
				else
				{
					System.out.println("InValid User");
				}
				break;
			case 5:
				System.out.println("Enter Username and Password: ");
				String name=sc.next();
				String pass=sc.next();
				BankAccount actObj2=BankAccountDAL.validateLogin(name, pass);
				if(actObj2!=null)
				{
					System.out.println("Valid User");
					System.out.println("Enter account id and amount to deposit:");
					int id=sc.nextInt();
					double amt=sc.nextDouble();
					BankAccountDAL.deposit(amt,id);
				}
				else
				{
					System.out.println("InValid User");
				}
				break;
				
				
			case 6:
				System.out.println("id enter ");
				int id1=sc.nextInt();
				System.out.println(BankAccountDAL.amountCheck(id1));				
				
				break;
	
				
				
			case 7:
				System.out.println("Enter Username and Password: ");
				String user=sc.next();
				String passwrd=sc.next();
				BankAccount actObj3=BankAccountDAL.validateLogin(user, passwrd);
				if(actObj3!=null)
				{
					System.out.println("Valid User");
					System.out.println("Enter account id of account wants to Delete:");
					int id=sc.nextInt();
					BankAccountDAL.delete(id);
				}
				else
				{
					System.out.println("InValid User");
				}
				break;
			
			case 8:
				System.out.println("Enter Username and Password: ");
				String user1=sc.next();
				String passwrd1=sc.next();
				BankAccount actObj4=BankAccountDAL.validateLogin(user1, passwrd1);
				if(actObj4!=null)
				{
					System.out.println("Valid User");
					System.out.println("Enter the Sender's account id : ");
					int sid=sc.nextInt();
					System.out.println("Enter the Receiver's account id : ");
					int rid=sc.nextInt();
					System.out.println("Enter the Amount : ");
					int amt=sc.nextInt();
					
					
					if(amt<=(BankAccountDAL.amountCheck(sid))) {
						BankAccountDAL.moneytransfer(sid,rid,amt);
					}
					else
					{
						System.out.println("Insufficient Balance");
					}
					
					
				}
				else
				{
					System.out.println("InValid User");
				}
				break;
			
			
			
			
			}
			
		} while (choice != 9);

	}

}
