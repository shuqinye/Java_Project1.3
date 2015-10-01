package adapter;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import util.FileIO;
import model.Automobile;

/**
 * This class hides the object Automobile but provides an API for user to access
 * API.
 * 
 * @author ShuqinYe
 * @andrewID shuqiny
 *
 */
public abstract class ProxyAutomobile {
	
	private static LinkedHashMap<String, Automobile> autos = new LinkedHashMap<String, Automobile>();
	
	
	/**
	 * Get the Automobile object with a specified Auto name.
	 * 
	 * @param name the Auto name = make + model
	 * @return the Automobile with the specified name
	 */
	public Automobile getAuto(String name) {
		Set<String> keySet = autos.keySet();
		Iterator<String> i = keySet.iterator();
		
		while (i.hasNext()) {
			String thisName = i.next();
			if (name.equals(thisName)) return autos.get(name);
		}
		
		return null;
	}
	
	
	/**
	 * Build an Automobile object from a file.
	 */
	public void buildAuto(String filename) {
		
		FileIO fileio = new FileIO();
		Automobile oneAuto = fileio.buildAutoObj(filename);
		String name = oneAuto.getName();
		autos.put(name, oneAuto);
		
	}
	
	/**
	 * Print the whole Automobile object from a file.
	 */
	public void printAuto(String name) {
		Set<String> keySet = autos.keySet();
		Iterator<String> i = keySet.iterator();
		
		while (i.hasNext()) {
			String thisName = i.next();
			if (name.equals(thisName)) {
				System.out.println(autos.get(name).getAuto());
				break;
			}
		}
		
	}
	

	
	/**
	 * Update option set name for an automobile with a given name
	 */
	public void updateOptionSetName(String name, String setName,
			String newName) {
		Set<String> keySet = autos.keySet();
		Iterator<String> i = keySet.iterator();
		
		while (i.hasNext()) {
			String thisName = i.next();
			if (name.equals(thisName)) {
				autos.get(name).updateOpSetName(setName, newName);
				break;
			}
		}
	}


	/**
	 * Update option price
	 */
	public void updateOptionPrice(String name, String setName, 
			String opName, float newPrice) {
		
		Set<String> keySet = autos.keySet();
		Iterator<String> i = keySet.iterator();
		
		while (i.hasNext()) {
			String thisName = i.next();
			if (name.equals(thisName)) {
				autos.get(name).updateOpPrice(setName, opName, newPrice);
				break;
			}
		}
	}
	
	
	/**
	 * Get the total price of user options for the car.
	 * @param name the car name
	 * @return the total price
	 */
	public float getTotalPrice(String name) {
		Set<String> keySet = autos.keySet();
		Iterator<String> i = keySet.iterator();
		
		while (i.hasNext()) {
			String thisName = i.next();
			if (name.equals(thisName)) {
				return autos.get(name).getTotalPrice();
			}
		}
		return 0;
	}
	
	
	/**
	 * Set the option for a car.
	 * @param name the car name
	 * @param setName option set name
	 * @param opName option name
	 */
	public void setOpChoice(String name, String setName, String opName) {
		Set<String> keySet = autos.keySet();
		Iterator<String> i = keySet.iterator();
		
		while (i.hasNext()) {
			String thisName = i.next();
			if (name.equals(thisName)) {
				autos.get(name).setOpChoice(setName, opName);
			}
		}
	}
	
	
	/**
	 * Print the configuration for a user defined car
	 * @param name the car name
	 */
	public void printConfig(String name) {
		Set<String> keySet = autos.keySet();
		Iterator<String> i = keySet.iterator();
		
		while (i.hasNext()) {
			String thisName = i.next();
			if (name.equals(thisName)) {
				autos.get(name).printConfig();
			}
		}
	}
	

}
