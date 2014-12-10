package rmiClientPackage;

import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends UnicastRemoteObject implements ServerInt {	
	private ArrayList<Tank> troops = new ArrayList<Tank>();	
        private XMLread reader = new StAXReadXML();
        private XMLwrite writer = new StAXWriteXML();
        private ArrayList<ClientInt> ClientList = new ArrayList<ClientInt>();
        
        
        
	public Server() throws RemoteException {
	}
	public static void main(String[] args) throws RemoteException, MalformedURLException  {
                System.out.println("Current MainThread "+Thread.currentThread().getName()+" ID: "+Thread.currentThread().getId());
		Server server = new Server ();
                System.out.println("Initializing...");
		String serviceName = "rmi://localhost/server";
		Registry registry = LocateRegistry.createRegistry( 1999 );
		registry.rebind(serviceName, server);
		System.out.println("Running...");		
	}
	
	public synchronized void add (Tank tank) throws RemoteException  {
            
                System.out.println("Current AddThread "+Thread.currentThread().getName()+" ID: "+Thread.currentThread().getId());
		troops.add(tank);
                saveXML("tank_base.xml");
                updateClients();
	}
	public synchronized void remove (int index) throws RemoteException {
            
                System.out.println("Current RemoveThread"+Thread.currentThread().getName()+" ID: "+Thread.currentThread().getId());
		troops.remove(index);
                saveXML("tank_base.xml");                
                updateClients();
            
	}
	public ArrayList<Tank> get (char point, String arg) {
                System.out.println("Current GetThread "+Thread.currentThread().getName()+" ID: "+Thread.currentThread().getId());
		ArrayList<Tank> tmpArray=new ArrayList<Tank>();
		Tank tmpTank;
                openXML("tank_base.xml");
		switch (point) {
		case '1': //All
			tmpArray = troops;
			break;
		case '2': //Nation		
			for (int i=0; i<troops.size(); i++) {
				tmpTank=troops.get(i);
				if (tmpTank.getNation().equals(arg)) {                                        
					tmpArray.add(tmpTank);
				}
			}
			break;
		case '3': //Model
			for (int i=0; i<troops.size(); i++) {
				tmpTank=troops.get(i);
				if (tmpTank.getModel().equals(arg)) {
					tmpArray.add(tmpTank);
				}
			}
			break;
		case '4': //CaliberGun
			for (int i=0; i<troops.size(); i++) {
				tmpTank=troops.get(i);
				if (tmpTank.getCaliberGun()==Integer.parseInt(arg)) {
					tmpArray.add(tmpTank);
				}
			}
			break;
		case '5': //EnginePower
			for (int i=0; i<troops.size(); i++) {
				tmpTank=troops.get(i);
				if (tmpTank.getEnginePower()==Integer.parseInt(arg)) {
					tmpArray.add(tmpTank);
				}
			}
			break;
		case '6': //Weight
			for (int i=0; i<troops.size(); i++) {
				tmpTank=troops.get(i);
				if (tmpTank.getWeight()==Integer.parseInt(arg)) {
					tmpArray.add(tmpTank);
				}
			}
			break;
		}
            
		return tmpArray;
	}

        public void openXML(String filename) {
            System.out.println("Current OpenXMLThread "+Thread.currentThread().getName()+" ID: "+Thread.currentThread().getId());
            System.out.println("Start reading");
            troops=reader.read(filename);
            System.out.println(troops);
            System.out.println("Finished reading");
        }
  
        public void saveXML(String filename) {
            System.out.println("Current SaveXMLThread "+Thread.currentThread().getName()+" ID: "+Thread.currentThread().getId());
            System.out.println("Start writing");
            writer.write(troops, filename);
            System.out.println("Finished writing");
        }
        
        public void registerClient (ClientInt client) {
            System.out.println("Current RegisterClientThread "+Thread.currentThread().getName()+" ID: "+Thread.currentThread().getId());
            ClientList.add(client);
            System.out.println(client);
        }
        
        private void updateClients () throws RemoteException {
            System.out.println("Current updateClientsThread "+Thread.currentThread().getName()+" ID: "+Thread.currentThread().getId());
            for (ClientInt tmpCL:ClientList) {
                try {
                tmpCL.updateClient(troops);
                }
                catch (RemoteException exc) {
                    exc.printStackTrace();
                }
            }
        }


        public void deleteClient(ClientInt client) throws RemoteException {
            System.out.println("Current deleteClientThread: "+Thread.currentThread().getName()+" ID: "+Thread.currentThread().getId());
            ClientList.remove(client);
    }
       
        
}

