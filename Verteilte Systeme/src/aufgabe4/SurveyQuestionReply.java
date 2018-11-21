package aufgabe4;

import java.util.HashSet;

public class SurveyQuestionReply extends Reply{
	
	public HashSet<SurveyAnswers> possibleAnswers;
	public String surveyQuestion;
	
	public SurveyQuestionReply(String question) {
		this.surveyQuestion = question;
		
		for (SurveyAnswers answer : SurveyAnswers.values())
			possibleAnswers.add(answer);
	}
}
