package aufgabe5;

import java.io.Serializable;

public class Reply<T> implements Serializable{
	
	T replyContent;
	
	public Reply() {
		
	}
	
	public Reply(T replyContent) {
		this.replyContent = replyContent;
	}
}
