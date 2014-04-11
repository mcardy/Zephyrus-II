package net.minezrc.zephyrus.hook;

import org.bukkit.entity.Player;

/**
 * Zephyrus - EconomyHook.java
 * 
 * @author minnymin3
 * 
 */

public interface EconomyHook extends PluginHook {

	public void drainBalance(Player player, double amount);
	public double getBalance(Player player);
	
}
