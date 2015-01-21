package main.java.rmi.lab1.db.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {


    
    public static void main(String[] args) {
	
	try {
	    RMIServiceInterface service = new RMIServiceImpl();
	    
	    
	    Registry reg = LocateRegistry.getRegistry();
	    
	    reg.rebind("InsertEmp", service);
	    
	    
	} catch (RemoteException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    
    
}
