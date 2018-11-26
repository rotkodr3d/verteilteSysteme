package aufgabe4;

import java.io.Serializable;
import java.util.HashMap;

public class Reply implements Serializable{
	public final static int GETSURVEY = 0;
	public final static int VOTE = 1;
	public final static int VOTEANSWER = 2;
	public int replyType;
	public String message; //Fügt Möglichkeit hinzu nur einen String als Antwort zu senden 
	
	public Reply() {
		
	}
	
	public Reply(String message, int replyType) {
		this.message = message;
		this.replyType = replyType;
	}
	
	public Reply(String message) {
		this.message = message;
	}
	
	public Reply(int replyType) {
		this.replyType = replyType;
	}
}
