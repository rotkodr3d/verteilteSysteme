package aufgabe7;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.*;

public class Server {
	private static final String DESTINATION = "queue/aufgabe7Queue";
	private static final String USER = "guest";
	private static final String PASSWORD = "guest";
	FileInputStream fis = null;
	ObjectInputStream ois = null;
	String umfrageAuswertung = "kundendatei.txt";

	private QueueConnectionFactory factory;
	private Queue queue;
	public static Umfrage umfrage;

	public Server() throws NamingException, JMSException {
		umfrage = new Umfrage("Studieren Sie?");
		Hashtable<String, String> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://infpro52.reutlingen-university.de");
		env.put(Context.SECURITY_PRINCIPAL, USER);
		env.put(Context.SECURITY_CREDENTIALS, PASSWORD);
		Context ctx = new InitialContext(env);
		factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
		queue = (Queue) ctx.lookup(DESTINATION);
	}

	public void process() throws JMSException {
		QueueConnection connection = factory.createQueueConnection(USER, PASSWORD);
		QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		QueueReceiver receiver = session.createReceiver(queue);
		connection.start();
		System.out.println("EchoServer gestartet ...");
		while (true) {
			String text = "";
			TextMessage request = (TextMessage) receiver.receive();
			String antwort = request.getText();
			Queue tempQueue = (Queue) request.getJMSReplyTo();
			Reply reply = null;
			switch (antwort) {
			case "ja":
				umfrage.ja++;
				text = ("Sie haben erfolgreich mit " + antwort + " abgestimmt");
				break;
			case "nein":
				umfrage.nein++;
				text = ("Sie haben erfolgreich mit " + antwort + " abgestimmt");
				break;
			case "enthalten":
				umfrage.enthaltung++;
				text = ("Sie haben erfolgreich mit " + antwort + " abgestimmt");
				break;
			case "Abfrage":
				text = abfragen();
				break;
			}
			try {
				FileOutputStream fos = new FileOutputStream(umfrageAuswertung);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(umfrage);
				oos.flush();
				oos.close();
				fos.close();
			} catch (IOException e) {
				System.out.println(e);
			}
			TextMessage response = session.createTextMessage();
			response.setText(text);
			QueueSender sender = session.createSender(tempQueue);
			sender.send(response);
		}
	}

	public String abfragen() {
		String text = null;
		try {
			fis = new FileInputStream(umfrageAuswertung);
			ois = new ObjectInputStream(fis);
			Umfrage abfrage = (Umfrage) ois.readObject();
			text = ("Umfrageergebnisse: " + umfrage.ja + " sind dafür, " + umfrage.nein + " sind dagegen, "
					+ umfrage.enthaltung + " enthalten sich, ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}

	public static void main(String[] args) throws Exception {
		Server server = new Server();
		server.process();
	}
}
