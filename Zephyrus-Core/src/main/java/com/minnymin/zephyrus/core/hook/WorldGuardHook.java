package com.minnymin.zephyrus.core.hook;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.core.util.reflection.ReflectionUtils;
import com.minnymin.zephyrus.hook.ProtectionHook;
import com.minnymin.zephyrus.spell.Spell;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;

/**
 * Zephyrus - WorldGuardHook.java
 * 
 * @author minnymin3
 * 
 */

public class WorldGuardHook implements ProtectionHook {

	private StateFlag flag;
	private WorldGuardPlugin plugin;

	@Override
	public boolean canBuild(Player player, Block block) {
		if (plugin.canBuild(player, block)) {
			return true;
		} else {
			Language.sendError("user.target.block.worldguard", "You cannot target that block here", player);
			return false;
		}
	}
	
	@Override
	public boolean canBuild(Player player, Location loc) {
		if (plugin.canBuild(player, loc)) {
			return true;
		} else {
			Language.sendError("user.target.block.worldguard", "You cannot target that block here", player);
			return false;
		}
	}
	
	@Override
	public boolean canCast(Player player, Spell spell) {
		if (plugin.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).allows(flag)
				|| player.hasPermission("zephyrus.worldguard.bypass")) {
			return true;
		}
		Language.sendError("spell.cast.region", "You cannot cast spells inside of this region", player);
		return false;
	}

	@Override
	public boolean canTarget(Player player, LivingEntity entity, boolean friendly) {
		if (entity instanceof Player) {
			if (plugin.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).allows(DefaultFlag.PVP)) {
				return true;
			}
			if (friendly) {
				return true;
			} else {
				Language.sendError("user.target.entity.worldguard", "You cannot target that player here", player);
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean checkHook() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
		if (plugin != null && plugin instanceof WorldGuardPlugin) {
			Zephyrus.getPlugin().getLogger().info("[Plugin Hooks] Found and hooked WorldGuard");
			return true;
		}
		return false;
	}

	@Override
	public void setupHook() {
		plugin = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
		flag = new StateFlag("allowspells", true);
		Flag<?>[] flags = new Flag<?>[DefaultFlag.flagsList.length + 1];
		System.arraycopy(DefaultFlag.flagsList, 0, flags, 0, DefaultFlag.flagsList.length);
		flags[DefaultFlag.flagsList.length] = flag;
		ReflectionUtils.setStaticFinalField(DefaultFlag.class, flags, "flagsList");
	}

}
