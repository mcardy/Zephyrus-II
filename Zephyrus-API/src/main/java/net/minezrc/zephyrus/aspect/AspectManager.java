package net.minezrc.zephyrus.aspect;

import net.minezrc.zephyrus.Manager;

import org.bukkit.inventory.ItemStack;

/**
 * Zephyrus - ReagentManager.java
 * 
 * @author minnymin3
 * 
 */

public interface AspectManager extends Manager {

	public AspectList getAspects(ItemStack item);
	
}
