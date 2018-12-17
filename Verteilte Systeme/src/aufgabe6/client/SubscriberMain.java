package aufgabe6.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import aufgabe6.server.IServer;

public class SubscriberMain {
	
	private static IServer server;
	private static Subscriber sub;
	
	public static void main(String[] args) throws RemoteException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Subscriber started!\nIf you want to end the subscription type 'stop'");
		System.out.println("Please enter the host address:");
		String sHostAddress = scanner.nextLine();

		if (sHostAddress.replaceAll("\\s+", "").isEmpty()) {
			System.err.println("Invalid input!");
			System.exit(1);
		}
		
		try {
			server = (IServer) Naming.lookup("rmi://" + sHostAddress + "/IServer");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
		sub = new Subscriber();
		if (server.addSubscriber(sub))
			System.out.println("Subscriber will now recieve messages from the server!");
		else
			System.err.println("Something went wrong! :(");
		
		String unsubscribe = scanner.nextLine();
		if (unsubscribe.trim().equals("stop")) {
			if(server.removeSubscriber(sub))
				System.out.println("Subscriber won't recieve messages from the server any longer!");
			else
				System.err.println("Something went wrong! :(");
		}
		scanner.close();
	}
}
