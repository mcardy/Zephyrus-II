package com.minnymin.zephyrus.hook;


import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.spell.Spell;

/**
 * Zephyrus - ProtectionHook.java
 * 
 * @author minnymin3
 * 
 */

public interface ProtectionHook extends PluginHook {

	public boolean canBuild(Player player, Block block);

	public boolean canBuild(Player player, Location loc);

	public boolean canCast(Player player, Spell spell);

	public boolean canTarget(Player player, LivingEntity entity, boolean friendly);

}
