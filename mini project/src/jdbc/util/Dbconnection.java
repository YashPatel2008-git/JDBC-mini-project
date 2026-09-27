package jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

import jdbc.util.Dbconnection;

public class Dbconnection {


	private static final  String URLNAME="jdbc:mysql://localhost:3306/project";
	private static final  String DRIVERCLASS="com.mysql.cj.jdbc.Driver";
	
	private static final  String USERNAME="root";
	private static final  String PASSWORD="root";
	
	public static Connection getconnetion() {
		
		Connection conn=null;
		try {
			
			 Class.forName(DRIVERCLASS);
			 
			 conn = DriverManager.getConnection(URLNAME,USERNAME,PASSWORD);
			 if(conn!=null)
			 {
				 System.out.println("db connected "+conn);
			 }
			 else
			 {
				 System.out.println("db not connected :"+conn);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		System.out.println(Dbconnection.getconnetion());
	}
}
