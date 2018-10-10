
public class Thread_1 extends Thread {
	private Semaphore[] sems;
	
	public Thread_1(Semaphore[] sems, String name) {
		super(name);
		this.sems = sems;
		start();
	}
	
	private void activity_1() {
		System.out.println(super.getName() + " running");
	}
	
	public void run() {
		activity_1();
		sems[0].v();
		sems[1].v();
	}
}
