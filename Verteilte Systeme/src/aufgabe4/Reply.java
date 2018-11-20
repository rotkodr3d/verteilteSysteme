package aufgabe4;

import java.io.Serializable;
import java.util.HashMap;

public class Reply implements Serializable{
	
	public HashMap<SurveyAnswers,Integer> votesReply;
	
	public Reply(HashMap<SurveyAnswers,Integer> votes) {
		votesReply = votes;
	}
}
