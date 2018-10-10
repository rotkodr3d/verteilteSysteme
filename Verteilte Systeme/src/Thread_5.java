
public class Thread_5 extends Thread {
	private Semaphore[] sems;
	
	public Thread_5(Semaphore[] sems, String name) {
		super(name);
		this.sems = sems;
		start();
	}
	
	private void activity_5() {
		System.out.println(super.getName() + " running");
	}
	
	public void run() {
		sems[2].p();
		activity_5();
		sems[2].v();
	}
}
