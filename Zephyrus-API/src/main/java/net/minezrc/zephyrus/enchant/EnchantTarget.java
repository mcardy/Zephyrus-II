package net.minezrc.zephyrus.enchant;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Zephyrus - EnchantTarget.java <br>
 * An enum representing the type of item an enchant can be applied to
 * 
 * @author minnymin3
 * 
 */

public enum EnchantTarget {

	/**
	 * Represents all helmets, chestplates, leggings and boots
	 */
	ARMOR(Material.IRON_HELMET, Material.GOLD_HELMET, Material.DIAMOND_HELMET, Material.IRON_CHESTPLATE,
			Material.GOLD_CHESTPLATE, Material.DIAMOND_CHESTPLATE, Material.IRON_LEGGINGS, Material.GOLD_LEGGINGS,
			Material.DIAMOND_CHESTPLATE, Material.IRON_BOOTS, Material.GOLD_BOOTS, Material.DIAMOND_BOOTS),
	/**
	 * Represents all axes
	 */
	AXE(Material.WOOD_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLD_AXE, Material.DIAMOND_AXE),
	/**
	 * Represents all boots
	 */
	BOOTS(Material.IRON_BOOTS, Material.GOLD_BOOTS, Material.DIAMOND_BOOTS),
	/**
	 * Represents bows
	 */
	BOW(Material.BOW),
	/**
	 * Represents all chestplates
	 */
	CHEST(Material.IRON_CHESTPLATE, Material.GOLD_CHESTPLATE, Material.DIAMOND_CHESTPLATE),
	/**
	 * Represents all helmets
	 */
	HELMET(Material.IRON_HELMET, Material.GOLD_HELMET, Material.DIAMOND_HELMET),
	/**
	 * Represents all leggings
	 */
	LEGS(Material.IRON_LEGGINGS, Material.GOLD_LEGGINGS, Material.DIAMOND_CHESTPLATE),
	/**
	 * Represents an enchantment that cannot be applied to anything
	 */
	NONE,
	/**
	 * Represents all pickaxes
	 */
	PICKAXE(Material.WOOD_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE,
			Material.DIAMOND_PICKAXE),
	/**
	 * Represents all shovels
	 */
	SHOVEL(Material.WOOD_SPADE, Material.STONE_SPADE, Material.IRON_SPADE, Material.GOLD_SPADE, Material.DIAMOND_SPADE),
	/**
	 * Represents all swords
	 */
	SWORD(Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLD_SWORD, Material.DIAMOND_SWORD),
	/**
	 * Represents all pickaxes, shovels and axes
	 */
	TOOL(Material.WOOD_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLD_AXE, Material.DIAMOND_AXE,
			Material.WOOD_SPADE, Material.STONE_SPADE, Material.IRON_SPADE, Material.GOLD_SPADE,
			Material.DIAMOND_SPADE, Material.WOOD_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE,
			Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE),
	/**
	 * Represents all swords and bows
	 */
	WEAPON(Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLD_SWORD, Material.DIAMOND_SWORD,
			Material.BOW);

	private Material[] items;

	EnchantTarget(Material... items) {
		this.items = items;
	}

	/**
	 * Checks if the itemstack material is compatible with the target
	 * 
	 * @param item The itemstack to check
	 * @return True if it is compatible
	 */
	public boolean isTypeCompatible(ItemStack item) {
		for (Material mat : items) {
			if (mat == item.getType()) {
				return true;
			}
		}
		return false;
	}

}
