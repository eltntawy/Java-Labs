package main.java.rmi.lab1.db.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServiceInterface extends Remote {

    public int saveEmp(Emp e) throws RemoteException ;

}
