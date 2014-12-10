package rmiClientPackage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInt extends Remote  {
        public void openXML (String filename) throws RemoteException;
        public void saveXML (String filename) throws RemoteException;
	public void add (Tank tank) throws RemoteException;
	public void remove (int index) throws RemoteException;
	public ArrayList<Tank> get (char point, String arg) throws RemoteException;
        public void registerClient (ClientInt client) throws RemoteException;
        public void deleteClient (ClientInt client) throws RemoteException;
}
