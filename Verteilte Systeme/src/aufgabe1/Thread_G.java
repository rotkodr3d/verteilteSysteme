package aufgabe1;

public class Thread_G extends Thread {
	private Semaphore[] sems;
	private int[] semsP; //Semaphoren die probiert werden sollen bzw."gebraucht" werden 
	private int[] semsV; //Semaphoren die freigegeben werden 
	
	public Thread_G(Semaphore[] sems, String name,int[] semsP, int[] semsV) {
		super(name);
		this.sems = sems;
		this.semsP = semsP;
		this.semsV = semsV;
		start();
	}
	
	private void activity() {
		System.out.println(super.getName() + " running");
	}
	
	public void run() {
		for (int semNr : semsP)
			sems[semNr].p();
		activity();
		for (int semNr : semsV)
			sems[semNr].v();
	}
}
