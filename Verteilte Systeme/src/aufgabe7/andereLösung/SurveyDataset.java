package aufgabe7.andereLösung;

import java.io.Serializable;
import java.util.HashMap;
import aufgabe4.SurveyAnswers;

//Datensatz Klasse die die Daten verwaltet 

public class SurveyDataset implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HashMap<SurveyEnum,Integer> votes;
	public String surveyQuestion;
	
	public SurveyDataset() {
		votes=new HashMap<>();
		votes.put(SurveyEnum.AGREE, 0);
		votes.put(SurveyEnum.DISAGREE, 0);
		votes.put(SurveyEnum.ABSTENSION, 0);
	}
	
	public void addAnswerToDataset(SurveyEnum answer, int amount) {
		if (!votes.containsKey(answer))
			votes.put(answer, amount);
		else
			System.out.println("ANSWER IS ALREADY IN THE DATASET!");
	}
	
	public void addVoteToAnswer(SurveyEnum answer) {
		votes.put(answer,votes.get(answer)+1); 
	}
	
	public void setSurveyQuestion(String newQuestion) {
		surveyQuestion = newQuestion;
	}
	
	public void updateVotesMap(HashMap<SurveyEnum,Integer> updatedVotes) {
		votes = updatedVotes;
	}
}
