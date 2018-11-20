package aufgabe4.Server;

import java.io.Serializable;
import java.util.HashMap;
import aufgabe4.SurveyAnswers;

//Datensatz Klasse die die Daten verwaltet 

public class SurveyDataset implements Serializable {
	
	public HashMap<SurveyAnswers,Integer> votes;
	
	public SurveyDataset() {
		votes=new HashMap<>();
	}
	
	public void addAnswerToDataset(SurveyAnswers answer, int amount) {
		if (!votes.containsKey(answer))
			votes.put(answer, amount);
		else
			System.out.println("ANSWER IS ALREADY IN THE DATASET!");
	}
	
	public void addVoteToAnswer(SurveyAnswers answer) {
		votes.put(answer,votes.get(answer)+1); 
	}
}
