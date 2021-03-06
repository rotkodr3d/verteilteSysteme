package aufgabe5.clients;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import aufgabe5.server.ServerInterface;

public class Producer {
	private ServerInterface<String> server; // It is assumed that you know the object type of the server

	@SuppressWarnings("unchecked")
	public Producer(String param1) {
		try {
			server = (ServerInterface<String>) Naming.lookup("rmi://" + param1 + "/ServerInterface");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter the host address:");
		String param1 = s.nextLine();
		s.close();

		if (param1.replace("\\s+", "").isEmpty()) {
			System.err.println("Invalid input!");
			System.exit(1);
		}

		Producer p = new Producer(param1);
		p.produce();
	}

	private void produce() {
		try {
			int number = 0;
			while (true) {
				number = (int) (Math.random() * 1000);
				server.addToBuffer("Producer will wait " + number + " ms");
				System.out.println("Producer will wait " + number + " ms");
				Thread.sleep(number);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
