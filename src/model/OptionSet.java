package model;

import java.io.Serializable;
import java.util.ArrayList;

import exceptions.AutoException;
import exceptions.ExceptionSets;
/**
 * This class is an option set of an automobile, containing all options available.
 * @author ShuqinYe
 *
 */

public class OptionSet implements Serializable {

	private String name; // The name of the car property.
	private ArrayList<Option> options; // The possible options of the property (OptionSet).
	
	private Option opChoice; // The option chosen by the user.
	
	
	/**
	 * Construct the OptionSet without name.
	 */
	protected OptionSet() {
		this("");
	}
	
	/**
	 * Construct the OptionSet
	 * @param opSize the number of options within one OptionSet
	 * @param name the name of the OptionSet.
	 */
	protected OptionSet(String name) {
		this.name = name;		
		
		// Initialize the ArrayList of options.
		options = new ArrayList<Option>();

	}
	
	/**
	 * Get the OptionSet name.
	 * @return the name of the OptionSet.
	 */
	protected String getName() {
		return name;
	}
	
	/**
	 * Gets the whole OptionSet with an array of Options.
	 * @return the OptionSet with all options.
	 */
	protected ArrayList<Option> getAllOptions() {
		return options;
	}
	
	/**
	 * Get an option with a certain index.
	 * @param opIndex the index of the option that needs to be found.
	 * @return the Option with an index of opIndex.
	 */
	protected Option getOpByIndex(int opIndex) {
		return options.get(opIndex);
	}
	
	/**
	 * Find an option with a certain name.
	 * @param name the name of the Option that needs to be found.
	 * @return the Option with a certain name.
	 */
	protected Option getOpByName(String name) {
		int i = findOpByName(name);
		try {
			if (i == -1) throw new AutoException(ExceptionSets.OPTIONNAMENOTFOUND);
			
		}
		catch(AutoException a) {
			int errNum = a.getErrNum();
			
			// Print the error message
			a.printException(errNum);
			
			// Output the log into a file called log.txt.
			a.log();
			
			// Assign the new setName
			while(true) {
				name = a.fix(errNum);
				i = findOpByName(name);
				if (i != -1) break;
				
				// Print the error message
				a.printException(errNum);
				// Output the log into a file called log.txt.
				a.log();
			}
		}
		return options.get(i);
	}
	
	/**
	 * Find the index of an option
	 * @param name the name of the option to be found.
	 * @return -1 if the option is not found, returns the index if found.
	 */
	protected int findOpByName(String name) {
		int i = 0;
		while (i < options.size() && !options.get(i).getName().equals(name)) ++i;
		return i < options.size() ? i : -1;
	}
	
	/**
	 * Find the index of an option of a certain price.
	 * @param price the price of the option to be found.
	 * @return -1 if not found, returns the index if found.
	 */
	protected int findOpByPrice(float price) {
		int i = 0;
		while (i < options.size() && options.get(i).getPrice() != price) ++i;
		return i < options.size() ? i : -1;
	}
	
	
	/**
	 * Sets the name of OptionSet.
	 * @param name the name of the OptionSet we need to set to.
	 */
	protected void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Update a certain Option in the OptionSet.
	 * @param option the option to be updated into the new option.
	 * @param opIndex the index in the OptionSet the option needs to updated.
	 */
	protected void setOp(int opIndex, String opName, float price) {
		options.get(opIndex).setName(opName);
		options.get(opIndex).setPrice(price);
	}
	
	
	/**
	 * Add one option to the option set.
	 * @param opName the name of the option.
	 * @param price price of the option name.
	 */
	protected void addOp(String opName, float price) {
		Option option = new Option(opName, price);
		options.add(option);
	}
	
	/**
	 * Delete the option at a certain index.
	 * @param opIndex the index of the option to be deleted.
	 */
	protected void deleteOpByIndex(int opIndex) {
		options.remove(opIndex);
	}
	
	/**
	 * Delete the option that has a certain name.
	 * @param name the name of the option to be deleted.
	 */
	protected void deleteOpByName(String name) {
		
		int i = findOpByName(name);
		try {
			// If the name of the option is not found.
			if (i == -1) throw new AutoException(ExceptionSets.OPTIONNAMENOTFOUND);
			
		}
		catch(AutoException a) {
			int errNum = a.getErrNum();
			
			// Print the error message
			a.printException(errNum);
			
			// Output the log into a file called log.txt.
			a.log();
			
			// Assign the new setName
			while(true) {
				name = a.fix(errNum);
				i = findOpByName(name);
				if (i != -1) break;
				
				// Print the error message
				a.printException(errNum);
				// Output the log into a file called log.txt.
				a.log();
			}
		}
		options.remove(i);
	}
	
	/**
	 * Get the whole OptionSet with details of its name and options.
	 * @return a String representation of the OptionSet.
	 */
	protected String getOpSet() {
		StringBuffer str = new StringBuffer();
		str.append("You can choose the following options and prices for ");
		str.append(name);
		str.append(":\n");
		str.append("Option");
		
		// Insert 40 spaces to separate the options and prices.
		for (int i = 0; i < 40; ++i) str.append(" ");

		str.append("Price($)\n");
		
		for (int i = 0; i < options.size(); ++i) {
			str.append(options.get(i).getOption());
		}
		
		str.append("\n");
		return str.toString();
	}
	
	
	// The below methods are used when user wants to configure their own auto options.
	/**
	 * Get the option chosen by the user.
	 * @return the Option chosen by the user.
	 */
	protected Option getOpChoice() {
		return opChoice;
	}
	
	/**
	 * Set the option chosen by the user.
	 */
	protected void setOpChoice(String opName) {
		opChoice = getOpByName(opName);
	}

}
