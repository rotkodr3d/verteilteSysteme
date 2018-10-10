
public class Thread_7 extends Thread {
	private Semaphore[] sems;
	
	public Thread_7(Semaphore[] sems, String name) {
		super(name);
		this.sems = sems;
		start();
	}
	
	private void activity_7() {
		System.out.println(super.getName() + " running");
	}
	
	public void run() {
		sems[2].p();
		activity_7();
		sems[2].v();
	}
}
