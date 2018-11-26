package aufgabe4.Client;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import aufgabe4.Reply;
import aufgabe4.SurveyQuestionReply;
import aufgabe4.SurveyStatusReply;
import aufgabe4.SurveyVoteReply;

public class Client {
	private ClientCommunicator communicator;
	
	private void handleReply(Reply reply) {
		//Prüfen welches Reply vom Server zurück kam
		if (reply.replyType == Reply.GETSURVEY) { 				//Reply ist der Status der Umfrage
			System.out.println("People have answered: ");
			SurveyStatusReply surveyStatus = ((SurveyStatusReply) reply);
			HashMap<?,?> votes = surveyStatus.votesReply;
			
			for (Entry<?,?> entry : votes.entrySet()) {
				System.out.println(entry.getKey() + " " + entry.getValue());
			}
		}
		else if (reply.replyType == Reply.VOTE) {				//Reply ist die Frage der Umfrage, sowie die Antwortmöglichkeiten
			SurveyQuestionReply surveyQuestion = (SurveyQuestionReply) reply;
			System.out.println(surveyQuestion.surveyQuestion+"\n");
			System.out.println("Please type in your answer to the above-mentioned question.\n" + 
								"You have " + surveyQuestion.possibleAnswers.size() + " possible answers.");
			System.out.println(surveyQuestion.possibleAnswers);
			//Scanner scanner = new Scanner(System.in);
			String answer = "agree";//scanner.next();
			//scanner.close();
			answer = answer.toUpperCase();
			System.out.println("You answered: " + answer);
			communicator.communicate(new Reply(answer));
		}
	}
	
	public Client(String[] arguments) {
		communicator = new ClientCommunicator(arguments[0]);
		String mode = arguments[1];
		mode = mode.toLowerCase();
		Reply reply = null;
		if (mode.equals("get"))
			reply = communicator.communicate(new Reply(Reply.GETSURVEY));
		else if (mode.equals("vote"))
			reply = communicator.communicate(new Reply(Reply.VOTE));
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
