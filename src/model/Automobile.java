package model;

import java.io.Serializable;
import java.util.ArrayList;

import exceptions.AutoException;
import exceptions.ExceptionSets;

/**
 * This class represents a specific car model and all its possible
 * properties (OptionSets)
 * All the methods in the class is synchronized so that the CRUD (create, read,
 * update, delete) options on the object are thread safe.
 * @author ShuqinYe
 * @andrewID shuqiny
 */
public class Automobile implements Serializable {

	// Member variables about the car - common to all configurations.
	private String name; // The car name = brand + model.
	private String make; // The brand
	private String model; // The model
	private float basePrice; // The car model's base price.
	
	// Member variables related to options and option sets.
	private ArrayList<OptionSet> opSets; // All possible option sets for the car.
	

	/**
	 * This is a constructor for a car model.
	 * @param size the number of properties specified by the car model.
	 * @param name the name of the car model.
	 */
	public Automobile(String make, String model, float basePrice) {
		this.make = make;
		this.model = model;
		this.name = make + " " + model;
		this.basePrice = basePrice;
		
		// Initialize the ArrayList.
		opSets = new ArrayList<OptionSet>();
		
		}
	
	/**
	 * @param opSet the OptionSet to be added
	 */
	synchronized public void addOpSet(OptionSet opSet) {
		opSets.add(opSet);
	}
	
	
	/**
	 * Add an empty set to the auto.
	 * @param setName the option set name.
	 */
	synchronized public void addEmptyOpSet(String setName) {
		OptionSet opSet = new OptionSet(setName);
		opSets.add(opSet);
	}
	
	
	/**
	 * Add one option to a certain option set.
	 * @param setName the option set name.
	 * @param opName the option name to be added.
	 * @param price the price of the option.
	 */
	synchronized public void addOption(String setName, String opName, float price) {
		Option option = new Option(opName, price);
		getOpSetByName(setName).addOp(opName, price);
	}
	

	/**
	 * Get the car model name.
	 * @return car model as a String.
	 */
	synchronized public String getName() { return make + " " + model; }
	
	/**
	 * Get the base price of the model.
	 * @return the base price.
	 */
	synchronized public float getBasePrice() { return basePrice; }
	
	/**
	 * Get the brand name
	 * @return the brand name
	 */
	synchronized public String getMake() { return make; }
	
	
	/**
	 * Get the car model
	 * @return the car model
	 */
	synchronized public String getModel() { return model; }
	
	
	
	
	/**
	 * Get the complete OptionSet of the car.
	 * @return the OptionSet of the car.
	 */
	synchronized public ArrayList<OptionSet> getAllOptionSets() {
		return opSets;
	}
	
	/**
	 * get OptionSet by index value
	 * @param setIndex the index of the OptionSet in the array of all OptionSets.
	 * @return the OptionSet for the specified index.
	 */
	synchronized public OptionSet getOpSetByIndex(int setIndex) {
		return opSets.get(setIndex);
	}
	
	
	/**
	 * Get OptionSet by name
	 * @param setName the option set name
	 * @return the OptionSet of a certain name
	 */
	synchronized public OptionSet getOpSetByName(String setName) {
		int i = 0;
		while (i < opSets.size() && !opSets.get(i).getName().equals(setName)) ++i;
		return opSets.get(i);
	}

	
	/**
	 * find the option set of name setName.
	 * @param setName the name of option set to be found.
	 * @return -1 if not found, index of the option set if found.
	 */
	synchronized public int findOpSetByName(String setName) {
		int i = 0;
		while (i < opSets.size() && !opSets.get(i).getName().equals(setName)) ++i;
		return i < opSets.size() ? i : -1;
	}
	
	
	/**
	 * Find an option with a certain name in all option sets
	 * @param opName the option name
	 * @return -1 if not found, index within its option set if found.
	 */
	synchronized public int findOpByName(String opName) {
		int i = 0;
		while (i < opSets.size() && opSets.get(i).findOpByName(opName) == -1) ++i;
		return i < opSets.size() ? opSets.get(i).findOpByName(opName) : -1;
	}
	
	/**
	 * Set car base price.
	 * @param basePrice the car base price.
	 */
	synchronized public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	/**
	 * Set the brand name
	 * @param make brand name
	 */
	synchronized public void setMake(String make) { this.make = make; }
	
	
	/**
	 * Set the model
	 * @param model the car model
	 */
	synchronized public void setModel(String model) { this.model = model; }
	
	
	/**
	 * Set the option set to opSet at the index of opSetIndex
	 * @param opSet the new option set
	 * @param opSetIndex the index of the option set that needs replacement
	 */
	synchronized public void setOpSet(int setIndex, OptionSet opSet) {
		opSets.add(setIndex, opSet);
	}
	
	
	/**
	 * Set the name of a certain option set.
	 * @param setIndex index of option set
	 * @param opSetName name of option set.
	 */
	synchronized public void setOpSetName(int setIndex, String opSetName) {
		opSets.get(setIndex).setName(opSetName);
	}
	
	/**
	 * set the option in a certain option set.
	 * @param name the name of the option set.
	 * @param opIndex the index of the option in the option set.
	 */
	synchronized public void setOpBySetName(String setName, int opIndex, 
			String opName, float opPrice) {
		int i = 0;
		while (i < opSets.size() && !opSets.get(i).getName().equals(setName)) ++i;
		opSets.get(i).setOp(opIndex, opName, opPrice);
	}
	
	
	/**
	 * Set option value at a certain index for option set at a certain index.
	 * @param setIndex the option set index
	 * @param opIndex the option index in which the option is to be set
	 * @param option the option
	 */
	synchronized public void setOpBySetIndex(int setIndex, int opIndex, 
			String opName, float opPrice) {
		opSets.get(setIndex).setOp(opIndex, opName, opPrice);
	}
	
	/**
	 * update an option set of a certain name.
	 * @param setName the set to be updated
	 * @param opSet the new option set
	 */
	synchronized public void updateOpSet(String setName, OptionSet opSet) {
		setOpSet(findOpSetByName(setName), opSet);
	}
	
	/**
	 * Update the opSet name of a give opSet
	 * @param setName the opSet name to be changed
	 * @param newName the new opSet name
	 */
	synchronized public void updateOpSetName(String setName, String newName) {
		int index = findOpSetByName(setName);
		
		try {
			if (index == -1) throw new AutoException(ExceptionSets.SETNAMENOTFOUND);		
		}
		catch(AutoException a) {
			int errNum = a.getErrNum();
			
			// Print the error message
			a.printException(errNum);
			
			// Output the log into a file called log.txt.
			a.log();
			
			// Assign the new setName
			while(true) {
				setName = a.fix(errNum);
				index = findOpSetByName(setName);
				if (index != -1) break;
				
				// Print the error message
				a.printException(errNum);
				// Output the log into a file called log.txt.
				a.log();
			}
		}
		
		setOpSetName(index, newName);
	}
	
	/**
	 * update an option in a certain option set.
	 * @param setName the set to be updated.
	 * @param opName the option to be updated.
	 * @param option the new option.
	 */
	synchronized public void updateOp(String setName, String opName, float opPrice) {
		int setIndex = findOpSetByName(setName);
		
		try {
			if (setIndex == -1) throw new AutoException(ExceptionSets.SETNAMENOTFOUND);		
		}
		catch(AutoException a) {
			int errNum = a.getErrNum();
			
			// Print the error message
			a.printException(errNum);
			
			// Output the log into a file called log.txt.
			a.log();
			
			// Assign the new setName
			while(true) {
				setName = a.fix(errNum);
				setIndex = findOpSetByName(setName);
				if (setIndex != -1) break;
				
				// Print the error message
				a.printException(errNum);
				// Output the log into a file called log.txt.
				a.log();
			}
		}
		
		int opIndex = opSets.get(setIndex).findOpByName(opName);
		opSets.get(setIndex).setOp(opIndex, opName, opPrice);
	}
	
	/**
	 * Update the option price
	 * @param setName the opSet name
	 * @param opName the option name
	 * @param opPrice the new price of the option
	 */
	synchronized public void updateOpPrice(String setName, String opName, float opPrice) {
		int setIndex = findOpSetByName(setName);
		
		try {
			if (setIndex == -1) throw new AutoException(ExceptionSets.SETNAMENOTFOUND);		
		}
		catch(AutoException a) {
			int errNum = a.getErrNum();
			
			// Print the error message
			a.printException(errNum);
			
			// Output the log into a file called log.txt.
			a.log();
			
			// Assign the new setName
			while(true) {
				setName = a.fix(errNum);
				setIndex = findOpSetByName(setName);
				if (setIndex != -1) break;
				
				// Print the error message
				a.printException(errNum);
				// Output the log into a file called log.txt.
				a.log();
			}
		}
		
		opSets.get(setIndex).getOpByName(opName).setPrice(opPrice);
	}
	
	
	/**
	 * Delete an option set at a certain index
	 * @param setIndex index of the set to be deleted
	 */
	synchronized public void deleteOpSetByIndex(int setIndex) {
		opSets.remove(setIndex);
	}
	
	
	/**
	 * Delete an option set of a certain name
	 * @param setName name of option set to be deleted
	 */
	synchronized public void deleteOpSetByName(String setName) {
		opSets.remove(findOpSetByName(setName));
	}
	
	/**
	 * Delete an option at a certain index, within option set at a certain index.
	 * @param setIndex index of option set
	 * @param opIndex index of option to be deleted
	 */
	synchronized public void deleteOpByIndex(int setIndex, int opIndex) {
		opSets.get(setIndex).deleteOpByIndex(opIndex);
	}
	
	/**
	 * Delete an option at a certain index, within option set of name setName
	 * @param setName name of option set the option is in
	 * @param opIndex index of option to be deleted
	 */
	synchronized public void deleteOpByIndex(String setName, int opIndex) {
		opSets.get(findOpSetByName(setName)).deleteOpByIndex(opIndex);
	}
	
	/**
	 * Delete an option of a certain name, within option set at a certain index.
	 * @param setIndex option set index.
	 * @param opName name of option to be deleted
	 */
	synchronized public void deleteOpByName(int setIndex, String opName) {
		opSets.get(setIndex).deleteOpByName(opName);
	}
	
	/**
	 * Delete an option of a certain name, within option set of a certain name
	 * @param setName name of option set the option is in
	 * @param opName name of option to be deleted
	 */
	synchronized public void deleteOpByName(String setName, String opName) {
		opSets.get(findOpSetByName(setName)).deleteOpByName(opName);
	}
	
	
	/**
	 * Output all option sets and options within each option set.
	 * @return a String representation of all option sets and options.
	 */
	synchronized public String getAuto() {
		StringBuffer str = new StringBuffer();
		str.append("The car ");
		str.append(make);
		str.append(" ");
		str.append(model);
		str.append(" has ");
		str.append(opSets.size());
		str.append(" property settings.\n\n");
		str.append("Its base price is ");
		str.append(basePrice);
		str.append(" dollars.\n");
		
		for (int i = 0; i < opSets.size(); ++i) {
			str.append(opSets.get(i).getOpSet());
		}
	
		return str.toString();
	}
	
	
	// Below methods are used when user wants to configure their auto.
	
	/**
	 * Get the name of the option chosen for a certain option set.
	 * @param setName the option set name.
	 * @return the name of the option chosen for the option set.
	 */
	synchronized public String getOpChoice(String setName) {
		return getOpSetByName(setName).getOpChoice().getName();
	}
	
	/**
	 * Get the price of the option chosen for a certain option set.
	 * @param setName the name of the option set.
	 * @return the price of the option for the option set.
	 */
	synchronized public float getOpChoicePrice(String setName) {
		return getOpSetByName(setName).getOpChoice().getPrice();
	}
	
	/**
	 * Set the user preferred options for an option set.
	 * @param setName the option set name.
	 * @param opName the option name the user chooses.
	 */
	synchronized public void setOpChoice(String setName, String opName) {
		getOpSetByName(setName).setOpChoice(opName);
	}

	
	/**
	 * Get the total price of the car of user's choice
	 * @return the total price of the car of a certain configuration.
	 */
	synchronized public float getTotalPrice() {
		float totalPrice = 0;
		totalPrice += basePrice;
		for (int i = 0; i < opSets.size(); ++i)
			totalPrice += getOpSetByIndex(i).getOpChoice().getPrice();
		return totalPrice;
	}
	
	
	/**
	 * Print out the user configured car.
	 */
	synchronized public void printConfig() {
		System.out.println("Your car configuration is as follows:");
		System.out.print("Car make: ");
		System.out.println(make);
		System.out.print("Car model: ");
		System.out.println(model);
		System.out.print("Base price: ");
		System.out.println(basePrice);
		
		for (int i = 0; i < opSets.size(); ++i) {
			System.out.println(getOpSetByIndex(i).getName());
			System.out.print(getOpSetByIndex(i).getOpChoice().getOption());
		}
		
	}
	
}
	
