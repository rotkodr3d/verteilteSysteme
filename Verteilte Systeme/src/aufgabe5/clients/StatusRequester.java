package aufgabe5.clients;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import aufgabe5.Reply;
import aufgabe5.server.ServerInterface;

public class StatusRequester {
	private ServerInterface<String> server; // It is assumed that you know the object type of the server

	@SuppressWarnings("unchecked")
	public StatusRequester(String param1) {
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
		System.out.println("StatusRequester started!");
		System.out.println("Please enter the host address:");
		String param1 = s.nextLine();

		if (param1.replaceAll("\\s+", "").isEmpty()) {
			System.err.println("Invalid input!");
			System.exit(1);
		}

		StatusRequester sr = new StatusRequester(param1);
		System.out.println(
		        "If you want the size of the buffer type 'size'" + " If you want the number of entries type 'entries'");
		String mode = s.next();
		s.close();
		sr.getStatusOfBuffer(mode);
	}

	private void getStatusOfBuffer(String mode) {
		try {
			Reply<?> reply = null;
			if (mode.equals("size"))
				reply = server.getBufferSize();
			else if (mode.equals("entries"))
				reply = server.getAmountOfBufferEntries();
			handleReply(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
