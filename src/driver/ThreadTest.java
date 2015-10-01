package driver;

import adapter.BuildAuto;
import adapter.ProxyAutomobile;
import scale.EditOptions;

/**
 * This class tests runs 2 threads which execute the same operations at the same
 * time. It demonstrate the concept of how the synchronized modifier will lock 
 * the access of only one thread to a certain set of data.
 * @author ShuqinYe
 *
 */
public class ThreadTest {
	
	public static void main(String[] args) {
		
		// Initiate new proxyautomobile linkedhashmap.
		ProxyAutomobile autos = new BuildAuto();
		
		// Populate all the option sets and options to the autos object.
		autos.buildAuto("Focus_Wagon_ZTW.txt");
		
		// Create thread 1.
		EditOptions editOp1 = new EditOptions("1");
		editOp1.setAutos(autos);
		
		// Create thread 2.
		EditOptions editOp2 = new EditOptions("2");
		editOp2.setAutos(autos);
		
		// Start 2 threads simultaneously.
		editOp1.start();
		editOp2.start();
		
		
		// Track when the thread is dead.
		boolean t1IsAlive = true;
		boolean t2IsAlive = true;
		
		do {
			
		if (t1IsAlive && !editOp1.isAlive()) {
			t1IsAlive = false;
			System.out.println("t1 is dead."); 
		}
		if (t2IsAlive && !editOp2.isAlive()) { 
			t2IsAlive = false;
			System.out.println("t2 is dead.");
		}
		} while(t1IsAlive || t2IsAlive);

	}


	
	
	
	
	
}
