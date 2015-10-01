package adapter;

import model.Automobile;

/**
 * This interface gets one Automobile object of a specific auto name.
 * @author ShuqinYe
 *
 */
public interface GetAutomobile {

	public Automobile getAuto(String autoName);
}
