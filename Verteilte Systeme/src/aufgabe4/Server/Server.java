package aufgabe4.Server;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import aufgabe4.Reply;

public class Server {
	
	public Server() {}
	
	
	//Abfrage der Abstimmung 
	public Reply getSurveyData() {
		SurveyDataset sd;
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		Reply reply = null;
		
		System.out.println("Called method surveyData()");
		String fileName = "surveyData.txt";
		
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			while(true) {
				sd = (SurveyDataset) ois.readObject();
			}
		} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
