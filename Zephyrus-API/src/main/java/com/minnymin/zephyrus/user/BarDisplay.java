package com.minnymin.zephyrus.user;

import org.bukkit.entity.Player;

/**
 * Zephyrus - BarDisplay.java<br>
 * Represents a mana bar display
 * 
 * @author minnymin3
 * 
 */

public interface BarDisplay {

	/**
	 * Removes the bar for the given player
	 * 
	 * @param player The player to remove
	 */
	public void removeBar(Player player);

	/**
	 * Sets the bar for the given player
	 * 
	 * @param player The player to set
	 * @param name The name of the bar
	 * @param health The amount of health (200 is a full bar)
	 */
	public void setBar(Player player, String name, int health);

}
