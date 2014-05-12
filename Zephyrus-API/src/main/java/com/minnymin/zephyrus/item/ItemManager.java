package com.minnymin.zephyrus.item;

import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyrus.Manager;

/**
 * Zephyrus - ItemManager.java <br>
 * Represents the manager of items
 * 
 * @author minnymin3
 * 
 */

public interface ItemManager extends Manager {

	/**
	 * Gets an item by the specified itemstack
	 * 
	 * @param item The itemstack to find
	 * @return A registered item or Null if no item was found
	 */
	public Item getItem(ItemStack item);

	/**
	 * Gets an item by the specified name
	 * 
	 * @param name The name of the item to find
	 * @return A registered item or Null if no item was found
	 */
	public Item getItem(String name);

	/**
	 * Gets an item by the specified name without any formatting
	 * 
	 * @param name The name of the item to find
	 * @return A registered item or NULL if no item was found
	 */
	public Item getItemFromBaseName(String name);

	/**
	 * Registers the specified item with Zephyrus and Bukkit
	 * 
	 * @param item The item to register
	 */
	public void registerItem(Item item);

}
