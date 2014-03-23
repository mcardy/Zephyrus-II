package net.minezrc.zephyrus.user;

import net.minezrc.zephyrus.Manager;

import org.bukkit.entity.Player;

/**
 * Zephyrus - UserManager.java
 * 
 * @author minnymin3
 * 
 */

public interface UserManager extends Manager {

	public BarDisplay getBarDisplay();
	public User getUser(Player player);
	public User getUser(String playerName);
	
}
