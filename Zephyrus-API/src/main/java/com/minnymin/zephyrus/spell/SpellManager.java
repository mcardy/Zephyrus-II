package com.minnymin.zephyrus.spell;

import java.util.List;
import java.util.Set;


import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyrus.Manager;
import com.minnymin.zephyrus.YmlConfigFile;

/**
 * Zephyrus - SpellManager.java<br>
 * Represents a spell manager
 * 
 * @author minnymin3
 * 
 */

public interface SpellManager extends Manager {

	/**
	 * Gets the config file of the spells
	 * 
	 * @return A YmlConfigFile for the spells
	 */
	public YmlConfigFile getConfig();

	/**
	 * Gets the spell from the class of the spell
	 * 
	 * @param spellClass The class of the spell
	 * @return Null if no spell was found
	 */
	public Spell getSpell(Class<? extends Spell> spellClass);

	/**
	 * Gets the spell from the name of the spell
	 * 
	 * @param name The name of the spell
	 * @return Null if no spell was found
	 */
	public Spell getSpell(String name);

	/**
	 * Gets the set of all registered spells
	 * 
	 * @return A cloned set of spells
	 */
	public Set<String> getSpellNameSet();

	/**
	 * Gets all spells that can be crafted with the given list of items
	 * 
	 * @param recipe The items to find
	 * @return A list of spells that can be crafted
	 */
	public List<Spell> getSpells(Set<ItemStack> recipe);

	/**
	 * Gets the set of the names of all registered spells
	 * 
	 * @return A set of spell names
	 */
	public Set<Spell> getSpellSet();

	/**
	 * Gets the spell tome ItemStack for the given spell
	 * 
	 * @param spell The spell to create a tome of
	 * @return A spell tome ItemStack
	 */
	public ItemStack getSpellTome(Spell spell);

	/**
	 * Registers the given spell with Zephyrus
	 * 
	 * @param spell The spell to register
	 */
	public void registerSpell(Spell spell);

}
