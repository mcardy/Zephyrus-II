package net.minezrc.zephyrus.enchant;

import org.bukkit.enchantments.Enchantment;

/**
 * Zephyrus - Enchant.java
 * <br>
 * An interface used to represent a custom enchantment
 * 
 * @author minnymin3
 * 
 */

public interface Enchant {

	/**
	 * Gets the cost in xp per level of availability
	 * @return
	 */
	public int getCostPerLevel();
	
	/**
	 * Gets the chance that this enchantment has to be applied (will be 1 in however many)
	 * @return
	 */
	public int getChance();
	
	/**
	 * Gets the level that this enchantment maxes out at
	 * @return
	 */
	public int getMaxLevel();
	
	/**
	 * Gets the name of the enchantment that will appear on the item
	 * @return
	 */
	public String getName();
	
	/**
	 * Gets the target items that this enchantment can be applied to
	 * @return
	 */
	public EnchantTarget getTarget();
	
	/**
	 * Checks if the enchantment is compatible with provided enchantment
	 * @param enchantment The enchantment to check
	 * @return True if compatible, false otherwise
	 */
	public boolean isEnchantmentIncompatible(Enchantment enchantment);
	
}
