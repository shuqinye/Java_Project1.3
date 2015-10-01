package scale;

import model.Automobile;
import adapter.BuildAuto;
import adapter.GetAutomobile;
import adapter.ProxyAutomobile;
import adapter.UpdateAuto;

/**
 * This class changes an option set name of a specific car in its own thread.
 * @author ShuqinYe
 *
 */
public class EditOptions extends Thread
								implements UpdateAuto, GetAutomobile {

	private ProxyAutomobile autos; // BuildAuto object to access proxyAutomobile.
	private Automobile auto; // Automobile object
	
	/**
	 * Construct an EditOption object.
	 * @param autoName the name of the automobile to be editted.
	 */
	public EditOptions(String id) {
		super(id);
	}
	
	/**
	 * Set the autos => the linkedhashmap.
	 * @param buildAuto the object buildAuto.
	 */
	public void setAutos(ProxyAutomobile buildAuto) {
		this.autos = buildAuto;
	}
	
	
	/**
	 * implementation of GetAutomobile interface.
	 */
	@Override
	public Automobile getAuto(String autoName) {
		// Get the Automobile object with the specified autoName.
		return autos.getAuto(autoName);
	}
	
	/**
	 * Implementation of UpdateAuto interface updateOptionSetName()
	 */
	@Override
	public void updateOptionSetName(String autoName, String setName,
			String newName) {
		// Get the Automobile object.
		auto = autos.getAuto(autoName);
		// call the synchronized method in Automobile class.
		auto.updateOpSetName(setName, newName);
	}

	/**
	 * Implementation of Update interface updateOptionPrice()
	 */
	@Override
	public void updateOptionPrice(String autoName, String setName,
			String opName, float newPrice) {
		// Get the Automobile object.
		auto = autos.getAuto(autoName);
		// call the synchronized method in Automobile class.
		auto.updateOpPrice(setName, opName, newPrice);
		
	}
	
	/**
	 * This method will run when the thread starts.
	 * It updates the name of a specified Option Set => this serves as an
	 * example of how two threads can run simultaneously and modify the same
	 * data.
	 */
	public void run() {
		// Synchronized statement is used because we need to make sure the result
		// is printed to the standard output before the other thread starts occupying
		// the processor.
		synchronized(System.out) {
			updateOptionPrice("Focus Wagon ZTW", "Color", 
					"Fort Knox Gold Clearcoat Metallic", (float) 10.0);
			
			System.out.println("The thread " + getName() + " has been run successfully.");
			System.out.println("The color option of \"Fort Knox Gold Clearcoat Metallic\""
					+ " has been changed to 10 dollars.");
			
			System.out.println("The Automobile after the above change is: ");
			System.out.println(auto.getAuto());
		}

	}


	
	
}
