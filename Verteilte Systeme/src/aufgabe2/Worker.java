package aufgabe2;

import aufgabe1.Semaphore;
import javafx.util.Pair;

public class Worker extends Thread{
	
	private Work work;
	private static Semaphore sem = new Semaphore(1);
	private static Master master;
	private int work_limit = 0;
	private int work_done = 0;
	private boolean hasLimit = false;
	
	public Worker(Master master) {
		if (Worker.master == null)
			Worker.master = master;	
	}
	
	public Worker(Work work, Master master, int work_limit) {
		this.work_limit = work_limit;
		this.work = work;
		hasLimit = true;
		if (Worker.master == null)
			Worker.master = master;	
	}
	
	public Worker(Work work, Master master) {
		this.work = work;
		if (Worker.master == null)
			Worker.master = master;	
	}
	
	
	@Override
	public void run() {
		while(work != null) {
			calcTellResult();
		}
	}
	
	public void calcTellResult() {
		Pair<int[],int[]> p = work.getWork();
		int result = 0;
		for(int i = 0; i < p.getKey().length; i++) {
			result += p.getKey()[i]*p.getValue()[i];
		}
		if (hasLimit)
			work_done++;
		sem.p();	
		if (work_done <= work_limit)
			work = master.tellResultGetWork(new MatrixElement(result,work.rowC,work.colC));
		else if (work_done >= work_limit) {
			master.tellResult(new MatrixElement(result,work.rowC,work.colC));
			work = null;
		}
		sem.v();
	}
}
