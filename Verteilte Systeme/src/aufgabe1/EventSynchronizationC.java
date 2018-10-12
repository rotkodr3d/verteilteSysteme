package aufgabe1;

public class EventSynchronizationC {
	static String name = "Thread_";
	public static void main(String[] args) {
		Semaphore[] sems = new Semaphore[9];
		for (int i = 0; i < sems.length; i++) {
			sems[i] = new Semaphore(0);
		}
		new Thread_G(sems,name+1,new int[] {},new int[] {0,1});
		new Thread_G(sems,name+2,new int[] {0},new int[] {2,3});
		new Thread_G(sems,name+3,new int[] {1},new int[] {4,5});
		new Thread_G(sems,name+4,new int[] {2},new int[] {6});
		new Thread_G(sems,name+5,new int[] {3,4},new int[] {7});
		new Thread_G(sems,name+6,new int[] {5},new int[] {8});
		new Thread_G(sems,name+7,new int[] {6,7,8},new int[] {});
		
	}
}