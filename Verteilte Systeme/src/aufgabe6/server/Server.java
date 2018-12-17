package aufgabe6.server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import aufgabe6.client.ISubscriber;

public class Server extends UnicastRemoteObject implements IServer {

	private ArrayList<ISubscriber> subscribers = new ArrayList<>();

	public static void main(String[] args) {
		try {
			Server server = new Server();
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("IServer", server);
			System.out.println("Server initialized!");
			server.createMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Server() throws RemoteException {
	
	}
	
	public void createMessage() {		//creates and sends messages for the subscribers
		try {
			while(true) {			
				float value = (float) Math.round((Math.random() *10000)); //random value
				String message = "Stock value is: ";
				value /= 100;
				message += (value + " EUR");
				sendMessage(message);
				Thread.sleep(60000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized boolean addSubscriber(ISubscriber sub) {
		return subscribers.add(sub);
	}
	
	@Override
	public synchronized boolean removeSubscriber(ISubscriber sub) {
		return subscribers.remove(sub);
	}

	@Override
	public void sendMessage(String message) throws RemoteException {
		for (ISubscriber sub : subscribers) {
			sub.displayMessage(message);
		}		
	}
}
