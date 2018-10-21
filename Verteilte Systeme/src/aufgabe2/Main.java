package aufgabe2;

import java.awt.Desktop;
import java.util.Scanner;

public class Main {
	
	private static Master master;
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("In what mode should this application run?\n0 = Worker-Master | 1 = Parallelism");
		int mode = parseStrToInt(s.next());
		System.out.println("Please enter the amount of threads:");
		int threads = parseStrToInt(s.next());
		threads = (threads == 0) ? Runtime.getRuntime().availableProcessors() : threads; 
		
		master = new Master(mode,threads);
		//master.manageMasterWoker();
	}
	
	private static int parseStrToInt(String s) {
		int i = 0;
		try {
			 i = Integer.parseInt(s);
		} catch(NumberFormatException nfe) {
			
		}
		return i;
	}

}
