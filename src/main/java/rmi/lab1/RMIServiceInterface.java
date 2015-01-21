package main.java.rmi.lab1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServiceInterface extends Remote {

    public String sayHello(String str) throws RemoteException;

}
