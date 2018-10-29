
public class Thread_2 extends Thread{
	private Semaphore[] sems;
	
	public Thread_2(Semaphore[] sems, String name) {
		super(name);
		this.sems = sems;
		start();
	}
	
	private void activity_2() {
		System.out.println(super.getName() + " running");
	}
	
	public void run() {
		sems[0].p();
		activity_2();
		//sems[2].v();
		sems[0].v();
	}
}
