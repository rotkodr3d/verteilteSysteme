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
			Reply clientReply = (Reply) in.readObject();
			String message = clientReply.message;
			int mode = clientReply.replyType;
			Reply reply;
			if (mode == Reply.GETSURVEY) { 				//Client möchte die Umfrage abfragen 
				reply = server.getSurveyData();
				out.writeObject(reply);
				out.flush();
				incoming.close();
			} else if (mode == Reply.VOTE) {			//Client möchte an Umfrage teilnehmen, Server sendet Frage
				reply = server.voteSurvey();
				out.writeObject(reply);
				out.flush();
			} else if (mode == Reply.VOTEANSWER) {		//Client hat Frage beantwortet und hat diese zum Server geschickt
				reply = server.countAnswer(message);
				out.writeObject(reply);
				incoming.close();
			}
				
		} catch(Exception e) {
			
		}
	}
}
