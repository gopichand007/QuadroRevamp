package businessLib;

import genericLib.WebDriverCommonUtills;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuadroLookupsDB extends WebDriverCommonUtills{
	//
	 static Connection connection = null;
	 static Statement statement= null;
	 
	public void WHQLLookupsDBGetConnection() {
		
		
		try{Class.forName("org.sqlite.JDBC");
			}catch(Exception e){
				System.err.println(e.getMessage());
			}
		
		try{
		connection = DriverManager.getConnection("jdbc:sqlite:whqlDriverLookups.db");
		statement = connection.createStatement();
	    statement.setQueryTimeout(30);  // set timeout to 30 sec.
	    }catch(SQLException e1){
	      // if the error message is "out of memory", 
	      // it probably means no database file is found
	        System.err.println(e1.getMessage());
	        
	        try
		      {
		        if(connection != null)
		          connection.close();
		      }
		      catch(SQLException e)
		      {
		        // connection close failed.
		        System.err.println(e);
		      }

	    }
	 
	
		
	}
	// Need to update Create method manually
	public void createTable(){
	 try{
		 if(connection != null){
		 statement.executeUpdate("drop table if exists whqllookups");
	     statement.executeUpdate("create table whqllookups (prducttype string, productseries string, product string, os srting, DownlaodType string, langauage string, "
	     		+ "driverdetailurls string, driverversionInDDP string, RleaseDate string, DDPageOS string, DDPageLanguge string)");
		 }
	    }catch(SQLException e1){
	    	System.err.println(e1.getMessage());
	    	try
		      {
		        if(connection != null)
		          connection.close();
		      }
		      catch(SQLException e)
		      {
		        // connection close failed.
		        System.err.println(e);
		      }

	    }
	}
	

	public int  insertData(String productType, String productSeries, String product, String os, String DownlaodType, String langauage, String driverdetailurls, String driverversionInDDP, 
			String RleaseDate, String DDPageOS, String DDPageLanguge){
		 int insered = 0;
		try{
			 if(connection != null){
				 PreparedStatement psm = connection.prepareStatement("insert into whqllookups values(?,?,?,?,?,?,?,?,?,?,?)");
				 psm.setString(1, productType);
				 psm.setString(2, productSeries);
				 psm.setString(3, product);
				 psm.setString(4, os);
				 psm.setString(5, DownlaodType);
				 psm.setString(6, langauage);
				 psm.setString(7, driverdetailurls);
				 psm.setString(8, driverversionInDDP);
				 psm.setString(9, RleaseDate);
				 psm.setString(10, DDPageOS);
				 psm.setString(11, DDPageLanguge);
				 insered= psm.executeUpdate();
				 
			 }
		 }catch(SQLException e1){
		    	System.err.println(e1.getMessage());
		    	try
			      {
			        if(connection != null)
			          connection.close();
			      }
			      catch(SQLException e)
			      {
			        // connection close failed.
			        System.err.println(e);
			      }

		    }
		return insered;
		
	}
	
	public void resultView(){
		try{
			 if(connection != null){
				 ResultSet rs = statement.executeQuery("select * from whqllookups");
				 while(rs.next()){
				System.out.println("productType = " + rs.getString("prducttype"));
				System.out.println("productSeries = " + rs.getString("productseries"));
				System.out.println("product = " + rs.getString("product"));
				System.out.println("os = " + rs.getString("os"));
				System.out.println("DownlaodType = " + rs.getString("DownlaodType"));
				System.out.println("langauage = " + rs.getString("langauage"));
				System.out.println("driverdetailurls = " + rs.getString("driverdetailurls"));
				System.out.println("driverversionInDDP = " + rs.getString("driverversionInDDP"));
				System.out.println("RleaseDate = " + rs.getString("RleaseDate"));
				System.out.println("DDPageOS = " + rs.getString("DDPageOS"));
				System.out.println("DDPageLanguge = " + rs.getString("DDPageLanguge"));
			
				 }
			 }
		 }catch(SQLException e1){
		    	System.err.println(e1.getMessage());
		    	try
			      {
			        if(connection != null)
			          connection.close();
			      }
			      catch(SQLException e)
			      {
			        // connection close failed.
			        System.err.println(e);
			      }

		    }
		
	}
	
	
	public void closeConnection(){
		try
	      {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e)
	      {
	        // connection close failed.
	        System.err.println(e);
	      }

		
	}
	
	
	
	
  public static void main(String[] args){
	  
	  try{
	  QuadroLookupsDB db =  new QuadroLookupsDB();
	  db.WHQLLookupsDBGetConnection();
	  db.createTable();
	  System.out.println(db.insertData( "1quadro", "2quadro series", "3String product", "4OS", "5String DownlaodType", "6String langauage", "7String driverdetailurls", "8String driverversionInDDP", 
				"9String relaseDate", "10OS", "11Langauge"));  
	  db.resultView();
	  db.closeConnection();
	  
  }catch(Exception e){
	  System.out.println("Issue in DB"+e.getMessage());
  }
	  }
  
  }