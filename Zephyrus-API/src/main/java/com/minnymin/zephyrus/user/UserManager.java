package com.minnymin.zephyrus.user;


import org.bukkit.entity.Player;

import com.minnymin.zephyrus.Manager;

/**
 * Zephyrus - UserManager.java<br>
 * Represents a user manager
 * 
 * @author minnymin3
 * 
 */

public interface UserManager extends Manager {

	/**
	 * Gets the bar display for the user manager
	 * 
	 * @return A BarDisplay object
	 */
	public BarDisplay getBarDisplay();

	/**
	 * Gets the user for the specified player
	 * 
	 * @param player The player to get
	 * @return The user portraying the player
	 */
	public User getUser(Player player);

	/**
	 * Gets the user for the specified player name
	 * 
	 * @param playerName The player name to get
	 * @return The user portraying the player represented by the name
	 */
	public User getUser(String playerName);

}
