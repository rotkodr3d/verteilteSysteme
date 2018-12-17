package aufgabe6.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import aufgabe6.client.ISubscriber;

public interface IServer extends Remote{
	
	void createMessage() throws RemoteException;
	
	void sendMessage(String message) throws RemoteException;
	
	boolean addSubscriber(ISubscriber sub) throws RemoteException;
	
	boolean removeSubscriber(ISubscriber sub) throws RemoteException;
}
