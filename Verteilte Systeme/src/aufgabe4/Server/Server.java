package aufgabe4.Server;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

import aufgabe4.Reply;
import aufgabe4.SurveyAnswers;
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
	
	
	public synchronized Reply countAnswer(String answer) {
		System.out.println("Called method countAnswer()");
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			while(true) {
				sd = (SurveyDataset) ois.readObject();
				ois.close();
				fis.close();
				HashMap<SurveyAnswers,Integer> votes = sd.votes;
				SurveyAnswers key = SurveyAnswers.valueOf(answer);
				int counts = votes.get(key);
				counts += 1;
				votes.put(key, counts);
				break;
			}
		} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
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
