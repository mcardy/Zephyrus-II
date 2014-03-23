package net.minezrc.zephyrus.item;

import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

/**
 * Zephyrus - Item.java
 * 
 * Represents a custom item that can be registered with Zephyrus
 * 
 * @author minnymin3
 * 
 */

public interface Item {

	/**
	 * The display name of the item
	 */
	public String getName();
	
	/**
	 * The recipe of the item
	 */
	public ItemRecipe getRecipe();
	
	/**
	 * The material of the item
	 */
	public Material getMaterial();
	
	/**
	 * The lore of the item
	 */
	public List<String> getLore();
	
	/**
	 * The enchantments applied to the item
	 */
	public Map<Enchantment, Integer> getEnchantments();
	
	/**
	 * The minimum required level to craft the item
	 */
	public int getCraftingLevel();
	
}
