package aufgabe4.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
			
		} catch(Exception e) {
			
		}
	}
}
