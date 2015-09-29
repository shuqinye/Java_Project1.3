package driver;

import adapter.BuildAuto;
import adapter.ProxyAutomobile;
import model.Automobile;
import util.FileIO;

/**
 * This class tests the following:
 * - how an auto is built from a file and gets into the databaes of all autos.
 * - how a user inputs his preference and configures the car.
 * - how to get the total price of a configured car.
 * @author ShuqinYe
 *
 */
public class CarConfigTest {

	public static void main(String[] args) {
		
		ProxyAutomobile autos = new BuildAuto();
		
		// Populate Automobile object.
		String filename = "Focus_Wagon_ZTW.txt";
		autos.buildAuto(filename);
		
		// Print the Automobile object before user configures the car.
		autos.printAuto("Focus Wagon ZTW");
		
		// User configures the auto by setting all the options for each option set.
		autos.setOpChoice("Focus Wagon ZTW", "Color", "Infra-Red Clearcoat");
		autos.setOpChoice("Focus Wagon ZTW", "Transmission", "Standard");
		autos.setOpChoice("Focus Wagon ZTW", "Brakes/Traction Control", "ABS");
		autos.setOpChoice("Focus Wagon ZTW", "Side Impace Air Bags", "None");
		autos.setOpChoice("Focus Wagon ZTW", "Power Moonroof", "Selected");
		
		// Calcualte user price for the configured car.
		System.out.print("The total price of the configured car is: ");
		System.out.println(autos.getTotalPrice("Focus Wagon ZTW"));
		
		// Print out the user configured car.
		autos.printConfig("Focus Wagon ZTW");
		
	}
}
