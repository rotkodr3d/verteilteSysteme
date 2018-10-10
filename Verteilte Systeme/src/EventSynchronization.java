
public class EventSynchronization {
	static String name = "Thread_";
	public static void main(String[] args) {
		Semaphore[] sems = new Semaphore[9];
		for (int i = 0; i < sems.length; i++) {
			sems[i] = new Semaphore(0);
		}
		new Thread_1(sems,name+1);
		new Thread_2(sems,name+2);
		new Thread_3(sems,name+3);
		new Thread_4(sems,name+4);
		new Thread_5(sems,name+5);
		new Thread_6(sems,name+6);
		new Thread_7(sems,name+7);
		
	}
}
