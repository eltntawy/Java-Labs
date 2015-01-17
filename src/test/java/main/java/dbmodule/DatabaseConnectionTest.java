package main.java.dbmodule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

import java.sql.PreparedStatement;

public class DatabaseConnectionTest {
    
    
    
    
    public Connection getConnection () throws SQLException {
	
	DriverManager.registerDriver(new Driver());
	
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database","root","root");
	
	return conn;
    }
    
    
    
    public static void main(String[] args) {
	
	try {
	    Connection conn = new DatabaseConnectionTest().getConnection();
	    
	    String query = "SELECT * FROM database.Emp;";
	    
	    PreparedStatement prestm = conn.prepareStatement(query);
	    
	    ResultSet rs = prestm.executeQuery();
	    
	    while (rs.next()) {
		
		String id = rs.getString("id");
		String name = rs.getString("name");
		String job = rs.getString("job");
		
		System.out.println("id = "+id+"  name = "+name + " - job = "+job);
	    }
	    
	    
	    
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
    }
}
