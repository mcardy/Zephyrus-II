package com.minnymin.zephyrus.item;

import java.util.List;

import org.bukkit.Material;

/**
 * Zephyrus - LevelledItem.java<br>
 * Represents an item that can be levelled up
 * 
 * @author minnymin3
 * 
 */

public interface LevelledItem extends Item {

	/**
	 * Gets the level of the item from the given lore.
	 * 
	 * @param lore The lore of the item
	 * @return The level of the item
	 */
	public int getLevel(List<String> lore);

	/**
	 * Gets the lore of the item for the given level. Will replace getLore in
	 * Item
	 * 
	 * @param level The level of the item
	 * @return The lore for that level
	 */
	public List<String> getLevelledLore(int level);

	/**
	 * Gets the cost to level up (amount per level)
	 * 
	 * @return The material when upgrading
	 */
	public Material getMaterialCost();

	/**
	 * Gets the maximum allowed level of this item
	 * 
	 * @return The max allowed level
	 */
	public int getMaxLevel();

}
