package main.java.rmi.lab1.db.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;



public class RMIServiceImpl extends UnicastRemoteObject implements RMIServiceInterface {

    protected RMIServiceImpl() throws RemoteException {
	super();
	// TODO Auto-generated constructor stub
    }

    public int saveEmp(Emp e) throws RemoteException {

	Connection conn;
	try {
	    conn = getConnection();
	    PreparedStatement ps = conn.prepareStatement("insert into database.Emp values	(?,?,?,?,?,?)");
	    int i = ps.executeUpdate();
	    
	    
	    
	    return i;
	    
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	    return 0;
	}
	
    }

    public Connection getConnection() throws SQLException {

	DriverManager.registerDriver(new Driver());

	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "root");

	return conn;
    }

}
