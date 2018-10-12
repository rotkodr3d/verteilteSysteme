package aufgabe3;

public class ThreadBC extends Thread {
	private int n;
	private int k;
	private static long result;
	
	public static void main(String[] args) {
		ThreadBC t = new ThreadBC(20,5);
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(result);
	}
	
	public ThreadBC(int n, int k) {
		this.n = n;
		this.k = k;
		start();
	}
	
	public long getResult() {
		return result;
	}
	
	@Override
	public void run() {	
		if ((n == k) || (k == 0)) {
            result += 1;
		}
        else {
        	ThreadBC t1 = new ThreadBC(n - 1, k - 1);
        	ThreadBC t2 = new ThreadBC(n - 1, k);
        	try {
				t1.join();
				t2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	
        }
	}
}
