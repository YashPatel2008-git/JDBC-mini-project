package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import jdbc.bean.productbean;
import jdbc.util.Dbconnection;

public class ProductDao {

	public int  insertProduct(productbean pbean)
	{
		String insertquery="insert into product (pid,pname,category,quantity,price) values(?,?,?,?,?)";
		
		System.out.println("insertquery"+insertquery);
		Connection conn=Dbconnection.getconnetion();
		PreparedStatement pstmt=null;
		int rowaffected=0;
		if(conn!=null)
			
		{
			try {
				pstmt=conn.prepareStatement(insertquery);
				
				pstmt.setInt(1, pbean.getPid());
				pstmt.setString(2, pbean.getPname());
				pstmt.setString(3, pbean.getCatagory());
				pstmt.setInt(4, pbean.getQuantity());
				pstmt.setDouble(5, pbean.getPrice());
							
				rowaffected =pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("db not conncted");
		}
		return rowaffected;
	}
	
	
	public int Deleteproduct(int id) {
		int affectedrow =0;
		Connection conn=Dbconnection.getconnetion();
		PreparedStatement pstmt=null;
		String deletequery="delete from product where pid=?";
		
		if(conn!=null)
		{
			try {
				pstmt=conn.prepareStatement(deletequery);
				pstmt.setInt(1, id);
				affectedrow=pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("db is not connected");
		}
		return affectedrow;
		
	}
	
	
	public void displayAllProduct() {
		
		Connection conn=Dbconnection.getconnetion();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String displayquery="select * from product";
		if(conn!=null)
		{
			try {
				pstmt=conn.prepareStatement(displayquery);
				
				rs=pstmt.executeQuery();
				System.out.printf("%-5s %-12s %-15s %-8s %-10s%n",
				        "pid", "pname", "catagory", "qty", "price");

				while(rs.next())
				{
				    int pid = rs.getInt(1);
				    String pname = rs.getString(2);
				    String catagary = rs.getString(3);
				    int quntity = rs.getInt(4);
				    double price = rs.getDouble(5);

				    System.out.printf("%-5d %-12s %-15s %-8d %-10.1f%n",
				            pid, pname, catagary, quntity, price);

				    
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		{
			System.out.println("db is not connected");
		}
	}
	
	public int updateProduct(productbean pbean,int id)
	{
		String Updatequery="UPDATE product set pname=?, category=?,  quantity=?, price=?  where pid=?";
		System.out.println("update"+Updatequery);
		Connection conn=Dbconnection.getconnetion();
		PreparedStatement pstmt=null;
		int rowaffected=0;
		
		if(conn!=null)	
		{
			try {
				
				pstmt=conn.prepareStatement(Updatequery);
				pstmt.setString(1, pbean.getPname());
				pstmt.setString(2, pbean.getCatagory());
				pstmt.setInt(3, pbean.getQuantity());
				pstmt.setDouble(4, pbean.getPrice());
				pstmt.setInt(5, id);
			
				rowaffected =pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("db not conncted");
		}
		return rowaffected;
	}
	
	public void SearchById(int id){
		Connection conn=Dbconnection.getconnetion();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
			
		String serchbyidquery="select * from product where pid=?";
		if(conn!=null){
			try {		
				pstmt=conn.prepareStatement(serchbyidquery);
				pstmt.setInt(1, id);
				rs=pstmt.executeQuery();
				System.out.printf("%-5s %-12s %-15s %-8s %-10s%n",
				        "pid", "pname", "catagory", "qty", "price");
					
				while(rs.next())
				{
				    int pid = rs.getInt(1);
				    String pname = rs.getString(2);
				    String catagary = rs.getString(3);
					int quntity = rs.getInt(4);
				    double price = rs.getDouble(5);

					System.out.printf("%-5d %-12s %-15s %-8d %-10.1f%n",
				            pid, pname, catagary, quntity, price);
				}
			}		
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else{
			System.out.println("db is not connected");
		}		
	}
	
	public void Searchbyname(String name)
	{
		Connection conn=Dbconnection.getconnetion();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String serchbynamequery="select * from product where pname=?";
		if(conn!=null)
		{
			try {
					
				pstmt=conn.prepareStatement(serchbynamequery);
				pstmt.setString(1, name);
				rs=pstmt.executeQuery();
				System.out.printf("%-5s %-12s %-15s %-8s %-10s%n",
						"pid", "pname", "catagory", "qty", "price");
					
				while(rs.next()){
					int pid = rs.getInt(1);
					String pname = rs.getString(2);
					String catagary = rs.getString(3);
					int quntity = rs.getInt(4);
					double price = rs.getDouble(5);

					System.out.printf("%-5d %-12s %-15s %-8d %-10.1f%n",
							pid, pname, catagary, quntity, price);

					    
				}
					
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
				
		}else{
				System.out.println("db is not connected");
		}	
	}
	
	public void lowStockProduct(){
		Connection conn=Dbconnection.getconnetion();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
			
		String serchbyidquery="select * from product where quantity < 10 ";
		if(conn!=null){
			try {		
				pstmt=conn.prepareStatement(serchbyidquery);
				rs=pstmt.executeQuery();
				System.out.printf("%-5s %-12s %-15s %-8s %-10s%n",
				        "pid", "pname", "catagory", "qty", "price");					
				while(rs.next())
				{
				    int pid = rs.getInt(1);
				    String pname = rs.getString(2);
				    String catagary = rs.getString(3);
				    int quntity = rs.getInt(4);
					double price = rs.getDouble(5);

				    System.out.printf("%-5d %-12s %-15s %-8d %-10.1f%n",
				            pid, pname, catagary, quntity, price);			    
				}
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}else{
			System.out.println("db is not connected");
		}
		
	}
		
	public void sortProducts(int choice) {
	    String query;
	    switch (choice) {
	        case 1:
	            query = "SELECT * FROM product ORDER BY pname ASC";
	            break;
	        case 2:
	            query = "SELECT * FROM product ORDER BY price ASC";
	            break;
	        case 3:
	            query = "SELECT * FROM product ORDER BY price DESC";
	            break;
	        default:
	            System.out.println("Incorrect choice " + choice + ". Please enter a valid choice...");
	            return;
	    }

	    Connection conn = Dbconnection.getconnetion();
	    if (conn != null) {
	        try (PreparedStatement pstmt = conn.prepareStatement(query);
	             ResultSet rs = pstmt.executeQuery()) {

	            System.out.printf("%-5s %-12s %-15s %-8s %-10s%n",
	                    "pid", "pname", "category", "qty", "price");

	            while (rs.next()) {
	                System.out.printf("%-5d %-12s %-15s %-8d %-10.1f%n",
	                        rs.getInt(1),
	                        rs.getString(2),
	                        rs.getString(3),
	                        rs.getInt(4),
	                        rs.getDouble(5));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void countProduct() {
		String displayquery="select count(*) from product";
		Connection conn=Dbconnection.getconnetion();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		if(conn!=null)
		{
			try {
				pstmt=conn.prepareStatement(displayquery);
					
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					System.out.println("Total availabale product = " + rs.getInt(1));
				}else {
					System.out.println("Product is not availabale ...");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("db is not connected");
		}
		
		
	}
	

	
}

