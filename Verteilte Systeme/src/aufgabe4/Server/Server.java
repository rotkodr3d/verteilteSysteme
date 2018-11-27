package aufgabe4.Server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import aufgabe4.Reply;
import aufgabe4.SurveyAnswers;
import aufgabe4.SurveyStatusReply;

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
				System.out.println(key + " " + votes.get(key));
				reply = new Reply("Thank you for participating the survey!");
				FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				sd.updateVotesMap(votes);
				oos.writeObject(sd);
				oos.flush();
				oos.close();
				break;
			}
		} catch (Exception e) {
				e.printStackTrace();
			}
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
		return reply;
		}
}
