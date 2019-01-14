package aufgabe7.andereLösung;

import java.util.Hashtable;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Server {
	
	private static final String DESTINATION = "queue/myQueue1";
	private static final String USER = "guest";
	private static final String PASSWORD = "guest";
	
	private QueueConnectionFactory factory;
	private Queue queue;
	
	public Server() throws NamingException, JMSException {
		Properties props  = new Properties();
		props.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		props.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
		props.put("java.naming.provider.url", "infpro52.reutlingen-university.de");
		Context ctx = new InitialContext(props);
		factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
		queue = (Queue) ctx.lookup(DESTINATION);
	}
	
	public void process() throws JMSException {
		QueueConnection connection = factory.createQueueConnection(USER, PASSWORD);
		QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		QueueReceiver receiver = session.createReceiver(queue);
		connection.start();
		
		while (true) {
			TextMessage request = (TextMessage) receiver.receive();
			String text = request.getText();
			Queue tempQueue = (Queue) request.getJMSReplyTo();
			TextMessage response = session.createTextMessage();
			response.setText(text);
			QueueSender sender = session.createSender(tempQueue);
			sender.send(response);
		}
	}
	
	public static void main(String[] args) throws Exception{
		Server server = new Server();
		server.process();
	}
}
