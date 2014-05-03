package com.minnymin.zephyrus.enchant;

import com.minnymin.zephyrus.Manager;

/**
 * Zephyrus - EnchantManager.java <br>
 * An interface used to handle all enchantment registering and calling
 * 
 * @author minnymin3
 * 
 */

public interface EnchantManager extends Manager {

	/**
	 * Gets the enchant by the specified id
	 * 
	 * @param id The id to find
	 * @return An enchant object that has been registered
	 */
	public Enchant getEnchant(int id);

	/**
	 * Gets the id of the enchant with the specified name
	 * 
	 * @param name The name to find
	 * @return An enchant object that has been registered
	 */
	public int getEnchant(String name);

	/**
	 * Registers an enchant with the next id
	 * 
	 * @param enchantment The enchant to register
	 * @return The id that this enchantment was registered with
	 */
	public int registerEnchantment(Enchant enchantment);

}
