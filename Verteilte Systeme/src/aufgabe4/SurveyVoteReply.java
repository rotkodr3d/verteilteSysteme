package aufgabe4;

public class SurveyVoteReply extends Reply {
	
	public final static int VOTE = 1;
	public SurveyAnswers answer;
	
	public SurveyVoteReply(SurveyAnswers answerGiven) {
		super(VOTE);
		answer = answerGiven;
	}
}
