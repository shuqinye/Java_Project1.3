package model;

import java.io.Serializable;

/**
 * This class represents the possible option a car can have within a specific
 * OptionSet(property).
 * @author ShuqinYe
 *
 */
public class Option implements Serializable {
	
	private String name; // Name of the option.
	private float price; // Price of the option.
	
	/**
	 * Default constructor without formal parameters.
	 */
	protected Option() {
		this("noNameYet", 0);
	}
	
	/**
	 * Constructor for option
	 * @param name option name
	 * @param price price
	 */
	protected Option(String name, float price) {
		this.name = name;
		this.price = price;
	}
	
	
	protected String getName() {
		return name;
	}
	
	protected float getPrice() {
		return price;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	protected void setPrice(float price) {
		this.price = price;
	}
	
	/**
	 * This method displays the information of the option as a String.
	 * @return a String that describes the option.
	 */
	protected String getOption() {
		StringBuffer str = new StringBuffer();
		str.append(name);
		int i = 46 - str.length();
		for (int c = 0; c < i; ++c) str.append(" ");
		
		str.append(price);
		str.append("\n");
		
		return str.toString();
	}
	
}
