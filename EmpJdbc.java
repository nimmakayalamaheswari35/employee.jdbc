package com.prodapt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class EmpJdbc {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ResultSet rs=null;
		Connection con=null;
		Statement st=null;
		int choice1=0;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo","root","Mahi@1122");
            do
            {
            	
                System.out.println("Press 1 to enter data into table");
                System.out.println("Press 2 to display the data from the table");
                System.out.println("Press 3 to update the data");
                System.out.println("Press 4 to delete record");
                System.out.println("Press 5 to exit");
                PreparedStatement ps=null;
                choice1=sc.nextInt();
                switch(choice1)
                {
                case 1:
                	ps=con.prepareStatement("insert into employee1(name,age,designation,salary,deptno) values(?,?,?,?,?)");
                	System.out.println("Enter the number of records you want to enter");
                	int insert=0;
                	int choice2=sc.nextInt();
               	 	for(int ii=0;ii<choice2;ii++)
                    {
                    	System.out.println("ENTER NAME");
                    	ps.setString(1,sc.next());
                    	System.out.println("ENTER Age");
                    	ps.setInt(2,sc.nextInt());
                    	System.out.println("ENTER Designation");
                    	String desg=sc.next();
                    	ps.setString(3,desg);
                    	if(desg.toLowerCase().equals("manager"))
                    		System.out.println("RECORD");
                    		ps.setInt(4,10000);
                    	if(desg.toLowerCase().equals("clerk"))
                    		ps.setInt(4,60000);
                    	if(desg.toLowerCase().equals("progammer"))
                    		ps.setInt(4,80000);
                    	System.out.println("ENTER Department Number");
                    	ps.setInt(5,sc.nextInt());
                    	insert=ps.executeUpdate();
                    	if(insert==1)
                    	{
                    		System.out.println("RECORD INSERTED SUCEESFULLY");
                    	}
                    	
                    }
                	break;
                case 2:
                	st=con.createStatement();
                	boolean Records=true;
                	rs=st.executeQuery("select * from employee1");
                	while(rs.next())
                	{
                		Records=false;
                		System.out.print(rs.getInt("id")+"\t");
                    	System.out.print(rs.getString("name")+"\t");
                    	System.out.print(rs.getInt("age")+"\t");
                    	System.out.print(rs.getString("designation")+"\t");
                    	System.out.print(rs.getInt("salary")+"\t");
                    	System.out.println(rs.getInt("deptno"));
                	}
                	if(Records)
                	{
                		System.out.println("NO RECORS FOUND");
                	}
                	break;
                case 3:
                	int choice3=0;
                	do
                	{
                        System.out.println("Press 1 update name");
                        System.out.println("Press 2 to update age");
                        System.out.println("Press 3 to update deptno");
                        System.out.println("Press 4 to exit");
                        int s=0;
                		choice3=sc.nextInt();
                		ps=null;
                		switch(choice3)
                		{
                		case 1:
                			ps=con.prepareStatement("update employee1 set name=? where id=?");
                			System.out.println("ENTER NAME");
                         	ps.setString(1,sc.next());
                         	System.out.println("ENTER id");
                         	ps.setInt(2,sc.nextInt());
                         	s=ps.executeUpdate();
                         	if(s==1)
                         		System.out.println("RECORD UPDATED SUCESSFULLY");
                         	else
                         		System.out.println("NO RECORD UPDATED");
                         	break;
                		case 2:
                			ps=con.prepareStatement("update employee1 set age=? where id=?");
                			System.out.println("ENTER AGE");
                         	ps.setInt(1,sc.nextInt());
                         	System.out.println("ENTER id");
                         	ps.setInt(2,sc.nextInt());
                         	s=ps.executeUpdate();
                         	if(s==1)
                         		System.out.println("RECORD UPDATED SUCESSFULLY");
                         	else
                         		System.out.println("NO RECORD UPDATED");
                         	break;
                		case 3:
                			ps=con.prepareStatement("update employee1 set deptno=? where id=?");
                			System.out.println("ENTER DEPARTMENT NUMBER");
                         	ps.setInt(1,sc.nextInt());
                         	System.out.println("ENTER id");
                         	ps.setInt(2,sc.nextInt());
                         	s=ps.executeUpdate();
                         	if(s==1)
                         		System.out.println("RECORD UPDATED SUCESSFULLY");
                         	else
                         		System.out.println("NO RECORD UPDATED");
                         	break;
                			
                		}
                		
                	}while(choice3<4);
                	break;
                case 4:
                	System.out.println("NUMBER OF RECORDS TO DELETE");
                	int loop=sc.nextInt();
                	for(int ll=0;ll<loop;ll++)
                	{
                		int ss=0;
                    	ps=con.prepareStatement("delete from employee1 where id=?");
                    	System.out.println("Enter the id of employee to delete record");
                    	ps.setInt(1,sc.nextInt());
                    	ss=ps.executeUpdate();
                    	if(ss==1)
                    		System.out.println("RECORD DELETED SUCESSFULLY");
                    	else
                    		System.out.println("NO RECORD WITH THIS ID");
                	}
                	break;
                	
                }
            }while(choice1<5);
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			try
			{
				if(st!=null)
					st.close();
				if(rs!=null)
					rs.close();
				if(con!=null)
					con.close();
				if(sc!=null)
					sc.close();
			}
			catch(Exception ec)
			{
				
			}
		}
		

	}

}