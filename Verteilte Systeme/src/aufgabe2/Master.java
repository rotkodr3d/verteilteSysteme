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
	
	private List<Worker> workers = new ArrayList<>(); 
	private Stack<Work> work = new Stack<>();
	
	
	public static void main(String[] args) {
		master = new Master();
		master.Manage();
	}
	
	public void tellResult(MatrixElement me) {
		int row = me.getRow();
		int col = me.getCol();
		mat_c[row][col] = mat_c[row][col] + me.getValue();
	}
	
	public Master() {}
	
	public void Manage() {
		for(int i = 0; i < mat_c.length; i++) { //Iteration über Zeilen in Matrix C
			for(int k = 0; k < mat_c.length; k++) { //Iteration über Spalten in Matrix C
				for(int j = 0; j < mat_b.length; j++) { //Iteration über Zeilen von A/Spalten von B
					work.add(new Work(mat_a[i][j],mat_b[j][k],i,k));
				}
			}
		}
		
		for (int amount = 0; amount < 25; amount++) {
			Worker w = new Worker(work.pop(),master);
			w.start();
			workers.add(w);
		}
		
		
		while(!workers.isEmpty()) {
			for(int i = 0; i < workers.size(); i++) {
				Worker worker = workers.get(i);
				try {
					worker.join();
					if (!work.isEmpty()) {
						Worker w = new Worker(work.pop(),master);
						w.start();
						workers.add(w);
					}
					workers.remove(i);
					System.out.println(workers.size());//worker.getState()+" "+worker.getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
