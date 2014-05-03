package com.minnymin.zephyrus.hook;


import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.Manager;
import com.minnymin.zephyrus.spell.Spell;

/**
 * Zephyrus - PluginHookManager.java<br>
 * Represents the manager for all plugin hooks
 * 
 * @author minnymin3
 * 
 */

public interface PluginHookManager extends Manager {

	/**
	 * Adds the specified ProtectionHook to the list of protections
	 * 
	 * @param hook The ProtectionHook to add
	 */
	public void addProtectionHook(ProtectionHook hook);

	/**
	 * Checks all protections hooks to see if the player can build at the
	 * specified block
	 * 
	 * @param player The player to check
	 * @param block The block to check
	 * @return True if the player can build
	 */
	public boolean canBuild(Player player, Block block);

	/**
	 * Checks all protections hooks to see if the player can build at the
	 * specified location
	 * 
	 * @param player The player to check
	 * @param loc The location to check
	 * @return True if the player can build
	 */
	public boolean canBuild(Player player, Location loc);

	/**
	 * Checks all protections hooks to see if the player can cast a spell at the
	 * specified location
	 * 
	 * @param player The player to check
	 * @param spell The spell being cast
	 * @return True if the player can cast
	 */
	public boolean canCast(Player player, Spell spell);

	/**
	 * Checks all protections hooks to see if the player can target the
	 * specified entity
	 * 
	 * @param player The player to check
	 * @param target The target to check
	 * @param friendly Whether or not the action is friendly
	 * @return Trie if the player can target
	 */
	public boolean canTarget(Player player, LivingEntity target, boolean friendly);

	/**
	 * Retrieves the EconomyHook for the current server instance
	 * 
	 * @return Null if no economy was hooked
	 */
	public EconomyHook getVaultHook();

}
