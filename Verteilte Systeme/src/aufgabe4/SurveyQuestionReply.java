package aufgabe4;

import java.util.HashSet;

public class SurveyQuestionReply extends Reply{
	
	public HashSet<SurveyAnswers> possibleAnswers = new HashSet<>();
	public String surveyQuestion;
	
	public SurveyQuestionReply(String question) {
		this.surveyQuestion = question;
		this.replyType = Reply.VOTE;
		for (SurveyAnswers answer : SurveyAnswers.values())
			possibleAnswers.add(answer);
	}
}
