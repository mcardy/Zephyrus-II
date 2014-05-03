package com.minnymin.zephyrus.hook;

import org.bukkit.entity.Player;

/**
 * Zephyrus - EconomyHook.java<br>
 * Represents a hooked economy plugin
 * 
 * @author minnymin3
 * 
 */

public interface EconomyHook extends PluginHook {

	/**
	 * Drains the specified amount from the specified player
	 * 
	 * @param player The player to drain
	 * @param amount The amount to drain
	 */
	public void drainBalance(Player player, double amount);

	/**
	 * Gets the balance of the specified player
	 * 
	 * @param player The player to get
	 * @return A double with the amount that the player has
	 */
	public double getBalance(Player player);

}
