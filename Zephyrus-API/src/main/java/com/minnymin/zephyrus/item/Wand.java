package com.minnymin.zephyrus.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyrus.YmlConfigFile;
import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.spell.Spell;

/**
 * Zephyrus - Wand.java<br>
 * Represents a wand item
 * 
 * @author minnymin3
 * 
 */

public abstract class Wand extends Item {

	private int bindingAbility;
	private int craftingAbility;
	private List<String> boundLore;
	private String boundName;

	public Wand(String name, String boundName, int craftingLevel, int bindingAbility, int craftingAbility,
			Material mat, List<String> lore, List<String> boundLore) {
		super(name, craftingLevel, mat, lore);
		YmlConfigFile yml = Zephyrus.getItemManager().getConfig();
		yml.addDefaults(defaultName + ".BoundName", translateToAlternate(boundName));
		yml.addDefaults(defaultName + ".BindingAbility", bindingAbility);
		yml.addDefaults(defaultName + ".CraftingAbility", craftingAbility);
		yml.addDefaults(defaultName + ".BoundLore", translateToAlternate(boundLore));
		yml.saveConfig();

		ConfigurationSection config = yml.getConfig().getConfigurationSection(defaultName);

		this.bindingAbility = config.getInt("BindingAbility");
		this.craftingAbility = config.getInt("CraftingAbility");
		this.boundLore = translateFromAlternate(config.getStringList("BoundLore"));
		this.boundName = translateFromAlternate(config.getString("BoundName"));
	}

	/**
	 * The level of spell up to which this wand can accept bound spells
	 * 
	 * @return The spell binding ability
	 */
	public int getBindingAbilityLevel() {
		return bindingAbility;
	}

	/**
	 * The lore of the wand when a spell is bound to it
	 * 
	 * @param spell The spell being bound
	 * @return The lore of the wand when a spell is bound
	 */
	public List<String> getBoundLore(Spell spell) {
		List<String> lore = new ArrayList<String>();
		for (String s : boundLore) {
			lore.add(s.replace("[SPELL]", spell.getName()));
		}
		return lore;
	}

	/**
	 * The name of the wand when a spell is bound to it
	 * 
	 * @param spell The spell being bound
	 * @return The name of the wand when a spell is bound
	 */
	public String getBoundName(Spell spell) {
		return boundName.replace("[SPELL]", spell.getName());
	}

	/**
	 * The level of spell that this wand can craft up to
	 * 
	 * @return The spell crafting ability
	 */
	public int getCraftingAbilityLevel() {
		return craftingAbility;
	}

	/**
	 * The amount of mana discounted on bind cast
	 * 
	 * @param spell The spell being cast
	 * @return The mana discount
	 */
	public abstract int getManaDiscount(Spell spell);

	/**
	 * The amount of power increased on bind cast
	 * 
	 * @param spell The spell being cast
	 * @return The power increase
	 */
	public abstract int getPowerIncrease(Spell spell);

	/**
	 * Gets the spell that is bound to the current itemstack
	 * 
	 * @param stack The ItemStack to get the spell off of
	 * @return The name of the spell or null if no spell is bound
	 */
	public abstract String getSpell(ItemStack stack);

}
