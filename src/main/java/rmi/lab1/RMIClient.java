package main.java.rmi.lab1;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {

    public static void main(String[] args) {

	Registry reg;
	try {
	    reg = LocateRegistry.getRegistry("127.0.0.1");

	    RMIServiceInterface sayHelloService = (RMIServiceInterface) reg.lookup("HelloService");

	    String ret = sayHelloService.sayHello("Client Hello");

	    System.out.println(ret);

	} catch (RemoteException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (NotBoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
