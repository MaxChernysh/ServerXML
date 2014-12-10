package rmiClientPackage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInt extends Remote {
    public void updateClient (ArrayList <Tank> updList) throws RemoteException;
    
}
