package net.minezrc.zephyrus.user;

import org.bukkit.entity.Player;

/**
 * Zephyrus - ManaBarDisplay.java
 * 
 * @author minnymin3
 * 
 */

public interface BarDisplay {

	public void removeBar(Player player);
	public void setBar(Player player, String name, int health);
	
}
