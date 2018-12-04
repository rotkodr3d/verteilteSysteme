package aufgabe5;

public class CircularBuffer<T> {

	private int size = 0;
	private T[] circularBuffer;
	private int in = 0; // zeigt immer auf das nächste freie feld
	private int out = 0;

	@SuppressWarnings("unchecked")
	public CircularBuffer(int size) {
		circularBuffer = (T[]) new Object[size];
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public int getAmountOfEntries() {
		if (in - 1 < 0)
			return 0;
		return in - 1;
	}

	private int wrapIndex(int i) {
		int m = i % size;
		if (m < 0) {
			m += size;
		}
		return m;
	}

	public synchronized void add(T element) {
		while ((in + 1) % size == out) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		circularBuffer[in] = element;
		in = wrapIndex(in + 1);
		notifyAll();
	}

	public synchronized T pop() {
		while (in == out) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		T element = circularBuffer[out];
		circularBuffer[out] = null;
		out = (out + 1) % size;
		notifyAll();
		return element;
	}
}
