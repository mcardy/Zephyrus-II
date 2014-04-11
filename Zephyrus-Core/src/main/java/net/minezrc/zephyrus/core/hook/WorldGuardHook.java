package net.minezrc.zephyrus.core.hook;

import net.minezrc.zephyrus.core.util.reflection.ReflectionUtils;
import net.minezrc.zephyrus.hook.ProtectionHook;
import net.minezrc.zephyrus.spell.Spell;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

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
		return plugin.canBuild(player, block);
	}
	
	@Override
	public boolean canBuild(Player player, Location loc) {
		return plugin.canBuild(player, loc);
	}
	
	@Override
	public boolean canCast(Player player, Spell spell) {
		if (plugin.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).allows(flag)
				|| player.hasPermission("zephyrus.worldguard.bypass")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canTarget(Player player, LivingEntity entity, boolean friendly) {
		if (entity instanceof Player) {
			if (plugin.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).allows(DefaultFlag.PVP)) {
				return true;
			}
			return friendly;
		}
		return true;
	}

	@Override
	public boolean checkHook() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
		if (plugin != null && plugin instanceof WorldGuardPlugin) {
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
		ReflectionUtils.setField(DefaultFlag.class, flags, "flagList");
	}

}
