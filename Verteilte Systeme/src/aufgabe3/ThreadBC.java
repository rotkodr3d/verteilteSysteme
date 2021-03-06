package aufgabe3;

import aufgabe1.Semaphore;

public class ThreadBC extends Thread {
	private int n;
	private int k;
	private static long result;
	
	Semaphore sem = new Semaphore(1);
	
	public static void main(String[] args) {
		int nIn = Integer.parseInt(args[0]); 
		int kIn = Integer.parseInt(args[1]);
		
		if (nIn <= 0 || nIn >= 21) {
			System.out.println("Ung�ltiger Wert f�r n! \nF�r n ist nur dieser Bereich erlaubt: 0 < n < 21");
			System.exit(1);
		}	
		ThreadBC t = new ThreadBC(nIn,kIn);
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
			sem.p();
            result += 1;
            sem.v();
		}
		else if (k == 1 || k == n-1) {
			sem.p();
			result += n;
			sem.v();
		}
		
        else {
        	if (k > n/2) {
        		k = n-k;
        	}
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
