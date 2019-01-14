package aufgabe7.andereLösung;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Client {
	private static final String DESTINATION = "queue/myQueue1";
	private static final String USER = "guest";
	private static final String PASSWORD = "guest";
	
	private String text;
	private QueueConnectionFactory factory;
	private Queue queue;
	
	public Client(String text) throws NamingException, JMSException{
		this.text = text;
		Properties props = new Properties();
		props.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		props.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
		props.put("java.naming.provider.url", "infpro52.reutlingen-university.de");
		Context ctx = new InitialContext(props);
		factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
		queue = (Queue) ctx.lookup(DESTINATION);
	}
	
	public void process() throws JMSException{
		QueueConnection connection = null;
		QueueSession session = null;
		TemporaryQueue tempQueue = null;
		QueueSender sender = null;
		QueueReceiver receiver = null;
		
		try {
			connection = factory.createQueueConnection(USER, PASSWORD);
			session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			tempQueue = session.createTemporaryQueue();
			
			sender = session.createSender(queue);
			receiver = session.createReceiver(tempQueue);
			connection.start();
			
			TextMessage request = session.createTextMessage();
			request.setText(text);
			request.setJMSReplyTo(tempQueue);
			sender.send(request);
			
			TextMessage response = (TextMessage) receiver.receive();
			System.out.println(response.getText());						//UMFRAGE SERIALIZABLE !!!
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
