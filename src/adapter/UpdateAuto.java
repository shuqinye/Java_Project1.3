package adapter;

/**
 * This interface provides API for updating an option set name and updating 
 * the option price.
 * @author ShuqinYe
 *
 */
public interface UpdateAuto {
	
	public void updateOptionSetName(String autoName, String setName, 
			String newName);
	
	public void updateOptionPrice(String autoName, String setName, 
			String opName, float newPrice);
	


}
