package aufgabe2;

import aufgabe1.Semaphore;
import javafx.util.Pair;

public class Worker extends Thread{
	
	int a;
	int b;
	Work work;
	static Semaphore sem = new Semaphore(1);
	static Master master;
	
	public Worker(Master master) {
		if (Worker.master == null)
			Worker.master = master;	
	}
	
	public Worker(int a, int b, Master master) {
		this.a = a;
		this.b = b;
		Worker.master = master == null ? master: Worker.master;
	}
	
	public Worker(Work work, Master master) {
		this.work = work;
		if (Worker.master == null)
			Worker.master = master;	
	}
	
	public void addWork(Work work) {
		this.work = work;
	}
	
	@Override
	public void run() {
		calcTellResult();
	}
	
	public void calcTellResult() {
		Pair<Integer,Integer> p = work.getWork();
		int result = p.getKey()*p.getValue();
		sem.p();
		master.tellResult(new MatrixElement(result,work.rowC,work.colC));
		sem.v();
	}
}
