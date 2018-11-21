package aufgabe4.Server;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import aufgabe4.Reply;
import aufgabe4.SurveyQuestionReply;
import aufgabe4.SurveyStatusReply;
import aufgabe4.SurveyVoteReply;

public class Server {
	
	SurveyDataset sd;
	ObjectInputStream ois = null;
	FileInputStream fis = null;
	Reply reply = null;
	String fileName = "surveyData.txt";
	
	public Server() {}
	
	public Reply voteSurvey() {
		System.out.println("Called method voteSuvrey()");
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			while(true) {
				sd = (SurveyDataset) ois.readObject();
				ois.close();
				fis.close();
				reply = new SurveyQuestionReply(sd.surveyQuestion);
				break;
			}
		} catch (Exception e) {
				e.printStackTrace();
			}
		
		System.out.println("Returning reply object");
		return reply;
	}
	
	//Abfrage der Abstimmung 
	public Reply getSurveyData() {
		System.out.println("Called method surveyData()");
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			while(true) {
				sd = (SurveyDataset) ois.readObject();
				ois.close();
				fis.close();
				reply = new SurveyStatusReply(sd.votes);
				break;
			}
		} catch (Exception e) {
				e.printStackTrace();
			}
		
		System.out.println("Returning reply object");
		return reply;
		}
}
