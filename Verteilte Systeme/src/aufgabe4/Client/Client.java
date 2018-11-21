package aufgabe4.Client;

import aufgabe4.Reply;
import aufgabe4.SurveyStatusReply;

public class Client {
	private ClientCommunicator communicator;
	
	private void outputReply(Reply reply) {
		//Pr�fen welches Reply vom Server zur�ck kam
		if (reply.replyType == Reply.GETSURVEY) { 				//Reply ist der Status der Umfrage
			System.out.println("People have answered: ");
			System.out.println((SurveyStatusReply) reply);
		}
		else if (reply.replyType == Reply.VOTE) {				//Reply ist die Frage der Umfrage
			
		}
	}
	
	public Client(String[] arguments) {
		communicator = new ClientCommunicator(arguments[0]);
		Reply reply = communicator.communicate(arguments[1]);
		outputReply(reply);
	}
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Wrong syntax!\nSyntax: java Client <server> <mode>");
			System.exit(1);
		}
		
		Client client = new Client(args);
	}
}