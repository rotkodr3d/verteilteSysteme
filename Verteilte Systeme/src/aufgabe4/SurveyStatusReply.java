package aufgabe4;

import java.util.HashMap;

public class SurveyStatusReply extends Reply{
	
	public HashMap<SurveyAnswers,Integer> votesReply;
	public final static int GETSURVEY = 0;
	
	public SurveyStatusReply(HashMap<SurveyAnswers,Integer> votes) {
		super(GETSURVEY);
		votesReply = votes;
	}
}
