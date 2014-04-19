package net.minezrc.zephyrus.item;

import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

/**
 * Zephyrus - Item.java <br>
 * Represents a custom item that can be registered with Zephyrus
 * 
 * @author minnymin3
 * 
 */

public interface Item {

	/**
	 * The display name of the item
	 * 
	 * @return The ItemStack name
	 */
	public String getName();

	/**
	 * The recipe of the item
	 * 
	 * @return An ItemRecipe object
	 */
	public ItemRecipe getRecipe();

	/**
	 * The material of the item
	 * 
	 * @return The ItemStack's material
	 */
	public Material getMaterial();

	/**
	 * The lore of the item
	 * 
	 * @return The ItemStack lore
	 */
	public List<String> getLore();

	/**
	 * The enchantments applied to the item
	 * 
	 * @return A map of Enchantment and Integer
	 */
	public Map<Enchantment, Integer> getEnchantments();

	/**
	 * The minimum required level to craft the item
	 * 
	 * @return An int of the crafting level
	 */
	public int getCraftingLevel();

}
