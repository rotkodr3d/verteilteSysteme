package aufgabe2;

import javafx.util.Pair;

public class Work {
	int rowC;
	int colC;
	private Pair<Integer,Integer> a_b;
	
	public Work(int a, int b, int rowC, int colC) {
		a_b = new Pair<>(a,b);
		this.rowC = rowC;
		this.colC = colC;
	}
	
	public Pair<Integer,Integer>getWork() {
		return a_b;
	}
}
