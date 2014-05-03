package com.minnymin.zephyrus.item;

import java.util.List;


import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyrus.spell.Spell;

/**
 * Zephyrus - Wand.java<br>
 * Represents a wand item
 * 
 * @author minnymin3
 * 
 */

public interface Wand extends Item {

	/**
	 * The level of spell up to which this wand can accept bound spells
	 * 
	 * @return The spell binding ability
	 */
	public int getBindingAbilityLevel();

	/**
	 * The lore of the wand when a spell is bound to it
	 * 
	 * @param spell The spell being bound
	 * @return The lore of the wand when a spell is bound
	 */
	public List<String> getBoundLore(Spell spell);

	/**
	 * The name of the wand when a spell is bound to it
	 * 
	 * @param spell The spell being bound
	 * @return The name of the wand when a spell is bound
	 */
	public String getBoundName(Spell spell);

	/**
	 * The level of spell that this wand can craft up to
	 * 
	 * @return The spell crafting ability
	 */
	public int getCraftingAbilityLevel();

	/**
	 * The amount of mana discounted on bind cast
	 * 
	 * @param spell The spell being cast
	 * @return The mana discount
	 */
	public int getManaDiscount(Spell spell);

	/**
	 * The amount of power increased on bind cast
	 * 
	 * @param spell The spell being cast
	 * @return The power increase
	 */
	public int getPowerIncrease(Spell spell);

	/**
	 * Gets the spell that is bound to the current itemstack
	 * 
	 * @param stack The ItemStack to get the spell off of
	 * @return The name of the spell or null if no spell is bound
	 */
	public String getSpell(ItemStack stack);

}
