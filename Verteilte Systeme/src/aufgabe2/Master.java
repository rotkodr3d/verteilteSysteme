package aufgabe2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Master {
	static Master master;
	private int[][] mat_a = {
							{1,-2,3,4,-1},
							{-2,3,0,1,2},
							{4,-1,2,1,-2},
							{-2,1,3,-1,3},
							{0,2,-1,2,4}
							};
	
	private int[][] mat_b = {
							{2,-4,-1,1,-2},
							{-1,1,-2,2,1},
							{5,0,3,-2,-4},
							{1,-2,1,0,2},
							{2,3,-3,0,0}
							};
	
	private int n = mat_a.length;
	private int m = mat_b[0].length;
	
	private int [][] mat_c = new int[n][m];
	private int numberThreads = 25;
	
	private int mode = 0; //0 = Master-Worker; 1 = Parallelismus
	
	private List<Worker> workers = new ArrayList<>(); 
	private Stack<Work> work = new Stack<>();
	
	
	public Work tellResultGetWork(MatrixElement me) {
		int row = me.getRow();
		int col = me.getCol();
		mat_c[row][col] = me.getValue();
		return getWork();
	}
	
	public void tellResult(MatrixElement me) {
		int row = me.getRow();
		int col = me.getCol();
		mat_c[row][col] = me.getValue();	
	}
	
	public Work getWork() {
		if (!work.isEmpty())
			return work.pop();
		else
			return null;
	}
	
	public int[] getColumn(int[][] matrix, int column) {
		int[] r_matrix = new int[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			r_matrix[i] = matrix[i][column];
		}
		return r_matrix;
	}
	
	public Master(int mode, int threads) {
		numberThreads = threads; 
		createWork();
		mode = (mode != 0 && mode != 1) ? 0 : mode;
		switch(mode) {
		
		case 0:
			createStartWorker();
			waitAndPrintResult(); 
			break;
		
		case 1:
			manageParallel();
			waitAndPrintResult(); 
			break;
		}
	}
	
	public Master() {}
	
	public void manageParallel() {
		int workPerThread = Math.floorDiv(work.size(),numberThreads);
		int restWork = (work.size()%numberThreads);
		createStartWorker(workPerThread,restWork);
		System.out.println(workPerThread + " " + restWork + " " + work.size());
	}
	
	public void createWork() {
		for (int i = 0; i < mat_c.length; i++) {
			for (int j = 0; j < mat_c[0].length; j++) {
				work.add(new Work(mat_a[i],getColumn(mat_b,j),i,j));			
			}
		}
	}
	
	public void createStartWorker(int workPerThread, int restWork) {	
		int rest = restWork;
		for (int amount = 0; amount < numberThreads; amount++) {
			if (!work.isEmpty()) {
				Worker w; 
				if (rest != 0) {
					w = new Worker(work.pop(),this,workPerThread+1);
					rest--;
				}
				else
					w = new Worker(work.pop(),this,workPerThread);
				w.start();
				workers.add(w);
			}
		}
	}
	
	public void createStartWorker() {	

		for (int amount = 0; amount < numberThreads; amount++) {
			if (!work.isEmpty()) {
				Worker w = new Worker(work.pop(),this);
				w.start();
				workers.add(w);
			}
		}
	}
	
	public void waitAndPrintResult() {
		for(Worker worker : workers) {
			try {
				worker.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < mat_c.length; i++) {
			System.out.print("[");
			for(int j = 0; j < mat_c[i].length; j++) {
				System.out.printf("%4d",mat_c[i][j]);
			}
			System.out.printf("%4s","]\n");
		}
	}
}
