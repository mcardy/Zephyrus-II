package com.minnymin.zephyrus.enchant;

import org.bukkit.enchantments.Enchantment;

/**
 * Zephyrus - Enchant.java <br>
 * An interface used to represent a custom enchantment
 * 
 * @author minnymin3
 * 
 */

public interface Enchant {

	/**
	 * Gets the chance that this enchantment has to be applied (will be 1 in
	 * however many)
	 * 
	 * @return The chance of the enchant
	 */
	public int getChance();

	/**
	 * Gets the cost in xp per level of availability
	 * 
	 * @return The cost per level
	 */
	public int getCostPerLevel();

	/**
	 * Gets the level that this enchantment maxes out at
	 * 
	 * @return The max level of the enchant
	 */
	public int getMaxLevel();

	/**
	 * Gets the name of the enchantment that will appear on the item
	 * 
	 * @return The name of the enchant
	 */
	public String getName();

	/**
	 * Gets the target items that this enchantment can be applied to
	 * 
	 * @return The target of the enchant
	 */
	public EnchantTarget getTarget();

	/**
	 * Checks if the enchantment is compatible with provided enchantment
	 * 
	 * @param enchantment The enchantment to check
	 * @return True if compatible, false otherwise
	 */
	public boolean isEnchantmentIncompatible(Enchantment enchantment);

}
