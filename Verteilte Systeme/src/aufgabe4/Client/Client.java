package aufgabe4.Client;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import aufgabe4.Reply;
import aufgabe4.SurveyStatusReply;
import aufgabe4.SurveyVoteReply;

public class Client {
	private ClientCommunicator communicator;
	
	private void handleReply(Reply reply) {
		//Prüfen welches Reply vom Server zurück kam
		if (reply != null)		
			if (reply.replyType == Reply.GETSURVEY) { 				//Reply ist der Status der Umfrage
				System.out.println("People have answered: ");
				SurveyStatusReply surveyStatus = ((SurveyStatusReply) reply);
				HashMap<?,?> votes = surveyStatus.votesReply;
				
				for (Entry<?,?> entry : votes.entrySet()) {
					System.out.println(entry.getKey() + " " + entry.getValue());
				}
			}
	}
	
	public Client(String[] arguments) {
		communicator = new ClientCommunicator(arguments[0]);
		String mode = arguments[1];
		mode = mode.toLowerCase();
		Reply reply = null;
		if (mode.equals("get"))
			reply = communicator.communicate(new Reply(Reply.GETSURVEY));
		else if (mode.equals("vote")) {
			System.out.println("Should java support multiple inheritance?");
			Scanner s = new Scanner(System.in);
			String answer = s.next();
			answer = answer.toUpperCase();
			s.close();
			//communicator.communicateWithoutReply(new Reply(answer,Reply.VOTEANSWER));
			communicator.communicate(new Reply(answer,Reply.VOTEANSWER));
		}
		handleReply(reply);
	}
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Wrong syntax!\nSyntax: java Client <server> <mode>");
			System.exit(1);
		}
		
		Client client = new Client(args);
	}
}
