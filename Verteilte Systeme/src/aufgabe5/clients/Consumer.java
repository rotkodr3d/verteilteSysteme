package aufgabe5.clients;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import aufgabe5.Reply;
import aufgabe5.server.ServerInterface;

public class Consumer {
	private ServerInterface<String> server; // It is assumed that you know the object type of the server

	@SuppressWarnings("unchecked")
	public Consumer(String param1) {
		try {
			server = (ServerInterface<String>) Naming.lookup("rmi://" + param1 + "/ServerInterface");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	private void handleReply(Reply<?> reply) {
		System.out.println(reply.getReplyContent());
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Consumer started!");
		System.out.println("Please enter the host address:");
		String param1 = s.nextLine();
		s.close();

		if (param1.replaceAll("\\s+", "").isEmpty()) {
			System.err.println("Invalid input!");
			System.exit(1);
		}

		Consumer c = new Consumer(param1);
		c.consume();
	}

	private synchronized void consume() {
		try {
			int number = 0;
			while (true) {
				number = (int) (Math.random() * 1000);
				Reply<?> reply = server.removeFromBuffer();
				handleReply(reply);
				Thread.sleep(number);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

