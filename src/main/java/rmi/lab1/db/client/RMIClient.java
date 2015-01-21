package main.java.rmi.lab1.db.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import main.java.rmi.lab1.db.server.Address;
import main.java.rmi.lab1.db.server.Emp;
import main.java.rmi.lab1.db.server.RMIServiceInterface;

public class RMIClient {

    public static void main(String[] args) {

	Registry reg;
	try {
	    reg = LocateRegistry.getRegistry("127.0.0.1");

	    RMIServiceInterface insertEmp = (RMIServiceInterface) reg.lookup("InsertEmp");

	    int ret = insertEmp.saveEmp(new Emp(10000,"mohamed","job",25,new Address("Street",5465)));

	    System.out.println(ret+ " row inserted");

	} catch (RemoteException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (NotBoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
