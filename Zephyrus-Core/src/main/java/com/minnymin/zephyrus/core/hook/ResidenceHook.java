package com.minnymin.zephyrus.core.hook;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.FlagPermissions;
import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.hook.ProtectionHook;
import com.minnymin.zephyrus.spell.Spell;

/**
 * Zephyrus - ResidenceHook.java
 * 
 * @author minnymin3
 * 
 */

public class ResidenceHook implements ProtectionHook {

	@Override
	public boolean checkHook() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("Residence");
		if (plugin != null && plugin instanceof Residence) {
			Zephyrus.getPlugin().getLogger().info("[Plugin Hooks] Found and hooked Residence");
			return true;
		}
		return false;
	}

	@Override
	public void setupHook() {
		FlagPermissions.addResidenceOnlyFlag("cast");
	}

	@Override
	public boolean canBuild(Player player, Block block) {
		return canBuild(player, block.getLocation());
	}

	@Override
	public boolean canBuild(Player player, Location loc) {
		FlagPermissions perms = Residence.getPermsByLocForPlayer(loc, player);
		return perms.has("build", true);
	}

	@Override
	public boolean canCast(Player player, Spell spell) {
		FlagPermissions perms = Residence.getPermsByLoc(player.getLocation());
		return perms.has("cast", true);
	}

	@Override
	public boolean canTarget(Player player, LivingEntity entity, boolean friendly) {
		if (entity instanceof Player) {
			FlagPermissions perm1 = Residence.getPermsByLoc(player.getLocation());
			FlagPermissions perm2 = Residence.getPermsByLoc(player.getLocation());
			return perm1.has("pvp", true) && perm2.has("pvp", true);
		}
		return true;
	}

}
