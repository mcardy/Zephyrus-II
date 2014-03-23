package net.minezrc.zephyrus.item;

import net.minezrc.zephyrus.Manager;

import org.bukkit.inventory.ItemStack;

/**
 * Zephyrus - ItemManager.java
 * 
 * @author minnymin3
 * 
 */

public interface ItemManager extends Manager {

	public Item getItem(String name);
	public Item getItem(ItemStack item);
	public void registerItem(Item item);
	
}
