
public class Thread_3 extends Thread {
	private Semaphore[] sems;
	
	public Thread_3(Semaphore[] sems, String name) {
		super(name);
		this.sems = sems;
		start();
	}
	
	private void activity_3() {
		System.out.println(super.getName() + " running");
	}
	
	public void run() {
		sems[2].p();
		activity_3();
		sems[2].v();
	}
}
