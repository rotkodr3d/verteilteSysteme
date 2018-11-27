package aufgabe4.Server;

import java.io.Serializable;
import java.util.HashMap;
import aufgabe4.SurveyAnswers;

//Datensatz Klasse die die Daten verwaltet 

public class SurveyDataset implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HashMap<SurveyAnswers,Integer> votes;
	public String surveyQuestion;
	
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
	
	public void setSurveyQuestion(String newQuestion) {
		surveyQuestion = newQuestion;
	}
	
	public void updateVotesMap(HashMap<SurveyAnswers,Integer> updatedVotes) {
		votes = updatedVotes;
	}
}
