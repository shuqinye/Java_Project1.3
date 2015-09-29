package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import exceptions.AutoException;
import exceptions.ExceptionSets;
import model.Automobile;

/**
 * This class:
 * - Build an automobile from a file input.
 * - Serialize it to a .ser file.
 * - Deserialize the .ser file and retrieve the Automobile object.
 * @author ShuqinYe
 *
 */
public class FileIO {
	
	public FileIO() {};
	
	/**
	 * Reads a file and build Automobile object with all option sets and options.
	 * @param filename the file to read the input from.
	 * @return an automobile with all option sets and options.
	 */
	public Automobile buildAutoObj(String filename) {
		

		Automobile auto = null;
		
		try {
			// Create the input file stream.
			FileReader reader = new FileReader(new File(filename));
			BufferedReader buffer = new BufferedReader(reader);
			
			String line = buffer.readLine(); // first line
			
			// tokens contains 3 Strings
			// - 0th is make, 1st is model, 2nd is basePrice.
			String[] tokens = line.split("\t");
			String make = tokens[0];
			String model = tokens[1];
			float basePrice = (float) Integer.parseInt(tokens[2]);
			
			try {
				// If the model name is blank.
				if (model.equals(" ")) 
					throw new AutoException(ExceptionSets.MODELNAMENOTFOUND);
			}
			
			catch(AutoException a) {
				int errNum = a.getErrNum();
				// Print the error message
				a.printException(errNum);
				// Output the log into a file called log.txt.
				a.log();
				buffer.close();
				return null;
			}
			
			
			// Create Automobile object and initializes it
			auto = new Automobile(make, model, basePrice);
			
			String setName = ""; // The name of an option set.
			// Read the rest of the file line by line until end of the file.
			while (line != null) {
				line = buffer.readLine();
				
				// if it reaches the end of the document.
				if (line == null) break;
				
				// If line is not empty.
				String[] values = line.split("\t");
				
				// The line is the option set name.
				if(values.length == 1) {
					setName = values[0];
					auto.addEmptyOpSet(setName);
				}
				
				// The lins is one option with name and price.
				else {
					String opName = values[0];
					float opPrice = (float) Integer.parseInt(values[1]);
					auto.addOption(setName, opName, opPrice);
				}
				
			} // Finish reading lines.
			
			buffer.close();

		}
		
		catch(FileNotFoundException f) {
			AutoException a = new AutoException(ExceptionSets.FILENOTFOUND);
			
			int errNum = a.getErrNum();
			
			// Print the error message
			a.printException(errNum);
			
			// Output the log into a file called log.txt.
			a.log();
		}
		
		catch(IOException i) {
			i.printStackTrace();
		}
		
		return auto;
		
	}
	
	/**
	 * Serialize the Automobile object to a .ser file.
	 * @param auto the Automobile object
	 * @return .ser file name 
	 */
	public String SerializeAuto(Automobile auto) {
		
		String filename = "auto.ser";
		
		try {
			FileOutputStream out = new FileOutputStream(filename);
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			objOut.writeObject(auto);
			objOut.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		
		return filename;
	}
	
	
	/**
	 * Read a .ser file and deserialize the file to the origina object.
	 * @param filename the name of the file
	 * @return an Automobile object
	 */
	public Automobile DeserializeAuto(String filename) {
		Automobile auto = null;
		
		try {
			FileInputStream in = new FileInputStream(filename);
			ObjectInputStream objIn = new ObjectInputStream(in);
			auto = (Automobile) objIn.readObject();
			objIn.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		
		return auto;
	}
	
	
}
