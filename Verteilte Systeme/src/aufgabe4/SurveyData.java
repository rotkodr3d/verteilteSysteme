package aufgabe4;

import java.io.Serializable;
import java.util.HashMap;

public class SurveyData implements Serializable{
	
	private static HashMap<SurveyOptions, Integer> surveyStatus = new HashMap<>();
	
	public SurveyData() {}
	
	public SurveyData(SurveyOptions option, int amountOfVotes) {
		surveyStatus.put(option, amountOfVotes);
	}
	
	public static SurveyData initEmptySurvey() {
		surveyStatus.put(SurveyOptions.AGREE,0);
		surveyStatus.put(SurveyOptions.DISAGREE,0);
		surveyStatus.put(SurveyOptions.ABSTAIN,0);
		return new SurveyData();
	}
}
