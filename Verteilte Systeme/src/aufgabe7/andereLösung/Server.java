package aufgabe7.andereLösung;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
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

import aufgabe4.Reply;

public class Server { //SERVER AUFGABE 7
	
	private static final String DESTINATION = "queue/myQueue1";
	private static final String USER = "guest";
	private static final String PASSWORD = "guest";
	
	private QueueConnectionFactory factory;
	private Queue queue;
	
	ObjectInputStream ois = null;
	FileInputStream fis = null;
	SurveyDataset sd = null;
	Reply reply = null;
	String fileName = "surveyData7.txt";
	
	public Server() throws NamingException, JMSException {
		Properties props  = new Properties();
		props.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		props.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
		props.put("java.naming.provider.url", "infpro52.reutlingen-university.de");
		Context ctx = new InitialContext(props);
		factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
		queue = (Queue) ctx.lookup(DESTINATION);
	}
	
	public void process() throws JMSException, IOException {
		QueueConnection connection = factory.createQueueConnection(USER, PASSWORD);
		QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		QueueReceiver receiver = session.createReceiver(queue);
		connection.start();
		
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			sd = (SurveyDataset) ois.readObject();
		} catch (IOException | ClassNotFoundException e){
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			sd = new SurveyDataset();
			oos.writeObject(sd);
			oos.flush();
			oos.close();
			fos.close();
		}
		
		while (true) {
			System.out.println("Server 7 waiting for requests!");
			TextMessage request = (TextMessage) receiver.receive();
			SurveyEnum message = SurveyEnum.valueOf(request.getText().toUpperCase());
			String answer = "";
			
			switch(message) {
			
			case AGREE:
				answer = vote(message);
				break;
			case DISAGREE:
				answer = vote(message);
				break;
			case ABSTENSION:
				answer = vote(message);
				break;
			case REQUEST:
				answer = getVotes();
				break;
			}
			
			Queue tempQueue = (Queue) request.getJMSReplyTo();
			TextMessage response = session.createTextMessage();
			response.setText(answer);
			QueueSender sender = session.createSender(tempQueue);
			sender.send(response);
		}
	}
	
	private String getVotes() {
		String answer = "Survey status:\n";
		HashMap<SurveyEnum,Integer> votes = sd.votes;
		for (Entry<?,?> entry : votes.entrySet()) {
			answer += entry.getKey() + " : " + entry.getValue() + "\n";
		}
		return answer;
	}
	
	private String vote(SurveyEnum answer) throws IOException {
		HashMap<SurveyEnum,Integer> votes = sd.votes;
		System.out.println(answer);
		int counts = votes.get(answer);
		counts += 1;
		votes.put(answer, counts);
		
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		sd.updateVotesMap(votes);
		oos.writeObject(sd);
		oos.flush();
		oos.close();
		
		return "You voted with " + answer;
	}
	
	public static void main(String[] args) throws Exception{
		Server server = new Server();
		server.process();
	}
}
