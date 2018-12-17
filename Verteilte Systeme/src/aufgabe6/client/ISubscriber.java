package aufgabe6.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISubscriber extends Remote{
	public void displayMessage(String message) throws RemoteException;
}
