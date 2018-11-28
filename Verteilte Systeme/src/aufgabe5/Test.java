package aufgabe5;

public class Test {
	
	public static void main(String[] args) {
		CircularBuffer<String> cb = new CircularBuffer<>(3);
		for (int i = 1; i < 5; i++) {
			cb.add("String_"+i);
		}
		
		for (int i = 0; i < cb.getSize(); i++) {
			System.out.println(cb.pop());
		}
	}
}
