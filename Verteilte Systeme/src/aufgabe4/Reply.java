package aufgabe4;

import java.io.Serializable;
import java.util.HashMap;

public class Reply implements Serializable{
	public final static int GETSURVEY = 0;
	public final static int VOTE = 1;
	public int replyType;
	
	public Reply() {
		
	}
	
	public Reply(int replyType) {
		this.replyType = replyType;
	}
}
