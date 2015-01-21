package main.java.rmi.lab1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServiceImpl extends UnicastRemoteObject implements RMIServiceInterface{

    protected RMIServiceImpl() throws RemoteException {
	super();
	// TODO Auto-generated constructor stub
    }

    public String sayHello(String str) throws RemoteException {
	
	return "Hello World "+str;
    }

    

}
