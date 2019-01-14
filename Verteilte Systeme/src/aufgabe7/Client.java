package aufgabe7;

import javax.jms.*;
import javax.naming.*;

public class Client {
	private static final String DESTINATION = "queue/aufgabe7Queue";
	private static final String USER = "guest";
	private static final String PASSWORD = "guest";

	private String text;
	private QueueConnectionFactory factory;
	private Queue queue;

	public Client(String text) throws NamingException, JMSException {
		this.text = text;
		Context ctx = new InitialContext();
		factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
		queue = (Queue) ctx.lookup(DESTINATION);
	}

	public void process() throws JMSException {
		QueueConnection connection = null;
		QueueSession session = null;
		TemporaryQueue tempQueue = null;
		QueueSender sender = null;
		QueueReceiver receiver = null;

		try {
			connection = factory.createQueueConnection(USER, PASSWORD);
			session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			// temporäre Queue für die Antwort erzeugen
			tempQueue = session.createTemporaryQueue();

			sender = session.createSender(queue);
			receiver = session.createReceiver(tempQueue);
			connection.start();

			TextMessage request = session.createTextMessage();
			request.setText(text);
			request.setJMSReplyTo(tempQueue);
			sender.send(request);

			// auf Antwort warten
			TextMessage response = (TextMessage) receiver.receive();

			System.out.println(response.getText());
		} finally {
			sender.close();
			receiver.close();
			tempQueue.delete();
			session.close();
			connection.close();
		}
	}

	public static void main(String[] args) throws Exception {
		String text = args[0];
		Client client = new Client(text);
		client.process();
	}
}