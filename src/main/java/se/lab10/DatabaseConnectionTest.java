package main.java.se.lab10;



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
	    
	    PreparedStatement prestm = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	    
	    ResultSet rs = prestm.executeQuery();
	    conn.setAutoCommit(false);
	    while (rs.next()) {
		
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String job = rs.getString("job");
		int age = rs.getInt("age");
		
		rs.moveToInsertRow();
		
		int x = (int)(1 + Math.random()*100);
		
		System.out.println(x);
		
		rs.updateInt("id", id+ x);
		rs.updateString("name", id+" inserted");
		rs.updateString("job", id+" inserted");
		rs.updateInt("age", age +10);
		
		rs.insertRow();
		prestm.addBatch();
		System.out.println("id = "+id+"  name = "+name + " - job = "+job+ " - "+"age = "+age);
	    }
	    
	    conn.commit();
	    
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
    }
}
