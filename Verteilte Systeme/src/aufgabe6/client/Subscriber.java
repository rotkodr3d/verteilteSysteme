package aufgabe6.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Subscriber extends UnicastRemoteObject implements ISubscriber {

	public Subscriber() throws RemoteException{}

	public void displayMessage(String message) {
		System.out.println(message);
	}
}