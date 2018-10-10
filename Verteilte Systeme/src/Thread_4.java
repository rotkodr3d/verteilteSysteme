
public class Thread_4 extends Thread{
	private Semaphore[] sems;
	
	public Thread_4(Semaphore[] sems, String name) {
		super(name);
		this.sems = sems;
		start();
	}
	
	private void activity_4() {
		System.out.println(super.getName() + " running");
	}
	
	public void run() {
		sems[2].p();
		activity_4();
		sems[2].v();
	}
}
