package aufgabe4.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import aufgabe4.Reply;

public class ServerCommunicator extends Thread{
	
	private final static int PORT = 7825;
	
	private static ServerSocket serverSocket;
	private static Server server;
	
	private Socket incoming;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public static void main(String[] args) {
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("ServerCommunicator waiting for clients...");
			server = new Server();
			
			while (true) {
				Socket incoming = serverSocket.accept();
				ServerCommunicator communicator = new ServerCommunicator(incoming);
				communicator.start();
			}
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public ServerCommunicator(Socket incoming) {
		this.incoming = incoming;
		
		try {
			out = new ObjectOutputStream(incoming.getOutputStream());
			in = new ObjectInputStream(incoming.getInputStream());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			String mode = (String) in.readObject();
			if (mode.equals("get")); { //Client m�chte die Umfrage abfragen 
				Reply reply = server.getSurveyData();
				out.writeObject(reply);
				out.flush();
				incoming.close();
			}
				
		} catch(Exception e) {
			
		}
	}
}
