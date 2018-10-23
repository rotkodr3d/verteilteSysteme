package aufgabe2;

import java.util.Stack;

import aufgabe1.Semaphore;
import javafx.util.Pair;

public class Worker extends Thread{
	
	private Work work;
	private static Semaphore sem = new Semaphore(1);
	private static Master master;
	private boolean parallelism = false;
	private Stack<Work> workStack;
	
	public Worker(Master master) {
		if (Worker.master == null)
			Worker.master = master;	
	}
	
	public Worker(Master master, Stack<Work> work) {
		parallelism = true;
		if (Worker.master == null)
			Worker.master = master;
		workStack = work;
	}
	
	
	public Worker(Work work, Master master) {
		this.work = work;
		if (Worker.master == null)
			Worker.master = master;	
	}
	
	
	@Override
	public void run() {
		if (!parallelism)
			while(work != null) {
				calcTellResult();
			}
		else
			while(!workStack.isEmpty()) {
				paralellism();
		}
	}
	
	public void paralellism() {
		Pair<int[],int[]> p;
		Stack<MatrixElement> resultStack = new Stack<>();
		for (Work w : workStack) {
			p = w.getWork();
			int result = 0;
			for (int i = 0; i < p.getKey().length; i++) {
				result += p.getKey()[i] * p.getValue()[i];
			}
		resultStack.push(new MatrixElement(result,w.rowC,w.colC));
		}
		sem.p();
		master.tellResult(resultStack);
		sem.v();
		workStack.clear();
	}
	
	public void calcTellResult() {
		Pair<int[],int[]> p = work.getWork();
		int result = 0;
		for(int i = 0; i < p.getKey().length; i++) {
			result += p.getKey()[i]*p.getValue()[i];
		}
		sem.p();	
			work = master.tellResultGetWork(new MatrixElement(result,work.rowC,work.colC));
		sem.v();
	}
}
