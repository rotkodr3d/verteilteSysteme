
public class Thread_6 extends Thread {
private Semaphore[] sems;
	
	public Thread_6(Semaphore[] sems, String name) {
		super(name);
		this.sems = sems;
		start();
	}
	
	private void activity_6() {
		System.out.println(super.getName() + " running");
	}
	
	public void run() {
		sems[2].p();
		activity_6();
		sems[2].v();
	}
}
